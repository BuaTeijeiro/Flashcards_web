package edu.badpals.front.controller;

import edu.badpals.front.dto.CategoryDto;
import edu.badpals.front.dto.WordDto;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage/{user}/words")
public class WordController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/detail/{id}")
    public String getWord(@PathVariable long user, @PathVariable long id, Model model, HttpSession session){
        ResponseEntity<WordDto> response = restTemplate.exchange(
                "http://localhost:8081/words/detail/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<WordDto>() {}
        );
        WordDto word = response.getBody();
        model.addAttribute("word", word);
        String language = (String) session.getAttribute(DeckController.DECK_LANGUAGE);
        ResponseEntity<List<CategoryDto>> response3 = restTemplate.exchange(
                "http://localhost:8081/categories/all/" + user + "?language=" + language ,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CategoryDto>>() {}
        );
        List<CategoryDto> categories = response3.getBody();
        model.addAttribute("categories", categories);
        model.addAttribute("deckId", 0);
        if (word.getCategory()!= null) {
            model.addAttribute("patterns", word.getCategory().getPatterns());
        } else {
            model.addAttribute("patterns" , new ArrayList<>());
        }
        model.addAttribute("user", user);
        ResponseEntity<Map<String,String>> response2 = restTemplate.exchange(
                "http://localhost:8081/words/full-inflected/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String,String>>() {}
        );
        Map<String,String> inflections = response2.getBody();
        model.addAttribute("inflections", inflections);

        return "wordDetail";
    }

    @GetMapping("/new/{id}")
    public String newWord(@PathVariable long user, @PathVariable long id, Model model){
        model.addAttribute("word", new WordDto());
        ResponseEntity<List<CategoryDto>> response3 = restTemplate.exchange(
                "http://localhost:8081/categories/all/" + user,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CategoryDto>>() {}
        );
        List<CategoryDto> categories = response3.getBody();
        model.addAttribute("categories", categories);
        model.addAttribute("deckId", id);
        if (categories.size() > 0)
            model.addAttribute("patterns", categories.get(0).getPatterns());
        model.addAttribute("user", user);
        model.addAttribute("inflections", new HashMap<>());

        return "wordDetail";
    }
}
