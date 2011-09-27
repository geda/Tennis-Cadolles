package ch.creasystem.tennis.server.rest.impl;

import java.text.SimpleDateFormat;
import java.util.Map;

import ch.creasystem.tennis.server.rest.dto.MatchDTO;
import ch.creasystem.tennis.shared.match.Match;
import ch.creasystem.tennis.shared.player.Player;

import com.googlecode.objectify.Key;

public class MatchHelper {
	private static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	public static MatchDTO matchToDTO(Match match, Map<Long, Player > playerMap) {
		MatchDTO matchDTO = new MatchDTO();
		matchDTO.setId(match.getId());
		matchDTO.setDate(df.format(match.getDate()));
		matchDTO.setWinner(playerMap.get(match.getWinner().getId())
				.getFirstName());
		matchDTO.setLooser(playerMap.get(match.getLooser().getId())
				.getFirstName());
		if (match.isDoubleMatch()) {
			matchDTO.setWinner2(playerMap.get(match.getWinner2().getId())
					.getFirstName());
			matchDTO.setLooser2(playerMap.get(match.getLooser2().getId())
					.getFirstName());
		}
		matchDTO.setPoint(match.getPoint());
		matchDTO.setValeurMatch(match.getValeurMatch());
		matchDTO.setDoubleMatch(match.isDoubleMatch());
		matchDTO.setCommentaire(match.getCommentaire());
		matchDTO.setScore(match.getScore());
		return matchDTO;
	}
	
	public static Match dtoToMatch(MatchDTO matchDTO, Map<String, Player > playerfirstnameMap) throws Exception {
		Match match = new Match();
		match.setId(matchDTO.getId());
		
		match.setDate(df.parse(matchDTO.getDate()));
		match.setWinner(new Key <Player>(Player.class, playerfirstnameMap.get(matchDTO.getWinner()).getId()));  
		match.setLooser(new Key <Player>(Player.class, playerfirstnameMap.get(matchDTO.getLooser()).getId()));  
				
		
		if (match.isDoubleMatch()) {
			match.setWinner2(new Key <Player>(Player.class, playerfirstnameMap.get(matchDTO.getWinner2()).getId()));
			match.setLooser2(new Key <Player>(Player.class, playerfirstnameMap.get(matchDTO.getLooser2()).getId()));
		}
		match.setPoint(matchDTO.getPoint());
		match.setValeurMatch(matchDTO.getValeurMatch());
		match.setDoubleMatch(matchDTO.isDoubleMatch());
		match.setCommentaire(matchDTO.getCommentaire());
		match.setScore(matchDTO.getScore());
		return match;
		
	}
}
