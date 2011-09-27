package ch.creasystem.tennis.server.rest;

import org.restlet.resource.Get;

import ch.creasystem.tennis.shared.ranking.ResultRankingList;

public interface RankingController {

	 
	 @Get
	 ResultRankingList getRanking() throws Exception;
}
