package com.generation.lojaDeGames.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.generation.lojaDeGames.model.Categoria;
import com.generation.lojaDeGames.repository.CategoriaRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Categoria>> getAll(){

        return ResponseEntity.ok(categoriaRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getId(@PathVariable Long id){

        return categoriaRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("console/{console}")
    public ResponseEntity <List<Categoria>> getConsole(@PathVariable String console){

        return ResponseEntity.ok(categoriaRepository.findAllByConsoleContainingIgnoreCase(console));


    }

    @GetMapping("midia/{midia}")
    public ResponseEntity<List<Categoria>> getMidia(@PathVariable String midia){

        return ResponseEntity.ok(categoriaRepository.findAllByMidiaContainingIgnoreCase(midia));

    }


    @PostMapping
    public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriaRepository.save(categoria));
    }

    @PutMapping
    public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria){

        return categoriaRepository.findById(categoria.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(categoriaRepository.save(categoria)))
                        .orElse( ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){

        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if(categoria.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        categoriaRepository.deleteById(id);

    }


}
