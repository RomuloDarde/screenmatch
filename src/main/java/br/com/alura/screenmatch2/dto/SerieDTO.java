package br.com.alura.screenmatch2.dto;

import br.com.alura.screenmatch2.model.Categoria;

public record SerieDTO (
        Long id,
        String titulo,
        String ano,
        String duracao,
        Categoria genero,
        String atores,
        String sinopse,
        String premios,
        String poster,
        Double avaliacao,
        Integer totalTemporadas){


}
