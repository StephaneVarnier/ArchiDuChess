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
	
	private String pgn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlayerWhite() {
		return playerWhite;
	}

	public void setPlayerWhite(String playerWhite) {
		this.playerWhite = playerWhite;
	}

	public String getPlayerBlack() {
		return playerBlack;
	}

	public void setPlayerBlack(String playerBlack) {
		this.playerBlack = playerBlack;
	}

	public String getTimeControl() {
		return timeControl;
	}

	public void setTimeControl(String timeControl) {
		this.timeControl = timeControl;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getOpening() {
		return opening;
	}

	public void setOpening(String opening) {
		this.opening = opening;
	}

	public String getPgn() {
		return pgn;
	}

	public void setPgn(String pgn) {
		this.pgn = pgn;
	}
	
	
	
}
