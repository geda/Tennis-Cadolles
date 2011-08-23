package ch.creasystem.tennis.client.ranking;

import java.util.ArrayList;

import ch.creasystem.tennis.shared.ranking.PlayerRanking;
import ch.creasystem.tennis.shared.ranking.ResultRankingList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RankingServiceAsync {

	void getPlayerRanking(Integer saison, AsyncCallback<ArrayList<PlayerRanking>> callback);

	void getResultRanking(Integer saison,
			AsyncCallback<ResultRankingList> callback);

}
