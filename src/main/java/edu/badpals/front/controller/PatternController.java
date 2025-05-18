package edu.badpals.front.controller;

import edu.badpals.front.dto.CategoryDto;
import edu.badpals.front.dto.InflectionMode;
import edu.badpals.front.dto.PatternDto;
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

@Controller
@RequestMapping("/patterns/")
public class PatternController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/detail/{id}")
    public String getPattern(@PathVariable long id, Model model){
        ResponseEntity<PatternDto> response = restTemplate.exchange(
                "http://localhost:8081/patterns/detail/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PatternDto>() {}
        );
        PatternDto pattern = response.getBody();
        model.addAttribute("pattern", pattern);
        model.addAttribute("modos", InflectionMode.values());
        model.addAttribute("user",MainMenuController.HARDCODED_USER);

        return "patternDetail";
    }

    @GetMapping("/new/{id}")
    public String newCategory(Model model, @PathVariable long id){
        model.addAttribute("pattern", new PatternDto());
        model.addAttribute("category", id);


        return "patternDetail";
    }
}
