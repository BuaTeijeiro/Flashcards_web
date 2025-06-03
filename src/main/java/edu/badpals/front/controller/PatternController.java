package edu.badpals.front.controller;

import edu.badpals.front.dto.InflectionMode;
import edu.badpals.front.dto.PatternDto;
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

@Controller
@RequestMapping("/manage/{user}/patterns")
public class PatternController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/detail/{id}")
    public String getPattern(@PathVariable long user, @PathVariable long id, Model model, HttpSession session){
        ResponseEntity<PatternDto> response = restTemplate.exchange(
                "http://localhost:8081/patterns/detail/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PatternDto>() {}
        );
        PatternDto pattern = response.getBody();
        Long categoryId = (Long) session.getAttribute(CategoryController.CATEGORY_ID);
        model.addAttribute("pattern", pattern);
        model.addAttribute("modos", InflectionMode.values());
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("user", user);

        return "patternDetail";
    }

    @GetMapping("/new/{idCategory}")
    public String newCategory(Model model, @PathVariable String user, @PathVariable long idCategory){
        model.addAttribute("pattern", new PatternDto());
        model.addAttribute("category", idCategory);
        model.addAttribute("categoryId", idCategory);
        model.addAttribute("user", user);
        return "patternDetail";
    }
}
