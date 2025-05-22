package edu.badpals.front.controller;

import edu.badpals.front.dto.CategoryDto;
import edu.badpals.front.dto.PhraseDto;
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
@RequestMapping("/manage/{user}/phrase")
public class PhraseController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/detail/{id}")
    public String getPattern(@PathVariable long user, @PathVariable long id, Model model){
        ResponseEntity<PhraseDto> response = restTemplate.exchange(
                "http://localhost:8081/phrases/detail/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PhraseDto>() {}
        );
        PhraseDto phrase = response.getBody();
        model.addAttribute("phrase", phrase);

        ResponseEntity<List<CategoryDto>> responseCategories = restTemplate.exchange(
                "http://localhost:8081/categories/all/" +  user,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CategoryDto>>() {}
        );
        List<CategoryDto> categories = responseCategories.getBody();
        model.addAttribute("phrase", phrase);
        model.addAttribute("user", user);
        model.addAttribute("categories", categories);

        return "phraseDetail";
    }
}
