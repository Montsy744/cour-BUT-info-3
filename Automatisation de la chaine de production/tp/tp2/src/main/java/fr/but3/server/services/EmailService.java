package fr.but3.server.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  private JavaMailSender mailSender;

  public void setMailSender(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void sendEmail(String to, String subject, String text) throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);

    helper.setFrom("no.reply.dolce.vita@gmail.com");
    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(text);

    mailSender.send(message);
  }
}
