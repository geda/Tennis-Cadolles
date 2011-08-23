package ch.creasystem.tennis.server.login;

import java.util.logging.Level;
import java.util.logging.Logger;

import ch.creasystem.tennis.client.login.LoginInfo;
import ch.creasystem.tennis.client.login.LoginService;
import ch.creasystem.tennis.client.login.NotAuthorizedException;
import ch.creasystem.tennis.client.login.NotLoggedInException;
import ch.creasystem.tennis.client.player.PlayerService;
import ch.creasystem.tennis.server.player.PlayerServiceImpl;
import ch.creasystem.tennis.shared.player.Player;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

	private PlayerService playerService = new PlayerServiceImpl();
	private Logger LOG = Logger.getLogger(LoginServiceImpl.class.getName());

	public LoginInfo login(String requestUri) {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		LoginInfo loginInfo = new LoginInfo();

		if (user != null) {
			loginInfo.setLoggedIn(true);
			loginInfo.setEmailAddress(user.getEmail());
			loginInfo.setNickname(user.getNickname());
			loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
			loginInfo.setUserId(user.getUserId());
		} else {
			loginInfo.setLoggedIn(false);
			loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
		}
		return loginInfo;
	}

	@Override
	public Player getPlayerLoggedIn() throws NotLoggedInException, NotAuthorizedException {
		// todo send notification
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user == null) {
			LOG.log(Level.SEVERE, "L'utilisateur n'est pas loggé");
			throw new NotLoggedInException("L'utilisateur n'est pas loggé");
		}
		Player loggedPlayer = null;
		
		if (user.getEmail().equals("test@example.com")) {
			// pour le dev local
			loggedPlayer = playerService.getPlayerList().get(0);
			return loggedPlayer;
		}
		for (Player player :playerService.getPlayerList()) {
			if (player.getGoogleAccount().equals(user.getEmail())) {
				loggedPlayer = player;
				break;
			}
		}
		
		if (loggedPlayer == null) {
			LOG.log(Level.INFO, "L'utilisateur loggé n'est pas un joueur");
			throw new NotAuthorizedException("L'utilisateur "+user.getNickname()+" n'est pas accès à cette fonction");
		}
		
		return loggedPlayer;
	}
}