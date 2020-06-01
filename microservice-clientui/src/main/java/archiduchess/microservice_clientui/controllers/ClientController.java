package archiduchess.microservice_clientui.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
//	@GetMapping(value="/fen-list/{id}")
//	public String fenList(Long id, Model model ) {
//		OnlineGameBean gameBean = mGameProxy.findGameById(id);
//		List<String> fens = 
//		
//		return "FenList";
//	}
	
}
