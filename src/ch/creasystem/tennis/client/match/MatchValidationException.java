package ch.creasystem.tennis.client.match;

import java.io.Serializable;

public class MatchValidationException extends Exception implements Serializable {

	public MatchValidationException() {
		super();
	}

	public MatchValidationException(String message) {
		super(message);
	}

}
