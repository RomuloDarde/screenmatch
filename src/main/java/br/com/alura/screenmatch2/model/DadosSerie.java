package br.com.alura.screenmatch2.model;

import com.google.gson.annotations.SerializedName;

public record DadosSerie(
        @SerializedName("Title") String titulo,
        @SerializedName("Year") String ano,
        @SerializedName("Runtime") String duracao,
        @SerializedName("Genre") String genero,
        @SerializedName("Actors") String atores,
        @SerializedName("Plot") String sinpose,
        @SerializedName("Awards") String premios,
        @SerializedName("Poster") String poster,
        @SerializedName("imdbRating") String avaliacao,
        @SerializedName("totalSeasons") String totalTemporadas) {

}