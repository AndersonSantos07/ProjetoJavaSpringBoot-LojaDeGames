package com.generation.lojaDeGames.controller;

import com.generation.lojaDeGames.model.Produtos;
import com.generation.lojaDeGames.repository.ProdutosRepository;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutosController {

    @Autowired
    public ProdutosRepository produtosRepository;

    @GetMapping
    public ResponseEntity<List<Produtos>> getAll(){

        return ResponseEntity.ok(produtosRepository.findAll());

    }

    @GetMapping("games/{game}")
    public ResponseEntity<List<Produtos>> getAllGame(@PathVariable String game){

        return ResponseEntity.ok(produtosRepository.findAllByGameContainingIgnoreCase(game));

    }

    @GetMapping("genero/{genero}")
    public ResponseEntity <List<Produtos>> getAllGenero(@PathVariable String genero){

        return ResponseEntity.ok(produtosRepository.findAllByGeneroContainingIgnoreCase(genero));

    }



    @PostMapping
    public ResponseEntity<Produtos> post(@Valid @RequestBody Produtos produto){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtosRepository.save(produto));
    }

    @PutMapping
    public ResponseEntity<Produtos> put(@Valid @RequestBody Produtos produto){

        return produtosRepository.findById(produto.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(produtosRepository.save(produto)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        Optional<Produtos> produto = produtosRepository.findById(id);

        if(produto.isEmpty())
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        produtosRepository.deleteById(id);


    }




}
