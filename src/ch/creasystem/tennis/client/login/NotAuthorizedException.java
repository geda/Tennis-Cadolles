package ch.creasystem.tennis.client.login;

import java.io.Serializable;

public class NotAuthorizedException extends Exception implements Serializable {

	public NotAuthorizedException() {
		super();
	}

	public NotAuthorizedException(String message) {
		super(message);
	}

}
