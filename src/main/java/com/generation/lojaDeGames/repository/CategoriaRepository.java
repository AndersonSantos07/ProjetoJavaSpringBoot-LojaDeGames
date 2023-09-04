package com.generation.lojaDeGames.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.lojaDeGames.model.Categoria;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

    public List<Categoria> findAllByConsoleContainingIgnoreCase(@Param("console") String console);

    public List<Categoria> findAllByMidiaContainingIgnoreCase(@Param("midia") String midia);

}
