package br.com.alura.screenmatch2.util;

import br.com.alura.screenmatch2.main.Principal;
import br.com.alura.screenmatch2.model.DadosTemporada;
import br.com.alura.screenmatch2.model.Episodio;
import br.com.alura.screenmatch2.service.ConsumoAPI;
import br.com.alura.screenmatch2.service.ConverteJson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MetodosQueRetornamListas {

    //Atributos
    private final String ENDERECO_FIXO = "https://www.omdbapi.com/?apikey=acc2c8e0&t=";


    //Método que gera uma lista de temporadas, que em cada temporada consta uma lista de episódios
    public List<DadosTemporada> listaEpisodiosPorTemporada(String nomeSerie) {
        String enderecoTemporada = ENDERECO_FIXO +
                nomeSerie.toLowerCase().replace(' ', '+') + "&season=";
        var serie = new Principal().obterSerie(nomeSerie);
        List <DadosTemporada> episodiosPorTemporada = new ArrayList<>();

        for (int i = 1; i <= serie.getTotalTemporadas(); i++) {
            String jsonTemporada = ConsumoAPI.obterDados(enderecoTemporada + i);
            episodiosPorTemporada.add(ConverteJson.obterDados(jsonTemporada, DadosTemporada.class));
        }
        return episodiosPorTemporada;
    }

    //Método que gera uma lista de episódios a partir da lista de temporadas
    public List<Episodio> listaEspisodios (String nomeSerie) {
        List <DadosTemporada> dadosTemporadas = listaEpisodiosPorTemporada(nomeSerie);
        return dadosTemporadas.stream()
                .flatMap(t -> t.dadosEpisodios().stream()
                        .map(d -> new Episodio(t.temporada(), d))).toList();
    }

    //Métodos que utilizam a lista de episódios gerada
    public List<String> listaTitulosEpisodios (String nomeSerie) {
        List<Episodio> episodios = listaEspisodios(nomeSerie);
        return episodios.stream()
                .map(e -> e.getTitulo())
                .toList();
    }

    public List<Episodio> top10Episodios (String nomeSerie) {
        List<Episodio> episodios = listaEspisodios(nomeSerie);
        return episodios.stream()
                .sorted(Comparator.comparing(Episodio::getNotaImdb).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Episodio> buscaEpisodiosPorData (String nomeSerie, Integer ano) {
        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
        List<Episodio> episodios = listaEspisodios(nomeSerie);
        return episodios.stream()
                .filter(e -> e.getDatalancamento() != null && e.getDatalancamento().isAfter(dataBusca))
                .collect(Collectors.toList());
    }

    public List<Episodio> buscaEpisodioPorTitulo (String nomeSerie, String trecho) {
        List<Episodio> episodios = listaEspisodios(nomeSerie);
        return episodios.stream()
                .filter(e -> e.getTitulo().toLowerCase().contains(trecho.toLowerCase()))
                .collect(Collectors.toList());
    }


}
