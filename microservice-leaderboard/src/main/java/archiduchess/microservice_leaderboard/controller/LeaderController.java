package archiduchess.microservice_leaderboard.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import archiduchess.microservice_leaderboard.modele.Leader;
import archiduchess.microservice_leaderboard.repository.LeaderRepository;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path="/archiduchess")
public class LeaderController {
	
	@Autowired
	private LeaderRepository leaderRepo;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@ApiOperation(value = "Liste tous les champions en base.")
	@GetMapping(path="/leaders")
	public @ResponseBody List<Leader> getAllLeaders() {
				
		List<Leader>  ls = leaderRepo.findAll();
		Collections.sort(ls);
		return ls;
		
	}

}
