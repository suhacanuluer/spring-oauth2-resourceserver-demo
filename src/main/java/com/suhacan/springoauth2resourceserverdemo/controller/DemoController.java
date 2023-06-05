package com.suhacan.springoauth2resourceserverdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;


@RestController
@RequestMapping()
public class DemoController {

    @GetMapping("/")
    public String index(@AuthenticationPrincipal Jwt jwt) {
        return String.format("Hello, %s!", jwt.getSubject());
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }

    @GetMapping("/error")
    public String error(HttpServletRequest request) {
        String message = (String) request.getSession().getAttribute("error.message");
        request.getSession().removeAttribute("error.message");
        return message;
    }

//    @GetMapping("/message")
//    public String message() {
//        return "secret message";
//    }
//
//    @PostMapping("/message")
//    public String createMessage(@RequestBody String message) {
//        return String.format("Message was created. Content: %s", message);
//    }
//
//    @GetMapping("/asli")
//    public String hiAsli() {
//        return "selam asli";
//    }
//
//    @GetMapping("/suha")
//    public String hiSuha() {
//        return "selam suha";
//    }

}
