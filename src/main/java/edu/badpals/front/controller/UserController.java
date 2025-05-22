package edu.badpals.front.controller;

import edu.badpals.front.dto.CategoryDto;
import edu.badpals.front.dto.UserDto;
import edu.badpals.front.mysc.Constants;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute(Constants.MESSAGE, "");
        return "login";
    }

    @GetMapping("/logout")
    public String logut(HttpSession session){
        session.setAttribute(Constants.LOGGED_USER, null);
        return "redirect:/login";
    }

    @PostMapping("/login/submit")
    public String submitLogin(@ModelAttribute UserDto userDto, HttpSession session, Model model){
        try {
            UserDto loggedUser = restTemplate.postForObject("http://localhost:8081/users/login", userDto, UserDto.class);
            session.setAttribute(Constants.LOGGED_USER, loggedUser);
            return "redirect:/menu";
        } catch (HttpClientErrorException e){
            model.addAttribute("message", "Email o constraseña no válidos");
            return "login";
        }

    }
}
