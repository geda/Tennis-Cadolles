package ch.creasystem.tennis.server.mail;

import java.util.Properties;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import ch.creasystem.tennis.shared.match.Match;
import ch.creasystem.tennis.shared.player.Player;

public class EmailHelper {
	private static Logger LOG = Logger.getLogger(EmailHelper.class.getName());

	public static final boolean sendNotificationMail(Player loggedInPlayer,
			Match match, TreeMap<Long, Player> playerMap, String classement) {

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(loggedInPlayer.getGoogleAccount(),
					loggedInPlayer.getNickName()));
			for (Player player : playerMap.values()) {
				if (player.getNotificationMail() != null) {
					msg.addRecipient(Message.RecipientType.TO,
							new InternetAddress(player.getNotificationMail(),
									player.getNickName()));
				}
			}
			msg.setSubject(ScoreTextHelper.getScoreResume(match, playerMap));
			MimeMultipart content = new MimeMultipart("matches");
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(ScoreTextHelper.getMatchDetails(match,
					loggedInPlayer.getNickName(), playerMap));
			content.addBodyPart(mbp1);

			MimeBodyPart attachment = new MimeBodyPart();
			// prepare attachment using a bytearraydatasource
			DataSource src = new ByteArrayDataSource(
					classement.getBytes("UTF8"), "text/csv");
			attachment.setFileName("Matches.csv");
			attachment.setDataHandler(new DataHandler(src));

			content.addBodyPart(attachment);
			msg.setContent(content);
			msg.saveChanges();

			Transport.send(msg);

		} catch (Exception e) {
			LOG.log(Level.SEVERE, "erreur lors de l'envoi d'email", e);
			return false;
		}
		return true;
	}
}
