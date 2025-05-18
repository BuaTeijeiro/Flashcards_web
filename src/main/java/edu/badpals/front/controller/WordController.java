package edu.badpals.front.controller;

import edu.badpals.front.dto.PatternDto;
import edu.badpals.front.dto.WordDto;
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

import java.util.Map;

@Controller
@RequestMapping("/words/")
public class WordController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/detail/{id}")
    public String getPattern(@PathVariable long id, Model model){
        ResponseEntity<WordDto> response = restTemplate.exchange(
                "http://localhost:8081/words/detail/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<WordDto>() {}
        );
        WordDto word = response.getBody();
        model.addAttribute("word", word);
        model.addAttribute("user",MainMenuController.HARDCODED_USER);
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
}
