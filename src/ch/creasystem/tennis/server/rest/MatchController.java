package ch.creasystem.tennis.server.rest;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

import ch.creasystem.tennis.server.rest.dto.MatchDTO;

public interface MatchController {
	
	@Get
	public MatchDTO getMatch() throws Exception;

	@Delete
	public void deleteMatch() throws Exception;
	
	@Put
	MatchDTO saveMatch(MatchDTO matchDTO) throws Exception;
	
//	MatchDTO saveMatch(MatchDTO matchDTO, boolean sendNotification) throws Exception;
}
