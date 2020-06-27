package archiduchess.microservice_leaderboard.modele;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Leader")
public class Leader implements Comparable<Leader>{

	private int id;
	private String username;
	private String name;
	private int score;
	private String title;
	private String avatar;
	
	public Leader() {
		// TODO Auto-generated constructor stub
	}
	
	@Override 
	public int compareTo(Leader leader) {
		return (leader.score - this.score);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
	
	
}
