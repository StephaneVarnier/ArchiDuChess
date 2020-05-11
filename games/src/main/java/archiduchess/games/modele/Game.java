package archiduchess.games.modele;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import archiduchess.games.rules.Move;

@Entity
public class Game {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	
	private String playerWhite;
	private String playerBlack;
	private String timeControl;
	
	private String result;
	private String opening;
	
	private List<Move> moves = new ArrayList<>();
	
	
	
}
