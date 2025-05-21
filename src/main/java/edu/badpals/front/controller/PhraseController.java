package edu.badpals.front.controller;

import edu.badpals.front.dto.CategoryDto;
import edu.badpals.front.dto.PhraseDto;
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

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/phrases/")
public class PhraseController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/detail/{id}")
    public String getPattern(@PathVariable long id, Model model){
        ResponseEntity<PhraseDto> response = restTemplate.exchange(
                "http://localhost:8081/phrases/detail/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PhraseDto>() {}
        );
        PhraseDto phrase = response.getBody();
        model.addAttribute("phrase", phrase);

        ResponseEntity<List<CategoryDto>> responseCategories = restTemplate.exchange(
                "http://localhost:8081/categories/all/" +  MainMenuController.HARDCODED_USER,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CategoryDto>>() {}
        );
        List<CategoryDto> categories = responseCategories.getBody();
        model.addAttribute("phrase", phrase);
        model.addAttribute("user",MainMenuController.HARDCODED_USER);
        model.addAttribute("categories", categories);

        return "phraseDetail";
    }
}
