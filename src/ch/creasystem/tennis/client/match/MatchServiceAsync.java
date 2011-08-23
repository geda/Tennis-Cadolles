package ch.creasystem.tennis.client.match;

import java.util.ArrayList;
import java.util.Date;

import ch.creasystem.tennis.shared.match.Match;
import ch.creasystem.tennis.shared.player.Player;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MatchServiceAsync {

	void createMatch(Date date, Player winner, Player looser, Player winner2, Player looser2, String score,
			Float point, Float valeurMatch, Boolean doubleMatch, String commentaire, Boolean sendNotification, AsyncCallback<Match> callback);

	void deleteMatch(Long matchId, AsyncCallback<Void> callback);

	void getMatchList(Integer saison, AsyncCallback<ArrayList<Match>> callback);

	void updateMatch(Match match, AsyncCallback<Match> callback);

	void getAllMatchCsv(AsyncCallback<String> callback);

}
