package ch.creasystem.tennis.server.ranking;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import net.sf.jsr107cache.Cache;
import ch.creasystem.tennis.client.match.MatchService;
import ch.creasystem.tennis.client.player.PlayerService;
import ch.creasystem.tennis.client.ranking.RankingService;
import ch.creasystem.tennis.server.cache.TennisCache;
import ch.creasystem.tennis.server.match.MatchServiceImpl;
import ch.creasystem.tennis.server.player.PlayerServiceImpl;
import ch.creasystem.tennis.shared.match.Match;
import ch.creasystem.tennis.shared.player.Player;
import ch.creasystem.tennis.shared.ranking.OponentScore;
import ch.creasystem.tennis.shared.ranking.PlayerRanking;
import ch.creasystem.tennis.shared.ranking.RankingList;
import ch.creasystem.tennis.shared.ranking.ResultRankingList;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class RankingServiceImpl extends RemoteServiceServlet implements
RankingService {

	
	MatchService matchService = new MatchServiceImpl();
	PlayerService playerService = new PlayerServiceImpl();
	
	@Override
	public ArrayList<PlayerRanking> getPlayerRanking(Integer saison) throws Exception {
		return new ArrayList<PlayerRanking> ( getRankingList().getPlayerRankingMap().values());
	}
	

	@Override
	public ResultRankingList getResultRanking(Integer saison) throws Exception {
		
		RankingList rankingList = getRankingList();
		
		ResultRankingList result = new ResultRankingList();
		
		result.setPlayerRanking(new ArrayList<PlayerRanking> ( rankingList.getPlayerRankingMap().values()));
		result.setPlayerList(playerService.getPlayerList());
		return result;
	}
	
	private RankingList getRankingList() throws Exception {
		Cache cache = TennisCache.getInstance().getCache();
		RankingList rankingList = (RankingList) cache.get(TennisCache.PLAYER_RANKING_LIST);
		if (rankingList == null) {
			rankingList = this.calculateRanking(null);
			cache.put(TennisCache.PLAYER_RANKING_LIST, rankingList);
		}
		return rankingList;
	}
	
	private RankingList calculateRanking(Integer saison) throws Exception {
		RankingList rankingList = new RankingList();
		TreeMap<Long, Player> playerMap = new TreeMap<Long, Player>();
		rankingList.setPlayerMap(playerMap);
		
		for(Player player : playerService.getPlayerList()) {
			playerMap.put(player.getId(), player);
			PlayerRanking playerRanking =rankingList.getRankingForPlayer(player);
			for(Player player2 : playerService.getPlayerList()) {
				playerRanking.getScoreForOpponent(player2);	
			}
		}
		
		List<Match> matches = matchService.getMatchList(saison);
		for (Match match : matches) {
			// add Winner Point
			calculateRankingWinner(rankingList, match);

			// add Looser Match
			calculateRankingLooser(rankingList, match);
		}
		return rankingList;
	}

	private void calculateRankingLooser(RankingList rankingList, Match match) throws EntityNotFoundException {

		// lire le ranking du looser
		Long looserId = match.getLooser().getId();
		PlayerRanking looserRanking1 = rankingList.getRankingForPlayer(rankingList.getPlayerMap().get(looserId));
		
		// Ajouter le match avec z�ro points
		looserRanking1.addTotalMatches(match.getValeurMatch());
		looserRanking1.addTotalPoints(0f);

		// Ajouter le match gagnant � son opposant
		Player winner1 = rankingList.getPlayerMap().get(match.getWinner().getId());
		OponentScore scoreWinnner = looserRanking1.getScoreForOpponent(winner1);
		if (match.isDoubleMatch()) {
			scoreWinnner.addTotalMatches(match.getValeurMatch()/2);
			scoreWinnner.addTotalPoints(0f);
			
		} else {
			scoreWinnner.addTotalMatches(match.getValeurMatch());
			scoreWinnner.addTotalPoints(0f);
		}

		// idem pour le double
		if (match.isDoubleMatch()) {
			//Ajouter le match perdu du premier joueur a son 2e opposant au 2e joueur
			Player winner2 =rankingList.getPlayerMap().get(match.getWinner2().getId());
			
			OponentScore looser1winner2Score = looserRanking1.getScoreForOpponent(winner2);
			looser1winner2Score.addTotalMatches(match.getValeurMatch()/2);
			looser1winner2Score.addTotalPoints(0f);
			
			// Ajouter le match perdu au 2e joueur
			Long looserId2 = match.getLooser2().getId();
			PlayerRanking looserRanking2 = rankingList.getRankingForPlayer(rankingList.getPlayerMap().get(looserId2));
			looserRanking2.addTotalMatches(match.getValeurMatch());
			looserRanking2.addTotalPoints(0f);

			//Ajouter le match gagn� du 2e joueur a son 1e opposant
			OponentScore looser2winner1Score = looserRanking2.getScoreForOpponent(winner1);
			looser2winner1Score.addTotalMatches(match.getValeurMatch()/2);
			looser2winner1Score.addTotalPoints(0f);

			//Ajouter le match gagn� du 2e joueur a son 2e opposant 
			OponentScore looser2winner2Score = looserRanking2.getScoreForOpponent(winner2);
			looser2winner2Score.addTotalMatches(match.getValeurMatch()/2);
			looser2winner2Score.addTotalPoints(0f);
		}
	}

	private void calculateRankingWinner(RankingList rankingList, Match match) throws EntityNotFoundException {
		
		// lire le ranking du winner et ajouter le match gagn� au total des matches
		Long winnerId = match.getWinner().getId();
		PlayerRanking winnerRanking1 = rankingList.getRankingForPlayer(rankingList.getPlayerMap().get(winnerId));
		winnerRanking1.addTotalMatches(match.getValeurMatch());
		winnerRanking1.addTotalPoints(match.getPoint());

		// Ajouter le match gagn� � son opposant
		Player looser1 = rankingList.getPlayerMap().get(match.getLooser().getId());
		OponentScore score = winnerRanking1.getScoreForOpponent(looser1);
		
		if (match.isDoubleMatch()) {
			score.addTotalMatches(match.getValeurMatch()/2);
			score.addTotalPoints(match.getPoint()/2);
		} else {
			score.addTotalMatches(match.getValeurMatch());
			score.addTotalPoints(match.getPoint());
		}

		if (match.isDoubleMatch()) {
			//Ajouter le match gagn� du premier joueur a son 2e opposant au 2e joueur
			Player looser2 = rankingList.getPlayerMap().get(match.getLooser2().getId());
			
			OponentScore winner1Looser2Score = winnerRanking1.getScoreForOpponent(looser2);
			winner1Looser2Score.addTotalMatches(match.getValeurMatch()/2);
			winner1Looser2Score.addTotalPoints(match.getPoint()/2);
			
			// Ajouter le match gangn� au 2e joueur
			Long winnerId2 = match.getWinner2().getId();
			PlayerRanking winnerRanking2 = rankingList.getRankingForPlayer(rankingList.getPlayerMap().get(winnerId2));
			winnerRanking2.addTotalMatches(match.getValeurMatch());
			winnerRanking2.addTotalPoints(match.getPoint());

			//Ajouter le match gagn� du 2e joueur a son 1e opposant
			OponentScore winner2looser1Score = winnerRanking2.getScoreForOpponent(looser1);
			winner2looser1Score.addTotalMatches(match.getValeurMatch()/2);
			winner2looser1Score.addTotalPoints(match.getPoint()/2);

			//Ajouter le match gagn� du 2e joueur a son 2e opposant 
			OponentScore winner2looser2Score = winnerRanking2.getScoreForOpponent(looser2);
			winner2looser2Score.addTotalMatches(match.getValeurMatch()/2);
			winner2looser2Score.addTotalPoints(match.getPoint()/2);
		}
	}
}