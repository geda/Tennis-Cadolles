package ch.creasystem.tennis.client.login;

import ch.creasystem.tennis.shared.player.Player;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
  public LoginInfo login(String requestUri);

  public Player getPlayerLoggedIn() throws NotLoggedInException, NotAuthorizedException;
}
