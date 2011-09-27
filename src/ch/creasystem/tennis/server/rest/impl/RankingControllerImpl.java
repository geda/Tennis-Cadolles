package ch.creasystem.tennis.server.rest.impl;

import org.restlet.resource.ServerResource;

import ch.creasystem.tennis.client.ranking.RankingService;
import ch.creasystem.tennis.server.ranking.RankingServiceImpl;
import ch.creasystem.tennis.server.rest.RankingController;
import ch.creasystem.tennis.shared.ranking.ResultRankingList;

public class RankingControllerImpl extends ServerResource implements
		RankingController {

	private RankingService rankingService = new RankingServiceImpl();
		
	public RankingControllerImpl() {

	}

	@Override
	public ResultRankingList getRanking() throws Exception {
		return rankingService.getResultRanking(null);
	}

}
