package ch.creasystem.tennis.client.ranking;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import ch.creasystem.tennis.server.ranking.DummyRankingGenerator;
import ch.creasystem.tennis.shared.ranking.PlayerRanking;


public class DummyRankingGeneratorTest {

	@Test
	public void testgenerateDummyRankingList() {

		ArrayList<PlayerRanking> rankingList = DummyRankingGenerator.generateDummyRankingList();
		Assert.assertNotNull(rankingList);
		int i = 1;
		for (PlayerRanking player : rankingList) {
			if (rankingList.size() > i) {
				//Assert.assertTrue(player.getTotalPourcentStr() > rankingList.get(i).getTotalPourcent());
			}
			i++;
		}
		
	
		System.out.println(rankingList);
	}
}
