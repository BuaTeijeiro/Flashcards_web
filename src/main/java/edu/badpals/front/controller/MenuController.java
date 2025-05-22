package edu.badpals.front.controller;

import edu.badpals.front.dto.UserDto;
import edu.badpals.front.mysc.Constants;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class MenuController {

    @GetMapping("/")
    public String showLogging(){
       return "redirect:/menu";
    }

    @GetMapping("/menu")
    public String showMenu(HttpSession session, Model model){
        UserDto userDto = (UserDto) session.getAttribute(Constants.LOGGED_USER);
        if (userDto.getUser_type() == 1){
            model.addAttribute("userName", userDto.getUsername());
            model.addAttribute("userId", userDto.getId());
            return "menu";
        } else {
            model.addAttribute("userName", userDto.getUsername());
            return "studentProfile";
        }

    }
}
