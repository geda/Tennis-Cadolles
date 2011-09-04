package ch.creasystem.tennis.shared.ranking;


import java.io.Serializable;
import java.util.TreeMap;

import ch.creasystem.tennis.shared.player.Player;

import com.google.gwt.i18n.client.NumberFormat;


/**
 * Rang final du joueur
 * 
 * @author gerberda
 * 
 */
	public class PlayerRanking implements Serializable {

   
	private Long playerId;
	private String name;
	private Float totalPoints;
	private Float totalMatches;
	private int ranking;
	private TreeMap<Long, OponentScore> opponentResult = new  TreeMap<Long, OponentScore>();
	
	public PlayerRanking() {
		super();
	}

	public PlayerRanking(Long playerId, String name, Float totalPoints,
			Float totalMatches, Float totalPourcent, int ranking,
			TreeMap<Long, OponentScore> opponentResult) {
		super();
		this.playerId = playerId;
		this.name = name;
		this.totalPoints = totalPoints;
		this.totalMatches = totalMatches;
		this.ranking = ranking;
		this.opponentResult = opponentResult;
	}
	

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getTotalPoints() {
		return totalPoints;
	}
	
	public String getTotalPointsStr() {
		if (this.totalPoints != null) {
			return NumberFormat.getFormat("#.##").format(this.totalPoints);
		}
		return "";
	}

	public void setTotalPoints(Float totalPoints) {
		this.totalPoints = totalPoints;
	}
	

	public Float getTotalMatches() {
		return totalMatches;
	}

	public String getTotalMatchesStr() {
		if (this.totalMatches != null) {
			return NumberFormat.getFormat("#.##").format(this.totalMatches);
		}
		return "";
	}
	
	public void setTotalMatches(Float totalMatches) {
		this.totalMatches = totalMatches;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public Float getTotalPourcent() {
		
		int index = 0;
		Float total =0f;
		for (OponentScore oponentScore : this.opponentResult.values()) {
			if (!this.playerId.equals(oponentScore.getPlayerId())) {
				if (oponentScore.getTotalPourcent() == null) {
					return null;
				}
				total += oponentScore.getTotalPourcent();
				index++;
			}
			
		}
		if (index == 0) {
			return null;
		}
		return total / index;
	}
	
	public String getTotalPourcentStr() {
		if (getTotalPourcent() != null) {

			return NumberFormat.getFormat("#.##").format(getTotalPourcent());
		}
		return "";
	}



	public TreeMap<Long, OponentScore> getOpponentResult() {
		return opponentResult;
	}

	public void setOpponentResult(TreeMap<Long, OponentScore> opponentResult) {
		this.opponentResult = opponentResult;
	}

	@Override
	public String toString() {
		return "PlayerRanking [playerId=" + playerId + ", name=" + name
				+ ", totalPoints=" + totalPoints + ", totalMatches="
				+ totalMatches 
				+ ", ranking=" + ranking + ", opponentResult=" + opponentResult
				+ "]";
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
	
	public OponentScore getScoreForOpponent(Player opponent) {
		OponentScore score = opponentResult.get(opponent.getId());
		
		if (score == null) {
			score = new OponentScore();
			score.setName(opponent.getNickName());
			score.setPlayerId(opponent.getId());
			opponentResult.put(opponent.getId(), score);
		}
		return score;
	}
}
