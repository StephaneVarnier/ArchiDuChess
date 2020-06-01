package archiduchess.microservice_clientui.proxies;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import archiduchess.microservice_clientui.bean.UserBean;

@FeignClient(name="microservice-user", url="localhost:9998")
public interface MicroserviceUserProxy {

		@GetMapping(path="/archiduchess/user/{username}")
		public UserBean findUserByUsername(@PathVariable String username);
		
		
}