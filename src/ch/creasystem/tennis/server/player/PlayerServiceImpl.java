package ch.creasystem.tennis.server.player;

import java.util.ArrayList;
import java.util.TreeMap;

import ch.creasystem.tennis.client.player.PlayerService;
import ch.creasystem.tennis.server.match.DAOPlayer;
import ch.creasystem.tennis.shared.player.Player;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PlayerServiceImpl extends RemoteServiceServlet implements
PlayerService {
	
	DAOPlayer daoPlayer = new DAOPlayer();

	@Override
	public ArrayList<Player> getPlayerList() {
		return (ArrayList<Player>) daoPlayer.findAll();
	}

	@Override
	public TreeMap<Long, Player> getPlayerMap() {
		TreeMap<Long, Player> playerMap = new TreeMap<Long, Player>();
		for (Player player: daoPlayer.findAll()) {
			playerMap.put(player.getId(), player);
		}
		return playerMap;
	}
	@Override
	public Player createAPlayer(String firstName, String lastName, String nickName, String birthday, String googleAccount, String notificationMail) throws Exception {
		return daoPlayer.createAPlayer(firstName, lastName, nickName, birthday, googleAccount, notificationMail);
	}

	@Override
	public void deletePlayer(Long playerId) throws Exception {
		Player player = daoPlayer.get(playerId);
		if (player == null){
			throw new Exception("Le joueur a effacé avec l'Id "+playerId+" n'existe pas.");
		}
	    daoPlayer.delete(player);
	}

	@Override
	public Player updatePlayer(Player player) throws Exception {
		return daoPlayer.updatePlayer(player);
	}

}
