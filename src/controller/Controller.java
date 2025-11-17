package br.univates.api_battle.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @PostMapping("/post")
    public String postPost() {
        return "MS Usuario operacional";
    }

    @GetMapping("/post")
    public String getPost() {
        return "MS Usuario operacional";
    }
}