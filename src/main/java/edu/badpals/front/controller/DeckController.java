package edu.badpals.front.controller;

import edu.badpals.front.dto.CategoryDto;
import edu.badpals.front.dto.DeckDto;
import edu.badpals.front.dto.DeckUserDto;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/decks/")
public class DeckController {



    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String showDecks(Model model){
        String id = MainMenuController.HARDCODED_USER;
        ResponseEntity<List<DeckDto>> response = restTemplate.exchange(
                "http://localhost:8081/decks/all/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DeckDto>>() {}
        );
        List<DeckDto> decks = response.getBody();
        model.addAttribute("decks", decks);
        model.addAttribute("ownerId", id);
        return "decks";
    }

    @GetMapping("/{id}")
    public String showDeck(Model model, @PathVariable long id){
        ResponseEntity<DeckDto> response = restTemplate.exchange(
                "http://localhost:8081/decks/data/"+id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<DeckDto>() {}
        );
        DeckDto deck = response.getBody();

        model.addAttribute("deck", deck);
        ResponseEntity<List<DeckUserDto>> responseUsers = restTemplate.exchange(
                "http://localhost:8081/decks/users/" + MainMenuController.HARDCODED_USER,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DeckUserDto>>() {}
        );
        List<DeckUserDto> users = new ArrayList<>();
        if (responseUsers.hasBody()){
            users = responseUsers.getBody();
        }
        model.addAttribute("users",users);
        model.addAttribute("owner",MainMenuController.HARDCODED_USER);
        return "deck";
    }

    @GetMapping("/new/{id}")
    public String newDeck(@PathVariable long id,  Model model){
        model.addAttribute("deck", new DeckDto());
        List<DeckUserDto> users = new ArrayList<>();
        model.addAttribute("users",users);
        model.addAttribute("owner",id);
        return "deck";
    }
}
