package ch.creasystem.tennis.client.player;

import java.util.ArrayList;
import java.util.TreeMap;

import ch.creasystem.tennis.shared.player.Player;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("player")
public interface PlayerService extends RemoteService{
	public ArrayList<Player> getPlayerList();
	
	public TreeMap<Long, Player> getPlayerMap();
	
	public Player createAPlayer(String firstName, String lastName, String nickName, String email, String userId) throws Exception;
	
	public void deletePlayer(Long playerId) throws Exception;
	
	public Player updatePlayer(Player player) throws Exception;
}
