package com.zzzdream.springreserve.util;

import com.zzzdream.springreserve.config.AppProperties;
import com.zzzdream.springreserve.model.auth.User;
import com.zzzdream.springreserve.model.mailMessages.MailMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import java.util.Properties;

@Component
public class MailSender  {
    private final JavaMailSender mailSender;
    private final AppProperties appProperties;
    public MailSender(AppProperties appProperties) {
        this.appProperties = appProperties;
        mailSender = getJavaMailSender();
    }
    public void sendMessage(MailMessage mailMessage) {
        MimeMessagePreparator preparator = mimeMessage -> {
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mailMessage.getTo()));
            mimeMessage.setFrom(new InternetAddress(appProperties.getMail().getUsername()));
            mimeMessage.setSubject(mailMessage.getSubject());
            mimeMessage.setContent(mailMessage.getText(), "text/html");
        };


        try {
            mailSender.send(preparator);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }

    public JavaMailSender getJavaMailSender() {
        if (mailSender != null) {
            return mailSender;
        }
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername(appProperties.getMail().getUsername());
        mailSender.setPassword(appProperties.getMail().getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");

        return mailSender;
    }


}