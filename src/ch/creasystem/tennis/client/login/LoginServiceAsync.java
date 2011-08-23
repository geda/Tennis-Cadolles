package ch.creasystem.tennis.client.login;

import ch.creasystem.tennis.shared.player.Player;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {

	void login(String requestUri, AsyncCallback<LoginInfo> callback);

	void getPlayerLoggedIn(AsyncCallback<Player> callback);

}
