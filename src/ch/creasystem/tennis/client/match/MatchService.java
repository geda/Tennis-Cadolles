package ch.creasystem.tennis.client.match;

import java.util.ArrayList;
import java.util.Date;

import ch.creasystem.tennis.client.login.NotAuthorizedException;
import ch.creasystem.tennis.client.login.NotLoggedInException;
import ch.creasystem.tennis.shared.match.Match;
import ch.creasystem.tennis.shared.player.Player;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("match")
public interface MatchService extends RemoteService{

	public String getAllMatchCsv();
	
	public ArrayList<Match> getMatchList(Integer saison) throws NotLoggedInException, NotAuthorizedException;
	
	public Match createMatch(Date date, Player winner, Player looser,
			Player winner2, Player looser2, String score, Float point, Float valeurMatch,
			Boolean doubleMatch, String commentaire, Boolean sendNotification) throws Exception;
	
	public void deleteMatch(Long MatchId) throws Exception;
	
	public Match updateMatch(Match match) throws Exception;
}
