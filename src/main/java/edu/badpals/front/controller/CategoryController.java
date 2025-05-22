package edu.badpals.front.controller;

import edu.badpals.front.dto.CategoryDto;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
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
@RequestMapping("/manage/{user}/categories")
public class CategoryController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserController userController;

    @GetMapping("/all")
    public String getAllCategories(@PathVariable long user, Model model){
        ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(
                "http://localhost:8081/categories/all/" + user,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CategoryDto>>() {}
        );
        List<CategoryDto> categories = response.getBody();
        model.addAttribute("categories", categories);
        model.addAttribute("user", user);

        return "categories";
    }

    @GetMapping("/{id}")
    public String getCategory(@PathVariable long user, @PathVariable long id,Model model){
        ResponseEntity<CategoryDto> response = restTemplate.exchange(
                "http://localhost:8081/categories/detail/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CategoryDto>() {}
        );
        CategoryDto cattegory = response.getBody();
        model.addAttribute("category", cattegory);
        model.addAttribute("user", user);
        return "categoryDetail";
    }

    @GetMapping("/new")
    public String newCategory(@PathVariable long user, Model model){
        model.addAttribute("category", new CategoryDto());
        model.addAttribute("user",user);

        return "categoryDetail";
    }



}
