package ch.creasystem.tennis.shared.player;

import java.io.Serializable;

import javax.persistence.Id;



/**
 * Define an Entity Player
 * @author gerberda
 *
 */
public class Player implements Serializable {
	
	@Id
    private Long id;

	private String lastName;
	
	private String firstName;

	private String nickName;
	
	
	private String googleAccount;
	
	private String notificationMail;
	
	
	
	public Player() {
		super();
	}

	public Player(String firstName, String lastName, String nickName, String googleAccount, String notificationMail) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.nickName = nickName;
		this.googleAccount = googleAccount;
		this.notificationMail = notificationMail;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getGoogleAccount() {
		return googleAccount;
	}

	public void setGoogleAccount(String googleAccount) {
		this.googleAccount = googleAccount;
	}

	public String getNotificationMail() {
		return notificationMail;
	}

	public void setNotificationMail(String notificationMail) {
		this.notificationMail = notificationMail;
	}

	public String tostring() {
		return "Player "+firstName+" "+lastName+" . Surnom: "+nickName+" .email: "+notificationMail;
	}

}
