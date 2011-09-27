package ch.creasystem.tennis.server.rest.dto;

import java.io.Serializable;
import java.util.List;

public class MatchList implements Serializable {
	private List<MatchDTO> matchlist;

	
	public MatchList(List<MatchDTO> matchlist) {
		super();
		this.matchlist = matchlist;
	}


	public List<MatchDTO> getMatchlist() {
		return matchlist;
	}
	
	
}
