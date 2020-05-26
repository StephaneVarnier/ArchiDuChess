package archiduchess.microservice_onlinegame.modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OnlineGame {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	
	private String playerWhite;
	private String playerBlack;

	private String timeControl;
	
	private String resultat;
	private String opening;
	
	@Column(length = 3600)
	private String pgn;
	
	public OnlineGame() {
		// TODO Auto-generated constructor stub
	}

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

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
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
