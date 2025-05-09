package com.zzzdream.springreserve.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/oauth2")
public class OAuth2Controller {

    @Value("${app.frontendUrl}")
    private String frontendUrl;

    @GetMapping("/redirect")
    public ModelAndView handleRedirectToken(@RequestParam String token) {
        System.out.println("Token: " + token);
        return new ModelAndView("redirect:" + frontendUrl + "/oauth2/redirect?token=" + token);
    }
}
