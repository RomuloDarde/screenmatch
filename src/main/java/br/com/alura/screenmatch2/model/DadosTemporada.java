package br.com.alura.screenmatch2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public record DadosTemporada(
        @SerializedName("Season") String temporada,
        @SerializedName("Episodes") List<DadosEpisodio> dadosEpisodios) {
}
