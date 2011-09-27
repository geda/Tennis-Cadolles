package ch.creasystem.tennis.server.rest;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import ch.creasystem.tennis.server.rest.impl.MatchControllerImpl;
import ch.creasystem.tennis.server.rest.impl.MatchListControllerImpl;
import ch.creasystem.tennis.server.rest.impl.RankingControllerImpl;

public class RestletDispatch extends Application {
	@Override
	public synchronized Restlet createInboundRoot() {
		final Router router = new Router(getContext());
		router.attach("/ranking", RankingControllerImpl.class);
		router.attach("/matchlist", MatchListControllerImpl.class);
		router.attach("/match/{match}", MatchControllerImpl.class);
		return router;
	}
}
