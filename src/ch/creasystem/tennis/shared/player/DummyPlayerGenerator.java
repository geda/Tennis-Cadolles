package ch.creasystem.tennis.shared.player;

import java.util.ArrayList;


public class DummyPlayerGenerator {

	public static ArrayList <Player> generatePlayers () {
		ArrayList<Player> playerList = new ArrayList<Player>();
		
		
		playerList.add(new Player("David","Gerber", "Kuba", "14.02.1973", "a@b.com", null));
		playerList.add(new Player("Patrick", "Kocher", "Hakin", "18.08.1972", "a@b.com", null));
		playerList.add(new Player("Gaetan", "Bernier", "Amélie", "12.08.1978", "a@b.com", null));
		playerList.add(new Player("Juan","Lopez", "Aranxa", "20.01.1972", "a@b.com", null));
		return playerList;
	}
}
