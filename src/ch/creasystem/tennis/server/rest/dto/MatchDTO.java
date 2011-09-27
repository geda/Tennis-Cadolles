package ch.creasystem.tennis.server.rest.dto;

import java.io.Serializable;

public class MatchDTO implements Serializable {

    private Long id;
	
	private String date;
	private String winner;
	private String winner2;
	private String looser;
	private String looser2;
	private String score;
	private Float point;
	private Float valeurMatch;
	private boolean doubleMatch;
	private String commentaire;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public String getWinner2() {
		return winner2;
	}
	public void setWinner2(String winner2) {
		this.winner2 = winner2;
	}
	public String getLooser() {
		return looser;
	}
	public void setLooser(String looser) {
		this.looser = looser;
	}
	public String getLooser2() {
		return looser2;
	}
	public void setLooser2(String looser2) {
		this.looser2 = looser2;
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
	
}
