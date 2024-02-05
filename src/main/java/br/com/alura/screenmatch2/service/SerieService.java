package br.com.alura.screenmatch2.service;

import br.com.alura.screenmatch2.dto.EpisodioDTO;
import br.com.alura.screenmatch2.dto.SerieDTO;
import br.com.alura.screenmatch2.model.Serie;
import br.com.alura.screenmatch2.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    //Atributos
    @Autowired
    private SerieRepository repositorio;


    //Métodos GET
    public List<SerieDTO> buscarTodasSeries() {
        List<Serie> series =  repositorio.findAll();
        return converterListaSeriesParaListaSeriesDTO(series);
    }

    public List<SerieDTO> top5Series() {
        List<Serie> top5Series =  repositorio.findTop5ByOrderByAvaliacaoDesc();
        return converterListaSeriesParaListaSeriesDTO(top5Series);
    }

    public List <SerieDTO> ultimosLancamentos() {
        List<Serie> ultimosLancamentos = repositorio.encontrarEpisodiosMaisRecentes();
        return converterListaSeriesParaListaSeriesDTO(ultimosLancamentos);
    }

    public SerieDTO buscarSeriePorId(Long id) {
        Optional<Serie> optionalSerie = repositorio.findById(id);
        if (optionalSerie.isPresent()) {
            var serie =  optionalSerie.get();
            var serieDTO = new SerieDTO(serie.getId(), serie.getTitulo(), serie.getAno(), serie.getDuracao(),
                    serie.getGenero(), serie.getAtores(), serie.getSinopse(), serie.getPremios(),
                    serie.getPoster(), serie.getAvaliacao(), serie.getTotalTemporadas());
            return serieDTO;
        } else throw new RuntimeException("Id buscado está incorreto.");
    }

    public List<EpisodioDTO> buscarTodasTemporadas(Long id) {
        var serie = converterOptionalSerieEmSerie(id);
        List<EpisodioDTO> episodiosDTO= serie.getEpisodios().stream()
                .map(e -> new EpisodioDTO(e.getTitulo(), e.getTemporada(), e.getNumeroEpisodio()))
                .collect(Collectors.toList());
        return episodiosDTO;
    }

    public List<EpisodioDTO> buscarTemporadasPorNumero(Long id, Integer numero) {
        var serie = converterOptionalSerieEmSerie(id);
        List<EpisodioDTO> episodiosDTO= serie.getEpisodios().stream()
                .filter(e -> e.getTemporada() == numero)
                .map(e -> new EpisodioDTO(e.getTitulo(), e.getTemporada(), e.getNumeroEpisodio()))
                .collect(Collectors.toList());
        return episodiosDTO;
    }

    public List<SerieDTO> buscarSeriesPorGenero(String genero) {
        List<Serie> series =  repositorio.findAll().stream()
                .filter(s -> s.getGenero().getCategoriaPortugues().equalsIgnoreCase(genero))
                .collect(Collectors.toList());
        return converterListaSeriesParaListaSeriesDTO(series);
    }

    //Método de conversão
    private List<SerieDTO> converterListaSeriesParaListaSeriesDTO(List<Serie> series) {
        return series.stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getAno(), s.getDuracao(),
                        s.getGenero(), s.getAtores(), s.getSinopse(), s.getPremios(),
                        s.getPoster(), s.getAvaliacao(), s.getTotalTemporadas()))
                .collect(Collectors.toList());
    }

    private Serie converterOptionalSerieEmSerie(Long id) {
        Optional<Serie> optionalSerie = repositorio.findById(id);
        if (optionalSerie.isPresent()) {
            return optionalSerie.get();
        } else throw new RuntimeException("Id buscado está incorreto.");
    }

}