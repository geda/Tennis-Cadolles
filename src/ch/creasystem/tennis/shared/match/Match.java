package ch.creasystem.tennis.shared.match;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import ch.creasystem.tennis.shared.player.Player;

import com.googlecode.objectify.Key;
/**
 * Représente un match
 * @author gerberda
 *
 */
@Entity
public class Match implements Serializable, Comparable<Match> {

	@Id
    private Long id;
	
	Date date;
	
	Key<Player> winner;

	Key<Player> winner2;
	
	Key<Player> looser;

	Key<Player> looser2;
	
	String score;
	
	Float point;
	
	Float valeurMatch;

	boolean doubleMatch;
	
	String commentaire;
	
	
	public Match() {
		super();
	}


	/**
	 * Constructor for simple match
	 * @param date
	 * @param winner
	 * @param looser
	 * @param score
	 * @param point
	 * @param valeurMatch
	 */
	public Match(Date date, Key<Player> winner, Key<Player> looser, Key<Player> winner2, Key<Player> looser2, String score,
			Float point, Float valeurMatch, boolean doubleMatch, String commentaire) {
		super();
		
		if (winner==null || looser==null) {
			throw new IllegalArgumentException("winner or looser is not defined");
		}
		
		if (doubleMatch) {
			if (winner2==null || looser2==null) {
				throw new IllegalArgumentException("For a double winner2 or looser2 is nor defined");
			}			
		}
		this.date = date;
		this.winner = winner;
		this.winner2 = winner2;
		this.looser = looser;
		this.looser2 = looser2;
		this.score = score;
		this.point = point;
		this.valeurMatch = valeurMatch;
		this.doubleMatch=doubleMatch;
		this.commentaire =commentaire;

	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public Float getPoint() {
		return point;
	}

	public void setPoint(Float point) {
		this.point = point;
	}

	public Float getValeurMatch() {
		return valeurMatch;
	}

	public void setValeurMatch(Float valeurMatch) {
		this.valeurMatch = valeurMatch;
	}

	public Long getId() {
		return id;
	}
	
	
	public Key<Player> getWinner() {
		return winner;
	}


	public void setWinner(Key<Player> winner) {
		this.winner = winner;
	}


	public Key<Player> getWinner2() {
		return winner2;
	}


	public void setWinner2(Key<Player> winner2) {
		this.winner2 = winner2;
	}


	public Key<Player> getLooser() {
		return looser;
	}


	public void setLooser(Key<Player> looser) {
		this.looser = looser;
	}


	public Key<Player> getLooser2() {
		return looser2;
	}


	public void setLooser2(Key<Player> looser2) {
		this.looser2 = looser2;
	}


	public boolean isDoubleMatch() {
		return doubleMatch;
	}


	public void setDoubleMatch(boolean doubleMatch) {
		this.doubleMatch = doubleMatch;
	}


	public String getCommentaire() {
		return commentaire;
	}


	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}


	public String toString() {
		String matchtype = "simple";
		if (doubleMatch) {
			matchtype ="double";
		}
		
		return "Match "+matchtype+" "+winner+" "+looser+" "+ score+ " "+date;
	}


	@Override
	public int compareTo(Match match) {
		if (match == null || match.getDate() == null) {
			return 1;
		} else if (this.date == null) {
			return -1;
		} 
		return this.date.compareTo(match.getDate());
		
	}
	
}
