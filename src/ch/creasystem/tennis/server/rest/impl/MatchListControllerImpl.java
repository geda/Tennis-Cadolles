package ch.creasystem.tennis.server.rest.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.restlet.resource.ServerResource;

import ch.creasystem.tennis.client.match.MatchService;
import ch.creasystem.tennis.client.player.PlayerService;
import ch.creasystem.tennis.server.match.MatchServiceImpl;
import ch.creasystem.tennis.server.player.PlayerServiceImpl;
import ch.creasystem.tennis.server.rest.MatchListController;
import ch.creasystem.tennis.server.rest.dto.MatchDTO;
import ch.creasystem.tennis.server.rest.dto.MatchList;
import ch.creasystem.tennis.shared.match.Match;
import ch.creasystem.tennis.shared.player.Player;

//import com.google.gwt.i18n.client.DateTimeFormat;

public class MatchListControllerImpl extends ServerResource implements
		MatchListController {
	private MatchService matchService = new MatchServiceImpl();
	private PlayerService playerService = new PlayerServiceImpl();

	public MatchListControllerImpl() {
	}

	@Override
	public MatchList getMatchList() throws Exception {

		Map<Long, Player> playerMap = playerService.getPlayerMap();

		List<MatchDTO> resultList = new ArrayList<MatchDTO>();
		for (Match match : matchService.getMatchList(null)) {
			
			MatchDTO matchDTO = MatchHelper.matchToDTO(match, playerMap);
			resultList.add(matchDTO);
		}
		MatchList matchList = new MatchList(resultList);
		return matchList;
	}

}
