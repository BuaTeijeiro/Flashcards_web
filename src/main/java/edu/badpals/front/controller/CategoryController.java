package edu.badpals.front.controller;

import edu.badpals.front.dto.CategoryDto;
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
@RequestMapping("/categories/")
public class CategoryController {
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/all")
    public String getAllCategories(HttpSession session, Model model){
        ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(
                "http://localhost:8081/categories/all/" + MainMenuController.HARDCODED_USER,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CategoryDto>>() {}
        );
        List<CategoryDto> cattegories = response.getBody();
        model.addAttribute("categories", cattegories);
        model.addAttribute("user",MainMenuController.HARDCODED_USER);

        return "categories";
    }

    @GetMapping("/detail/{id}")
    public String getCategory(@PathVariable long id,Model model){
        ResponseEntity<CategoryDto> response = restTemplate.exchange(
                "http://localhost:8081/categories/detail/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CategoryDto>() {}
        );
        CategoryDto cattegory = response.getBody();
        model.addAttribute("category", cattegory);
        model.addAttribute("user",MainMenuController.HARDCODED_USER);

        return "categoryDetail";
    }


}
