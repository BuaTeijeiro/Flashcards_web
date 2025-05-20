package edu.badpals.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("/teacher/{id}")
    public String routeTeacher(@PathVariable long id){

        return "redirect:/decks/all/" +id;
    }

    @GetMapping("student/{id}")
    public String routeStudent(@PathVariable long id){
        return "studentProfile";
    }

}
