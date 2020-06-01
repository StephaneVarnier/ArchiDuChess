package archiduchess.microservice_onlinegame;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import chesspresso.pgn.PGNSyntaxError;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
public class MicroserviceOnlineGameApplication {

	public static void main(String[] args) throws PGNSyntaxError, IOException {
		SpringApplication.run(MicroserviceOnlineGameApplication.class, args);
	
	}

}
