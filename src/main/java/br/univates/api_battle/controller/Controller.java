package br.univates.api_battle.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/post")
public class Controller {

    
    @Autowired
    private JdbcTemplate jdbc;

    @PostMapping
    public String postPost(@RequestBody Map<String, Object> body) {

    String nome = (String) body.get("nome");
    String comentario = (String) body.get("comentario");
    Integer curtidas = body.get("curtidas") != null ? (Integer) body.get("curtidas") : 0;

    String sql = "INSERT INTO posts (nome, data_hora, comentario, curtidas) " +
                 "VALUES (?, NOW(), ?, ?)";

    jdbc.update(sql, nome, comentario, curtidas);

    return "Post inserido com sucesso!";
}

    @GetMapping
    public List<Map<String, Object>> getPosts() {
        String sql = "SELECT * FROM posts ORDER BY id";
        return jdbc.queryForList(sql);
    }

    @GetMapping("/count")
    public Integer getCountPost() {
        String sql = "SELECT COUNT(*) FROM posts";
        return jdbc.queryForObject(sql, Integer.class);
    }

    @GetMapping("/id/{id}")
    public Map<String, Object> getPostByID(@PathVariable Integer id) {
        String sql = "SELECT * FROM posts WHERE id = ?";
        return jdbc.queryForMap(sql, id);
    }

    @GetMapping("/exp/{exp}")
    public List<Map<String, Object>> getPostByComentario(@PathVariable String exp) {
        String sql = "SELECT * FROM posts WHERE comentario ILIKE ?";
        return jdbc.queryForList(sql, "%" + exp + "%");
    }
}