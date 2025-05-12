package edu.badpals.front.controller;

import edu.badpals.front.dto.DeckDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/decks/")
public class DeckController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String showDecks(Model model){
        ResponseEntity<List<DeckDto>> response = restTemplate.exchange(
                "http://localhost:8081/decks/all/1",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DeckDto>>() {}
        );
        List<DeckDto> decks = response.getBody();
        model.addAttribute("decks", decks);
        return "decks";
    }
}
