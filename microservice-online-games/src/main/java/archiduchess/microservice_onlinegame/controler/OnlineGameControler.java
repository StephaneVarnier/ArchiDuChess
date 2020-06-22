package archiduchess.microservice_onlinegame.controler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import archiduchess.microservice_onlinegame.configuration.ApplicationPropertiesConfiguration;
import archiduchess.microservice_onlinegame.modele.OnlineGame;
import archiduchess.microservice_onlinegame.repository.OnlineGameRepository;
import chesspresso.game.Game;
import chesspresso.pgn.PGNReader;
import chesspresso.pgn.PGNSyntaxError;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path="/archiduchess")
public class OnlineGameControler {

	@Autowired
	private OnlineGameRepository onlineGameRepo;
	
	@Autowired
	ApplicationPropertiesConfiguration appProperties;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@ApiOperation(value = "Liste toutes les parties en base.")
	@GetMapping(path="/onlineGames")
	public @ResponseBody Iterable<OnlineGame> getAllOnlineGames() {
				
		Iterable<OnlineGame> gamesIterable = onlineGameRepo.findAll();
		return limitList(gamesIterable);
	}
	
	@ApiOperation(value = "Recherche une partie par id")
	@GetMapping(path="/onlineGames/{id}")
	public @ResponseBody Optional<OnlineGame> getGameById(@PathVariable String id) throws Exception {
		log.info("<----------------- "+id+" -------------->" );
		Optional<OnlineGame> og = onlineGameRepo.findById(id);
		
		if (!og.isPresent()) throw new Exception("There is no game "+ id +" !");
		log.info(og.toString());
		return og;

		
	}
	
	@ApiOperation(value = "Recherche les positions d'une partie par id")
	@GetMapping(path="/onlineGames/{id}/fens")
	public @ResponseBody List<String> getFensById(@PathVariable String id) throws PGNSyntaxError, IOException {
		
		Optional<OnlineGame> onlineGame = onlineGameRepo.findById(id);
		List<String> fens = new ArrayList<>();
		
		if (onlineGame.isPresent()) {
			Game game = this.parseOnlineGame(onlineGame.get());
				
			game.gotoStart();
			do  {
				fens.add(game.getPosition().getFEN());
				game.goForward();
			} while (game.hasNextMove());
			
			fens.add(game.getPosition().getFEN());
		}
				
		return fens;
	}
	
	@ApiOperation(value = "Recherche les coups d'une partie par id sous format LAN - long annotation")
	@GetMapping(path="/onlineGames/{id}/lans")
	public @ResponseBody List<String> getLansById(@PathVariable String id) throws PGNSyntaxError, IOException {
		Optional<OnlineGame> onlineGame = onlineGameRepo.findById(id);
		List<String> moves = new ArrayList<>();
		
		
		if (onlineGame.isPresent()) {
			Game game = this.parseOnlineGame(onlineGame.get());
			
			game.gotoStart();
			do  {
				moves.add(game.getNextMove().getLAN());
				game.goForward();
			} while (game.hasNextMove());
		}
		
		
		//moves.add(game.getPosition().getFEN());
		return moves;
	}
	
	@ApiOperation(value = "Recherche les coups d'une partie par id sous format SAN - short annotation")
	@GetMapping(path="/onlineGames/{id}/sans")
	public @ResponseBody List<String> getSansById(@PathVariable String id) throws PGNSyntaxError, IOException {
		Optional<OnlineGame> onlineGame = onlineGameRepo.findById(id);
		List<String> moves = new ArrayList<>();
		
		
		if (onlineGame.isPresent()) {
			Game game = this.parseOnlineGame(onlineGame.get());
			
			game.gotoStart();
			do  {
				moves.add(game.getNextMove().getSAN());
				game.goForward();
			} while (game.hasNextMove());
		}
		
		//moves.add(game.getPosition().getFEN());
		return moves;
	}
	
	
	
	
	@ApiOperation(value = "Liste les parties d'un joueur donné avec les blancs")
	@GetMapping(path="onlineGames/white/{username}")
	public @ResponseBody Iterable<OnlineGame> getOnlineGamesByWhiteUsername(@PathVariable String username) {
	
		Iterable<OnlineGame> gamesIterable = onlineGameRepo.findByPlayerWhite(username);
        return limitList(gamesIterable);
	}
	
	@ApiOperation(value = "Liste les parties d'un joueur donné avec les noirs")
	@GetMapping(path="onlineGames/black/{username}")
	public @ResponseBody Iterable<OnlineGame> getOnlineGamesByBlackUsername(@PathVariable String username) {
		
		Iterable<OnlineGame> gamesIterable = onlineGameRepo.findByPlayerBlack(username);
		return limitList(gamesIterable);
	}
	
	@ApiOperation(value = "Liste les parties d'un joueur donné")
	@GetMapping(path="onlineGames/user/{username}")
	public @ResponseBody Iterable<OnlineGame> getOnlineGamesByUsername(@PathVariable String username) {
		
		Iterable<OnlineGame> gamesIterable = onlineGameRepo.findByPlayerBlackOrPlayerWhite(username, username);
		return limitList(gamesIterable);
	}
	
	@ApiOperation(value = "Liste les parties d'un joueur donné selon la couleur et le résultat final")
	@GetMapping(path="onlineGames/{color}/{username}/{resultat}")
	public @ResponseBody Iterable<OnlineGame> getGamesByUsernameColorResult(@PathVariable String color, @PathVariable String username,  @PathVariable String resultat) {
		
		Iterable<OnlineGame> gamesIterable ;
		if (color.equals("black")) { 
			gamesIterable = onlineGameRepo.findByPlayerBlackAndResultat(username, resultat);
		} 
		else {
			gamesIterable = onlineGameRepo.findByPlayerWhiteAndResultat(username, resultat);
		}
		return limitList(gamesIterable);
	}
	
	@ApiOperation(value = "Liste les parties ayant atteint une position donnée sous format FEN")
	@RequestMapping(value = "onlineGames/fen/**", method = RequestMethod.GET)
	public @ResponseBody List<OnlineGame> getGamesByFen(HttpServletRequest request) throws PGNSyntaxError, IOException {
		
	    String requestURL = request.getRequestURL().toString();
	    String fenURL= requestURL.split("onlineGames/fen/")[1];
	    String fen = java.net.URLDecoder.decode(fenURL, StandardCharsets.UTF_8.name());
	    log.info("FEN -------------------------> "+fen);
	    
		Iterable<OnlineGame> gamesIterable = onlineGameRepo.findAll();
		
		List<OnlineGame> filteredGames= new ArrayList<>();

		for (OnlineGame onlineGame : gamesIterable) {
			if (contains(onlineGame, fen)) 
			{
				filteredGames.add(onlineGame);
			}
		}
		return limitList(filteredGames);
	}
	
	

	@ApiOperation(value = "Liste les parties ayant atteint une position donnée sous format FEN, selon un résultat")
	@RequestMapping(value = "onlineGames/{resultat}/fen/**", method = RequestMethod.GET)
	public @ResponseBody List<OnlineGame> getGamesByFenAndResultat(HttpServletRequest request) throws PGNSyntaxError, IOException {
		
	    String requestURL = request.getRequestURL().toString();
	    String fenURL0= requestURL.split("/fen/")[0];
	    String fenURL1= requestURL.split("/fen/")[1];
	    
	    String res= fenURL0.split("onlineGames/")[1];
	    String fen = java.net.URLDecoder.decode(fenURL1, StandardCharsets.UTF_8.name());
	    log.info("FEN -------------------------> "+fen);
	    
		Iterable<OnlineGame> gamesIterable = onlineGameRepo.findByResultat(res);
		
		List<OnlineGame> filteredGames= new ArrayList<>();

		for (OnlineGame onlineGame : gamesIterable) {
			if (contains(onlineGame, fen)) 
			{
				filteredGames.add(onlineGame);
			}
		}
		return limitList(filteredGames);
	}
	
	public boolean contains(OnlineGame onlineGame, String fen) throws PGNSyntaxError, IOException {

		Game game = parseOnlineGame(onlineGame);
		
		game.gotoStart();
		do  {
			log.info(onlineGame.getId() + " --> "+game.getPosition().getFEN());
			if (game.getPosition().getFEN().contains(fen))
				return true;
			game.goForward();
			
		} while (game.hasNextMove());
		
		log.info(onlineGame.getId() + " --> "+game.getPosition().getFEN());
		if (game.getPosition().getFEN().contains(fen))
			return true;

		return false;
	}
	
	
	public Game parseOnlineGame(OnlineGame onlineGame) throws PGNSyntaxError, IOException {
		String pgnStr = removeClk(onlineGame.getPgn(), "{[", "]}");

		InputStream is = new ByteArrayInputStream(pgnStr.getBytes());
		PGNReader pgn = new PGNReader(is, "");
		return pgn.parseGame();
		
	}
	

	
	// limit the size of the returned list 
	private List<OnlineGame> limitList(Iterable<OnlineGame> gamesIterable) {
		  	List gamesList = StreamSupport 
	                .stream(gamesIterable.spliterator(), false) 
	                .collect(Collectors.toList()); 
	        
		    int limit = Math.min(appProperties.getGamesNumberLimit(), gamesList.size());
	        List<OnlineGame> limitedList = gamesList.subList(0, limit);
	        return limitedList;
	}
	
	
	public static String removeClk(String str, String start, String end) {
		StringBuilder sb = new StringBuilder(str);
		while (sb.toString().contains(end)) {
			int endIndex = sb.lastIndexOf(end);
			int startIndex = sb.lastIndexOf(start);
			sb = sb.delete(startIndex, endIndex+start.length());
		}
		return sb.toString();
	}
	
	
}