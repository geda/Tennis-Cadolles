package ch.creasystem.tennis.server.rest.impl;

import java.util.HashMap;
import java.util.Map;

import org.restlet.Request;
import org.restlet.resource.ServerResource;

import ch.creasystem.tennis.client.match.MatchService;
import ch.creasystem.tennis.client.player.PlayerService;
import ch.creasystem.tennis.server.match.MatchServiceImpl;
import ch.creasystem.tennis.server.player.PlayerServiceImpl;
import ch.creasystem.tennis.server.rest.MatchController;
import ch.creasystem.tennis.server.rest.dto.MatchDTO;
import ch.creasystem.tennis.shared.match.Match;
import ch.creasystem.tennis.shared.player.Player;

public class MatchControllerImpl extends ServerResource implements
		MatchController {
	private MatchService matchService = new MatchServiceImpl();
	private Map<Long, Player> playerMap;
	private Map<String, Player> playerFirstnameMap;

	public MatchControllerImpl() {
		PlayerService playerService = new PlayerServiceImpl();
		playerMap = playerService.getPlayerMap();
		playerFirstnameMap = new HashMap<String, Player>();
		for (Player player : playerMap.values()) {
			playerFirstnameMap.put(player.getFirstName(), player);
		}

	}
	
	

	@Override
	public MatchDTO getMatch() throws Exception {
		Request req = super.getRequest();
		if (req.getAttributes().get("match") == null) {
			throw new IllegalArgumentException("match is not defined");
		}

		String matchId = req.getAttributes().get("match").toString();
		Match match = matchService.getMatch(Long.parseLong(matchId));
		return MatchHelper.matchToDTO(match, playerMap);

	}

	@Override
	public void deleteMatch() throws Exception {

		Request req = super.getRequest();
		if (req.getAttributes().get("match") == null) {
			throw new IllegalArgumentException("match is not defined");
		}
		String matchId = req.getAttributes().get("match").toString();
		matchService.deleteMatch(Long.parseLong(matchId));

	}

	@Override
	public MatchDTO saveMatch(MatchDTO matchDTO)
			throws Exception {
		return this.saveMatch(matchDTO, false);
	}
	
	private MatchDTO saveMatch(MatchDTO matchDTO, boolean sendNotification)
			throws Exception {
		Match match = null;
		if (matchDTO == null) {
			throw new IllegalArgumentException("match is null");
		}
		if (matchDTO.getId() == null) {

			// Create
			match = matchService.createMatch(
					MatchHelper.dtoToMatch(matchDTO, playerFirstnameMap),
					sendNotification);

		} else {
			// Update
			match = matchService.updateMatch(MatchHelper.dtoToMatch(matchDTO,
					playerFirstnameMap));
		}
		return MatchHelper.matchToDTO(match, playerMap);
	}

}
