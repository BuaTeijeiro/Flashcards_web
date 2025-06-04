package edu.badpals.front.controller;

import edu.badpals.front.dto.CategoryDto;
import edu.badpals.front.dto.PhraseDto;
import edu.badpals.front.dto.TagDto;
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

import java.util.List;

@Controller
@RequestMapping("/manage/{user}/phrases")
public class PhraseController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/detail/{id}")
    public String getPhrase(@PathVariable long user, @PathVariable long id, Model model, HttpSession session){
        ResponseEntity<PhraseDto> response = restTemplate.exchange(
                "http://localhost:8081/phrases/detail/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PhraseDto>() {}
        );
        PhraseDto phrase = response.getBody();
        model.addAttribute("phrase", phrase);
        String language = (String) session.getAttribute(DeckController.DECK_LANGUAGE);
        ResponseEntity<List<CategoryDto>> responseCategories = restTemplate.exchange(
                "http://localhost:8081/categories/all-by-language/" +  user + "?language=" + language,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CategoryDto>>() {}
        );
        List<CategoryDto> categories = responseCategories.getBody();
        Long deckId = (Long) session.getAttribute(DeckController.DECK_ID);
        model.addAttribute("deckId", deckId);
        model.addAttribute("phrase", phrase);
        model.addAttribute("user", user);
        model.addAttribute("categories", categories);

        ResponseEntity<List<TagDto>> responseTags = restTemplate.exchange(
                "http://localhost:8081/tags/all/" + user,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TagDto>>() {}
        );
        List<TagDto> tags = responseTags.getBody();
        model.addAttribute("tags", tags);

        return "phraseDetail";
    }
}
