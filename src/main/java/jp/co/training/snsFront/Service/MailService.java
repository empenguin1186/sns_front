package jp.co.training.snsFront.Service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Service
@Data
@ConfigurationProperties(prefix = "mail")
public class MailService {

    private String from;
    private String password;
    private String charset;
    private String encoding;
    private String host;
    private String port;
    private String starttls;
    private String auth;
    private String connectiontimeout;
    private String timeout;
    private String debug;

    public void send(String subject, String content, String email) {

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttls);

        props.put("mail.smtp.connectiontimeout", connectiontimeout);
        props.put("mail.smtp.timeout", timeout);

        props.put("mail.debug", debug);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            // Set From:
            message.setFrom(new InternetAddress(from, "SNS Application"));
            // Set ReplyTo:
            message.setReplyTo(new Address[]{new InternetAddress(from)});
            // Set To:
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject(subject, charset);
            message.setText(content, charset);
            message.setHeader("Content-Transfer-Encoding", encoding);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }
}
