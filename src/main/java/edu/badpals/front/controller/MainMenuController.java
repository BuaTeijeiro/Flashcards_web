package edu.badpals.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainMenuController {
    @GetMapping("/")
    public String showLogging(){
        return "menu";
    }

    @GetMapping("/menuPrincipal")
    public String showMenu(){
        return "redirect:/decks/";
    }
}
