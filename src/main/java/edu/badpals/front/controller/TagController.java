package edu.badpals.front.controller;

import edu.badpals.front.dto.TagDto;
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
@RequestMapping("/manage/{user}/tags")
public class TagController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String getTags(@PathVariable long user, Model model){
        ResponseEntity<List<TagDto>> response = restTemplate.exchange(
                "http://localhost:8081/tags/all/" + user,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TagDto>>() {}
        );
        List<TagDto> tags = response.getBody();
        model.addAttribute("tags", tags);
        model.addAttribute("user", user);

        return "tags";
    }



}
