package ch.creasystem.tennis.client.ranking;

import java.util.ArrayList;

import ch.creasystem.tennis.shared.ranking.PlayerRanking;
import ch.creasystem.tennis.shared.ranking.ResultRankingList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ranking")
public interface RankingService extends RemoteService {
	public ArrayList<PlayerRanking> getPlayerRanking(Integer saison) throws Exception;
	
	public ResultRankingList getResultRanking(Integer saison) throws Exception;
}
