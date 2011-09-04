package ch.creasystem.tennis.server.cache;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

/**
 * Singleton Cache
 * 
 * @author gerberda
 * 
 */
public class TennisCache {
	public static final String PLAYER_RANKING_LIST = "PlayerRankingList";
	private static final Logger log = Logger.getLogger(TennisCache.class.getName());

	private static TennisCache instance;

	private Cache cache;

	private TennisCache() {
		try {

			CacheFactory cacheFactory = CacheManager.getInstance()
					.getCacheFactory();

			cache = cacheFactory.createCache(Collections.emptyMap());

		} catch (CacheException e) {

			log.log(Level.SEVERE, "error lors de la creation du cache", e);
		}
	}

	public static synchronized TennisCache getInstance() {

		if (instance == null) {
			instance = new TennisCache();
		}
		return instance;
	}

	public Cache getCache() {
		return cache;
	}
}
