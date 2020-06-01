package archiduchess.microservice_clientui.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import archiduchess.microservice_clientui.bean.OnlineGameBean;

@FeignClient(name="microservice-onlineGame")
public interface MicroserviceOnlineGameProxy {

		@GetMapping(path="/archiduchess/onlineGames/{username}")
		public List<OnlineGameBean> FindGamesByUsername(@PathVariable String userName);
		
		
}
