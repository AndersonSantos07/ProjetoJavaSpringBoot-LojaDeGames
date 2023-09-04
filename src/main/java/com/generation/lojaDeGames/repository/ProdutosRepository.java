package com.generation.lojaDeGames.repository;

import com.generation.lojaDeGames.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.lojaDeGames.model.Produtos;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutosRepository extends JpaRepository<Produtos,Long> {

    public List<Produtos> findAllByGameContainingIgnoreCase(@Param("game") String game);

    public List<Produtos> findAllByGeneroContainingIgnoreCase(@Param("genero") String ggenero);




}
