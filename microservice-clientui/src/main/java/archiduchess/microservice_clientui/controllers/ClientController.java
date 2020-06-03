package archiduchess.microservice_clientui.controllers;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import archiduchess.microservice_clientui.bean.FenBean;
import archiduchess.microservice_clientui.bean.OnlineGameBean;
import archiduchess.microservice_clientui.bean.UserBean;
import archiduchess.microservice_clientui.proxies.MicroserviceOnlineGameProxy;
import archiduchess.microservice_clientui.proxies.MicroserviceUserProxy;

@Controller
public class ClientController {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MicroserviceOnlineGameProxy mGameProxy;
	
	@Autowired
	MicroserviceUserProxy mUserProxy;
	
	@RequestMapping("/")
	public String accueil(Model model) {

		UserBean userBean = new UserBean();
		model.addAttribute("userBean", userBean);
		
		log.info("---------------- Initialisation de la page Accueil ");
		return "Accueil";

	}
	
	@PostMapping(value="/find-user")
	public String findUser(UserBean userBean, Model model ) {
		UserBean user = mUserProxy.findUserByUsername(userBean.getUsername());
		List<OnlineGameBean> games = mGameProxy.findGamesByUsername(userBean.getUsername());
		model.addAttribute("user", user);
		model.addAttribute("games", games);
		return "UserGames";
	}
	
	@GetMapping(value="/fen-list-stats/{id}/{username}")
	public String fenListUser(@PathVariable Long id, @PathVariable String username, Model model ) {
		
		log.info("id -----------> " +id);
		log.info("user ---------> " +username);
		
		List<String> fens = mGameProxy.findFensById(id);
		List<FenBean> fenBeans = new ArrayList<>();
		
		for (String fen : fens) { 
			FenBean fenBean = new FenBean();
			
			fenBean.setFen(fen);
			List<OnlineGameBean> games = mGameProxy.findGamesByFen(fen);
			fenBean.setGameNumber(games.size());
			fenBean.setPct(pctCalculate(games, username)) ;
			fenBeans.add(fenBean);
			
		}
		
		model.addAttribute("fenBeans", fenBeans);
		return "FenListStats";
	}
	
	@GetMapping(value="/fen-list/{id}")
	public String fenList(@PathVariable Long id, Model model ) {
		
		log.info("id -----------> " +id);
		List<String> fens = mGameProxy.findFensById(id);
	
		model.addAttribute("fens", fens);
		return "FenList";
	}
	
	public String pctCalculate(List<OnlineGameBean> games , String username) {
		
		double points = 0;
		
		for (OnlineGameBean game : games) {
			points = points + pointsCalculate(game, username);
		}
		Double pct =  points/games.size() * 100;
		return (pct + " %");
	}
	
	public double pointsCalculate(OnlineGameBean game, String username) {
		
		if (game.getPlayerWhite().equals(username)) {
			if (game.getResultat().equals("1-0")) return 1.0;
			if (game.getResultat().equals("0-1")) return 0.0;
			return 0.5;
			
		}
		if (game.getPlayerBlack().equals(username)) {
			if (game.getResultat().equals("1-0")) return 0.0;
			if (game.getResultat().equals("0-1")) return 1.0;
			return 0.5;
		}
		return 0;
	}
	
}
