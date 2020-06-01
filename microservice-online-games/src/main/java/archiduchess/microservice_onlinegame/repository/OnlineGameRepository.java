package archiduchess.microservice_onlinegame.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import archiduchess.microservice_onlinegame.modele.OnlineGame;

public interface OnlineGameRepository extends CrudRepository<OnlineGame,  Integer> {
	
	List<OnlineGame> findByPlayerWhite (String playerWhite) ; 
	
	List<OnlineGame> findByPlayerBlack (String playerBlack) ;

	Iterable<OnlineGame> findByPlayerBlackOrPlayerWhite(String playerBlack, String playerWhite);

	Iterable<OnlineGame> findByPlayerBlackAndResultat(String username, String resultat);

	Iterable<OnlineGame> findByPlayerWhiteAndResultat(String username, String resultat);

	Iterable<OnlineGame> findByResultat(String res);

	OnlineGame findGameById(long id);	
	
	
}
