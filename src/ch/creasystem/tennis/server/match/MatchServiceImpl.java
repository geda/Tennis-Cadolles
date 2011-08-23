package ch.creasystem.tennis.server.match;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import org.datanucleus.util.StringUtils;

import ch.creasystem.tennis.client.login.LoginService;
import ch.creasystem.tennis.client.login.NotAuthorizedException;
import ch.creasystem.tennis.client.login.NotLoggedInException;
import ch.creasystem.tennis.client.match.MatchService;
import ch.creasystem.tennis.client.match.MatchValidationException;
import ch.creasystem.tennis.client.player.PlayerService;
import ch.creasystem.tennis.server.login.LoginServiceImpl;
import ch.creasystem.tennis.server.mail.EmailHelper;
import ch.creasystem.tennis.server.player.PlayerServiceImpl;
import ch.creasystem.tennis.shared.match.Match;
import ch.creasystem.tennis.shared.player.Player;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class MatchServiceImpl extends RemoteServiceServlet implements MatchService {

	DAOMatch daoMatch = new DAOMatch();
	LoginService loginService = new LoginServiceImpl();
	PlayerService playerService = new PlayerServiceImpl();
	
	@Override
	public ArrayList<Match> getMatchList(Integer saison) throws NotLoggedInException, NotAuthorizedException {
		int tennisSaison;
		loginService.getPlayerLoggedIn();
		
		if (saison != null) {
			tennisSaison = saison;
		} else {
			Calendar cal = Calendar.getInstance();
			tennisSaison =cal.get(Calendar.YEAR);
		}
		
		ArrayList<Match> matchList = (ArrayList<Match>) daoMatch.findMatchesForSeason(tennisSaison);
		Collections.sort(matchList);
		return matchList;
	}

	@Override
	public Match createMatch(Date date, Player winner, Player looser, Player winner2, Player looser2, String score,
			Float point, Float valeurMatch, Boolean doubleMatch, String commentaire, Boolean sendNotification) throws Exception {
		
		Player loggedPlayer = loginService.getPlayerLoggedIn();
		
		if (date == null) {
			throw new MatchValidationException("Date incorrecte");
		}

		if (winner == null || looser == null) {
			throw new MatchValidationException("Le gagnant ou le perdant n'est pas défini");
		}
		if (doubleMatch) {
			if (winner2 == null || looser2 == null) {
				throw new MatchValidationException("Le 2e gagnant ou le 2e perdant n'est pas défini");
			}
		}

		if (StringUtils.isEmpty(score)) {
			throw new MatchValidationException("Score incorrect");
		}

		if (point == null || valeurMatch == null) {
			throw new MatchValidationException("Le nombre de points ou la valeur du match n'est pas défini");
		}
		Match match = daoMatch.createMatch(date, winner, looser, winner2, looser2, score, point, valeurMatch, doubleMatch,
				commentaire);
		if (sendNotification) {
			String classement = getAllMatchCsv();
			EmailHelper.sendNotificationMail(loggedPlayer, match, playerService.getPlayerMap(), classement);
		}
		return match;
	}

	@Override
	public void deleteMatch(Long matchId) throws Exception {
		Match match = daoMatch.get(matchId);
		if (match == null) {
			throw new Exception("Le Match a effacer avec l'Id " + matchId + " n'existe pas.");
		}
		daoMatch.delete(match);

	}

	@Override
	public Match updateMatch(Match match) throws Exception {
		return daoMatch.updateMatch(match);
	}

	@Override
	public String getAllMatchCsv() {
		StringBuilder sb = new StringBuilder();
		List<Match> matchList = daoMatch.findAll();
		TreeMap<Long, Player> playerMap = playerService.getPlayerMap();
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		sb.append("Date,Winner1,Winner2,Looser1,Looser2,Valeur Point,Valeur Match,Commentaire\n");
		for (Match match : matchList) {
			if (match.getDate() != null){
				sb.append(df.format(match.getDate()));
			}
			sb.append(",");
			sb.append(playerMap.get(match.getWinner()).getFirstName());
			sb.append(",");
			if (match.isDoubleMatch()) {
				sb.append(playerMap.get(match.getWinner2()).getFirstName());
			}
			sb.append(",");
			sb.append(playerMap.get(match.getLooser()).getFirstName());
			sb.append(",");
			if (match.isDoubleMatch()) {
				sb.append(playerMap.get(match.getLooser2()).getFirstName());
			}
			sb.append(",");
			sb.append(match.getPoint());
			sb.append(",");
			sb.append(match.getValeurMatch());
			sb.append(",");
			sb.append(match.getScore());
			sb.append(",");
			sb.append(match.getCommentaire());
			sb.append("\n");
		}
		return sb.toString();
	}

}
