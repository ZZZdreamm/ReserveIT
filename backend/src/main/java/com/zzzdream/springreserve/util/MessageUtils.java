package com.zzzdream.springreserve.util;

import com.zzzdream.springreserve.model.auth.User;
import com.zzzdream.springreserve.model.mailMessages.MailMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageUtils {

    private static String baseUrl;
    @Value("${app.baseUrl}")
    public void setBaseUrl(String baseUrl) {
        MessageUtils.baseUrl = baseUrl;
    }
    public static MailMessage getMailMessage(User userResult, String randomCode) {
        String message = "<h1>You have successfully registered!</h1><br>" +
                "<div><span style='padding: 10px;'>Thank you for registering with us. We are excited to have you on board." +
                " To be able to use your account just click this button!</span>" +
                "<a style='display: inline-block; padding: 10px; background-color: lightgreen; text-decoration: none; margin: 5px;" +
                " color: black; border-radius: 5px; cursor: pointer;'' href='" + baseUrl +
                "/auth/verify?userId=" + userResult.getId() + "&code=" + randomCode + "'>Activate account!</a></div>";
        return new MailMessage(userResult.getEmail(), "Registration", message);
    }
}
