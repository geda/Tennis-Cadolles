package ch.creasystem.tennis.shared.ranking;

import java.io.Serializable;

public class OponentScore implements Serializable {

	private long playerId;
	private String name;
	private Float totalMatches;
	private Float totalPoints;
	
	public OponentScore() {
		super();
	}

	public OponentScore(long playerId, String name, Float totalPoints,
			Float totalMatches) {
		super();
		this.playerId = playerId;
		this.name = name;
		this.totalMatches = totalMatches;
		this.totalPoints = totalPoints;
	}
	
	public Float addTotalPoints(float totalToAdd) {
		if (totalPoints == null) {
			totalPoints = new Float(totalToAdd);
		} else {
			totalPoints = totalPoints + totalToAdd;
		}
		return totalPoints;
	}
	
	public Float addTotalMatches(float totalToAdd) {
		if (totalMatches == null) {
			totalMatches = new Float(totalToAdd);
		} else {
			totalMatches = totalMatches + totalToAdd;
		}
		return totalMatches;
	}
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getTotalMatches() {
		return totalMatches;
	}
	
	
	public void setTotalMatches(Float totalMatches) {
		this.totalMatches = totalMatches;
	}
	public Float getTotalPoints() {
		return totalPoints;
	}
	
	public void setTotalPoints(Float totalPoints) {
		this.totalPoints = totalPoints;
	}
	
	public Float getTotalPourcent() {
		if (this.totalPoints != null && this.totalMatches != null) {
			return (this.totalPoints / this.totalMatches) * 100;
		}
		
		return null;
	}
	


	@Override
	public String toString() {
		return "OponentScore [playerId=" + playerId + ", name=" + name
				+ ", totalMatches=" + totalMatches + ", totalPoints="
				+ totalPoints + "]";
	}
	
	
}
