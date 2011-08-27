package ch.creasystem.tennis.server.mail;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.TreeMap;

import ch.creasystem.tennis.shared.match.Match;
import ch.creasystem.tennis.shared.player.Player;

public class ScoreTextHelper {
	private static final String[] ecrase = new String[] { "écrasé", "éclaté", "atomisé", "battu de justesse", "battu",
			"gagné à la limite du fair-play", "mal joué mais quand même battu", "survolé les débats en battant",
			"ridiculisé", "battu à l'arrache", "montré sa détermination en battant", "enfin réussi à battre",
			"joué comme une m. mais quand-même battu" };
	private static final String[] coups = new String[] { "bosse ton coup droit", "travaille ton service", "améliore ton revert", "frappe tes volées",
			"bosse tes amorties", "change ta coupe de cheveux", "arrête avect ton petit bras" };

	public static String getScoreResume(Match match, TreeMap<Long, Player> playerMap) {

		StringBuilder sb = new StringBuilder();
		if (match.isDoubleMatch()) {
			sb.append(playerMap.get(match.getWinner().getId()).getNickName());
			sb.append(" et ");
			sb.append(playerMap.get(match.getWinner2().getId()).getNickName());
			sb.append(" ont ");
			Random rando = new Random(ecrase.length);
			sb.append(ecrase[rando.nextInt()]);
			sb.append(" ");
			sb.append(playerMap.get(match.getLooser().getId()).getNickName());
			sb.append(" et ");
			sb.append(playerMap.get(match.getLooser2().getId()).getNickName());
			sb.append(" sur le score ");
			sb.append(match.getScore());
		} else {
			sb.append(playerMap.get(match.getWinner().getId()).getNickName());
			sb.append(" a ");
			Random rando = new Random();
			sb.append(ecrase[rando.nextInt(ecrase.length-1)]);
			sb.append(" ");
			sb.append(playerMap.get(match.getLooser().getId()).getNickName());
			sb.append(" sur le score ");
			sb.append(match.getScore());
		}
		return sb.toString();
	}
	
	public static String getMatchDetails(Match match, String nickname, TreeMap<Long, Player> playerMap) {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		StringBuilder sb = new StringBuilder();
		sb.append("Salut les filles,\n\nVoici encore quelques détails croustillants:\n");
		sb.append("Date du match: ");
		sb.append(df.format(match.getDate()));
		sb.append("\n");
		sb.append("Score: ");
		sb.append(match.getScore());
		sb.append("\n");
		sb.append("Valeur match: ");
		sb.append(match.getValeurMatch());
		sb.append("\n");
		sb.append("Points: ");
		sb.append(match.getPoint());
		sb.append("\n");
		sb.append("Commentaire: ");
		sb.append(match.getCommentaire());
		sb.append("\n\n");

		sb.append("Hey ");
		sb.append(playerMap.get(match.getLooser().getId()).getNickName());
		if (match.isDoubleMatch()) {
			sb.append(" et ");
			sb.append(playerMap.get(match.getLooser2().getId()).getNickName());
		}
		sb.append(", pour le prochain match, ");
		Random rando = new Random();
		sb.append(coups[rando.nextInt(coups.length-1)]);
		sb.append(".\n\n");
		sb.append("Bien amicalement\n");
		sb.append(nickname);
		return sb.toString();
	}
}
