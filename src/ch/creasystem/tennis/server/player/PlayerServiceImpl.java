package ch.creasystem.tennis.server.player;

import java.util.ArrayList;
import java.util.TreeMap;

import ch.creasystem.tennis.client.login.LoginService;
import ch.creasystem.tennis.client.player.PlayerService;
import ch.creasystem.tennis.server.login.LoginServiceImpl;
import ch.creasystem.tennis.server.match.DAOPlayer;
import ch.creasystem.tennis.shared.player.Player;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PlayerServiceImpl extends RemoteServiceServlet implements
PlayerService {
	
	DAOPlayer daoPlayer = new DAOPlayer();
	LoginService loginService = new LoginServiceImpl();

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
	public Player createAPlayer(String firstName, String lastName, String nickName, String googleAccount, String notificationMail) throws Exception {
		loginService.isUserAuthotized();
		return daoPlayer.createAPlayer(firstName, lastName, nickName, googleAccount, notificationMail);
	}

	@Override
	public void deletePlayer(Long playerId) throws Exception {
		loginService.isUserAuthotized();
		Player player = daoPlayer.get(playerId);
		if (player == null){
			throw new Exception("Le joueur a effacé avec l'Id "+playerId+" n'existe pas.");
		}
	    daoPlayer.delete(player);
	}

	@Override
	public Player updatePlayer(Player player) throws Exception {
		loginService.isUserAuthotized();
		return daoPlayer.updatePlayer(player);
	}

}
