package ch.creasystem.tennis.server.rest;

import org.restlet.resource.Get;

import ch.creasystem.tennis.server.rest.dto.MatchList;

public interface MatchListController {

	 @Get
	 MatchList getMatchList() throws Exception;
}
