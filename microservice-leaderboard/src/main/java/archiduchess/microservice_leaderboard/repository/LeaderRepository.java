package archiduchess.microservice_leaderboard.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import archiduchess.microservice_leaderboard.modele.Leader;

public interface LeaderRepository extends MongoRepository<Leader, Integer> {

	
}
