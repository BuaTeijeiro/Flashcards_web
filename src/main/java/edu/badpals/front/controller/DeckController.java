package edu.badpals.front.controller;

import edu.badpals.front.dto.CategoryDto;
import edu.badpals.front.dto.DeckDto;
import edu.badpals.front.dto.DeckUserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/manage/{user}/decks")
public class DeckController {

    public static final String DECK_LANGUAGE = "deckLanguage";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/all")
    public String showDecks(@PathVariable long user, Model model, HttpSession session){
        ResponseEntity<List<DeckDto>> response = restTemplate.exchange(
                "http://localhost:8081/decks/all/" + user,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DeckDto>>() {}
        );
        List<DeckDto> decks = response.getBody();
        model.addAttribute("decks", decks);
        model.addAttribute("ownerId", user);
        return "decks";
    }

    @GetMapping("/{id}")
    public String showDeck(@PathVariable long user, Model model, @PathVariable long id, HttpSession session){
        ResponseEntity<DeckDto> response = restTemplate.exchange(
                "http://localhost:8081/decks/data/"+id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<DeckDto>() {}
        );
        DeckDto deck = response.getBody();
        String language = deck.getLanguage()!=null? deck.getLanguage() : "";
        session.setAttribute(DECK_LANGUAGE, language);
        ResponseEntity<List<CategoryDto>> responseCategories = restTemplate.exchange(
                "http://localhost:8081/categories/all/"+id + "?language=" +  language,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CategoryDto>>() {}
        );
        List<CategoryDto> categories = responseCategories.getBody();
        model.addAttribute("categories", categories);

        model.addAttribute("deck", deck);
        ResponseEntity<List<DeckUserDto>> responseUsers = restTemplate.exchange(
                "http://localhost:8081/decks/users/" + user,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DeckUserDto>>() {}
        );
        List<DeckUserDto> users = new ArrayList<>();
        if (responseUsers.hasBody()){
            users = responseUsers.getBody();
        }
        model.addAttribute("users",users);
        model.addAttribute("owner", user);
        return "deck";
    }

    @GetMapping("/new")
    public String newDeck(@PathVariable long user,  Model model){
        model.addAttribute("deck", new DeckDto());
        List<DeckUserDto> users = new ArrayList<>();
        model.addAttribute("users",users);
        model.addAttribute("owner",user);
        return "deck";
    }
}
