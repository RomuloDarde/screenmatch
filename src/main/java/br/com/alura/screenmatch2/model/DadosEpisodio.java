package br.com.alura.screenmatch2.model;

import com.google.gson.annotations.SerializedName;

public record DadosEpisodio(
        @SerializedName("Episode") String numeroEpisodio,
        @SerializedName("Title") String titulo,
        @SerializedName("Released") String datalancamento,
        @SerializedName("imdbRating") String notaImdb) {
}
