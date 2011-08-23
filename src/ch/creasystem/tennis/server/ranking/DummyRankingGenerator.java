package ch.creasystem.tennis.server.ranking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import ch.creasystem.tennis.shared.ranking.OponentScore;
import ch.creasystem.tennis.shared.ranking.PlayerRanking;

public class DummyRankingGenerator {

	public static ArrayList<PlayerRanking> generateDummyRankingList() {
		ArrayList<PlayerRanking> rankingList = new ArrayList<PlayerRanking>();

//		// David
//		PlayerRanking daveRanking = new PlayerRanking(1l, "David", 11f, 12f,
//				93.3f, 1, null);
//		TreeMap<Long, OponentScore> daveScores = new TreeMap<Long, OponentScore>();
//		daveScores.put(1l, new OponentScore(1l, "David", null, null, null));
//		daveScores.put(2l, new OponentScore(2l, "Juan", 4f, 5f, 0.8f));
//		daveScores.put(3l, new OponentScore(3l, "Patrick", 3.5f, 3.5f, 1f));
//		daveScores.put(4l, new OponentScore(4l, "Gaetan", 3.5f, 3.5f, 1f));
//		daveRanking.setOpponentResult(daveScores);
//		rankingList.add(daveRanking);
//
//		// Juan
//		PlayerRanking juanRanking = new PlayerRanking(2l, "Juan", 5f, 13f, 38.3f,
//				2, null);
//		TreeMap<Long, OponentScore> juanScores = new TreeMap<Long, OponentScore>();
//		juanScores.put(1l, new OponentScore(1l, "David", 1f, 5f, 0.2f));
//		juanScores.put(2l, new OponentScore(2l, "Juan", null, null, null));
//		juanScores.put(3l, new OponentScore(3l, "Patrick", 3f, 5.5f, 0.5f));
//		juanScores.put(4l, new OponentScore(4l, "Gaetan", 1f, 2.5f, 0.4f));
//		juanRanking.setOpponentResult(juanScores);
//		rankingList.add(juanRanking);
//
//		// Koko
//		PlayerRanking kokoRanking = new PlayerRanking(3l, "Patrick", 6.5f, 13.5f, 44.8f,
//				3, null);
//		TreeMap<Long, OponentScore> kokoScores = new TreeMap<Long, OponentScore>();
//		kokoScores.put(1l, new OponentScore(1l, "David", 0f, 3.5f, 0f));
//		kokoScores.put(2l, new OponentScore(2l, "Juan", 2.5f, 5.5f, 0.5f));
//		kokoScores.put(3l, new OponentScore(3l, "Patrick", null, null, null));
//		kokoScores.put(4l, new OponentScore(4l, "Gaetan", 4f, 4.5f, 0.9f));
//		kokoRanking.setOpponentResult(kokoScores);
//		rankingList.add(kokoRanking);
//
//		// Gaillouf
//		PlayerRanking gailloufRanking = new PlayerRanking(4l, "Gaetan", 2f, 39.5f, 24.8f,
//				4, null);
//		TreeMap<Long, OponentScore> gailloufScores = new TreeMap<Long, OponentScore>();
//		gailloufScores.put(1l, new OponentScore(1l, "David", 0f, 3.5f, 0f));
//		gailloufScores.put(2l, new OponentScore(2l, "Juan", 1.5f, 2.5f, 0.6f));
//		gailloufScores.put(3l, new OponentScore(3l, "Patrick", 0.5f, 3.5f, 0.1f));
//		gailloufScores.put(4l, new OponentScore(4l, "Gaetan", null, null, null));
//		gailloufRanking.setOpponentResult(gailloufScores);
//		
//		rankingList.add(gailloufRanking);
//		//Collections.sort(rankingList);
//		ArrayList<Long> playerList = new ArrayList <Long>();
//		for (PlayerRanking ranking : rankingList) {
//			playerList.add(ranking.getPlayerId());
//		}
////		daveRanking.sortOpponentResult(playerList);
////		juanRanking.sortOpponentResult(playerList);
////		kokoRanking.sortOpponentResult(playerList);
////		gailloufRanking.sortOpponentResult(playerList);
//		
		return rankingList;
	}
}
