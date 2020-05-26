package archiduchess.microservice_onlinegame.controler;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import archiduchess.microservice_onlinegame.configuration.ApplicationPropertiesConfiguration;
import archiduchess.microservice_onlinegame.modele.OnlineGame;
import archiduchess.microservice_onlinegame.repository.OnlineGameRepository;

@RestController
@RequestMapping(path="/archiduchess")
public class OnlineGameControler {

	@Autowired
	private OnlineGameRepository onlineGameRepo;
	
	@Autowired
	ApplicationPropertiesConfiguration appProperties;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(path="/onlineGames")
	public @ResponseBody Iterable<OnlineGame> getAllOnlineGames() {
				
		Iterable<OnlineGame> gamesIterable = onlineGameRepo.findAll();
		return limitList(gamesIterable);
	}
	
	@GetMapping(path="onlineGames/white/{username}")
	public @ResponseBody Iterable<OnlineGame> getOnlineGamesByWhiteUsername(@PathVariable String username) {
	
		Iterable<OnlineGame> gamesIterable = onlineGameRepo.findByPlayerWhite(username);
        return limitList(gamesIterable);
	}
	
	@GetMapping(path="onlineGames/black/{username}")
	public @ResponseBody Iterable<OnlineGame> getOnlineGamesByBlackUsername(@PathVariable String username) {
		
		Iterable<OnlineGame> gamesIterable = onlineGameRepo.findByPlayerBlack(username);
		return limitList(gamesIterable);
	}
	
	@GetMapping(path="onlineGames/{username}")
	public @ResponseBody Iterable<OnlineGame> getOnlineGamesByUsername(@PathVariable String username) {
		
		Iterable<OnlineGame> gamesIterable = onlineGameRepo.findByPlayerBlackOrPlayerWhite(username, username);
		return limitList(gamesIterable);
	}
	
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

	
	// limit the size of the returned list 
	private Iterable<OnlineGame> limitList(Iterable<OnlineGame> gamesIterable) {
		  	List gamesList = StreamSupport 
	                .stream(gamesIterable.spliterator(), false) 
	                .collect(Collectors.toList()); 
	        
		    int limit = Math.min(appProperties.getGamesNumberLimit(), gamesList.size());
	        List<OnlineGame> limitedList = gamesList.subList(0, limit);
	        return limitedList;
	}
	
	
	
	
}