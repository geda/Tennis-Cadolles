package ch.creasystem.tennis.server.match;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import ch.creasystem.tennis.shared.match.Match;
import ch.creasystem.tennis.shared.player.Player;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.googlecode.objectify.Key;

public class DAOMatch extends DAOBASE<Match> {
	
	public DAOMatch() {
		super(Match.class);
	}

	Logger log = Logger.getLogger(DAOMatch.class.getName());


	public Match findAMatchById(Long id) {

		Match found = ofy().find(Match.class, id);
		if (found == null)
			return null;
		else
			return found;
	}
	

	public Match createMatch(Date date, Player winner, Player looser,
			Player winner2, Player looser2, String score, Float point, Float valeurMatch,
			Boolean doubleMatch, String commentaire) throws Exception {
		Key<Player> keyWinner = new Key<Player>(Player.class, winner.getId());
		Key<Player> keyLooser = new Key<Player>(Player.class, looser.getId());
		Key<Player> keyWinner2 = null;
		Key<Player> keyLooser2 = null;

		if (doubleMatch) {
			keyWinner2 = new Key<Player>(Player.class, winner2.getId());
			keyLooser2 = new Key<Player>(Player.class, looser2.getId());
		}
		Match match = new Match(date, keyWinner, keyLooser, keyWinner2, keyLooser2, score,
				valeurMatch, valeurMatch, doubleMatch, commentaire);
		ofy().put(match);
		return match;
	}
	
	public Match createMatch(Match newMatch) {
		if (newMatch == null || newMatch.getId()!= null) {
			throw new IllegalArgumentException("New match is null or id is already defined");
		}
		ofy().put(newMatch);
		return newMatch;
	}
	

		
	public List<Match> findMatchesForSeason(int year) {
		List<Match> matches = new ArrayList<Match>();
		
		// only the specified season filter
		for (Match match :this.findAll()) {
			if (match.getDate() != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(match.getDate());
				if (cal.get(Calendar.YEAR) == year) {
					matches.add(match);	
				}
			}
		}
		return matches;
	}
	public Match updateMatch(Match match) throws Exception {
		Key<Match> updateKey = this.put(match);
		try {
			return this.get(updateKey);
		} catch (EntityNotFoundException e) {
			throw new Exception("Error while updating match");
		}
		
	}
}
