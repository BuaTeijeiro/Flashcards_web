package edu.badpals.front.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainMenuController {

    public static final String USER = "user";
    public static final String HARDCODED_USER = "1";

    @GetMapping("/")
    public String showLogging(HttpSession session){
        session.setAttribute(USER,1);
        return "menu";
    }

    @GetMapping("/menuPrincipal")
    public String showMenu(){
        return "redirect:/";
    }
}
