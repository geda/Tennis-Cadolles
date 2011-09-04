package ch.creasystem.tennis.shared.ranking;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import ch.creasystem.tennis.shared.player.Player;

import com.google.appengine.api.datastore.EntityNotFoundException;

public class RankingList implements Serializable {

	private Map<Long, PlayerRanking> playerRankingMap = new TreeMap<Long, PlayerRanking>();
	private TreeMap<Long, Player> playerMap;

	public PlayerRanking getRankingForPlayer(Player player)
			throws EntityNotFoundException {
		PlayerRanking ranking = playerRankingMap.get(player.getId());

		if (ranking == null) {
			ranking = new PlayerRanking();
			ranking.setName(player.getNickName());
			ranking.setPlayerId(player.getId());
			playerRankingMap.put(player.getId(), ranking);
		}
		return ranking;
	}

	public RankingList() {
		super();
	}

	public String toString() {
		StringBuilder builder = new StringBuilder("Rankinglist");
		for (PlayerRanking playerRanking : playerRankingMap.values()) {
			builder.append("\r\nplayer " + playerRanking.getName());
			builder.append(" total score:" + playerRanking.getTotalPoints());
			builder.append(" total matches:" + playerRanking.getTotalMatches());
		}
		return builder.toString();
	}

	public TreeMap<Long, Player> getPlayerMap() {
		return playerMap;
	}

	public void setPlayerMap(TreeMap<Long, Player> playerMap) {
		this.playerMap = playerMap;
	}

	public Map<Long, PlayerRanking> getPlayerRankingMap() {
		return playerRankingMap;
	}

	public void setPlayerRankingMap(Map<Long, PlayerRanking> playerRankingMap) {
		this.playerRankingMap = playerRankingMap;
	}

}
