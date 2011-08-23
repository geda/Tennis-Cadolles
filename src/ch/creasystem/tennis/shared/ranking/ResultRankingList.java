package ch.creasystem.tennis.shared.ranking;

import java.io.Serializable;
import java.util.ArrayList;

import ch.creasystem.tennis.shared.player.Player;

public class ResultRankingList implements Serializable {
	
	private ArrayList<PlayerRanking> playerRanking;
	private ArrayList<Player> playerList;
	
	public ResultRankingList() {
		super();
	}

	public ArrayList<PlayerRanking> getPlayerRanking() {
		return playerRanking;
	}

	public void setPlayerRanking(ArrayList<PlayerRanking> playerRanking) {
		this.playerRanking = playerRanking;
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}

}
