package ch.creasystem.tennis.server.match;

import java.util.logging.Logger;

import ch.creasystem.tennis.shared.player.Player;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.googlecode.objectify.Key;



public class DAOPlayer extends DAOBASE<Player> {
	
	public DAOPlayer() {
		super(Player.class);
	}
	

	Logger log = Logger.getLogger(DAOPlayer.class.getName());

	public Player findAPlayerById(Long id) {

		Player found = ofy().find(Player.class, id);
		if (found == null)
			return null;
		else
			return found;
	}
	public Player findAPlayerByName(String lastName) {
		
		Player found = ofy().query(Player.class).filter("lastName",lastName).get();
		if (found == null)
			return null;
		else
			return found;
	}

	public Player createAPlayer(String firstName, String lastName, String nickName, String googleAccount, String notificationMail)
			throws Exception {
		Player player = ofy().query(Player.class).filter("googleAccount",googleAccount).get();

		if (player == null) {
			player = new Player(firstName, lastName, nickName, googleAccount, notificationMail);
			ofy().put(player);
			return player;
		} else {
			throw new Exception("Player with lastname " + lastName + " already exist");
		}
	}

	public Player updatePlayer(Player player) throws Exception {
		Key<Player> updateKey = this.put(player);
		try {
			return this.get(updateKey);
		} catch (EntityNotFoundException e) {
			throw new Exception("Error while updating player");
		}
		
	}

}
