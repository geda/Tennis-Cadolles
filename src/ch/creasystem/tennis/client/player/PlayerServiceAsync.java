package ch.creasystem.tennis.client.player;

import java.util.ArrayList;
import java.util.TreeMap;

import ch.creasystem.tennis.shared.player.Player;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PlayerServiceAsync {

	public void getPlayerList(AsyncCallback<ArrayList<Player>> callback);

	void createAPlayer(String firstName, String lastName, String nickName,
			 String email, String userId,
			AsyncCallback<Player> callback);

	void deletePlayer(Long playerId, AsyncCallback<Void> callback);

	void updatePlayer(Player player, AsyncCallback<Player> callback);

	void getPlayerMap(AsyncCallback<TreeMap<Long, Player>> callback);

}
