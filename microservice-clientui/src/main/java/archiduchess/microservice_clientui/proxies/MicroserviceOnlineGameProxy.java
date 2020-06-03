package archiduchess.microservice_clientui.proxies;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import archiduchess.microservice_clientui.bean.OnlineGameBean;

@FeignClient(name="microservice-onlineGame", url="localhost:9999")
public interface MicroserviceOnlineGameProxy {

		@GetMapping(path="/archiduchess/onlineGames/{username}")
		public List<OnlineGameBean> findGamesByUsername(@PathVariable String username);

		@GetMapping(path="/archiduchess/onlineGame/{id}")
		public OnlineGameBean findGameById(@PathVariable Long id);

		@GetMapping(path="/archiduchess/onlineGame/{id}/fens")
		public List<String> findFensById(@PathVariable Long id);
		
		@RequestMapping(value = "/archiduchess/onlineGames/fen/{fen}", method = RequestMethod.GET)
		public List<OnlineGameBean> findGamesByFen(@PathVariable String fen)  ;
}
