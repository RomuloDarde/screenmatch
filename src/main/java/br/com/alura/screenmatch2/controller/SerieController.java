package br.com.alura.screenmatch2.controller;

import br.com.alura.screenmatch2.dto.EpisodioDTO;
import br.com.alura.screenmatch2.dto.SerieDTO;
import br.com.alura.screenmatch2.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/series")
public class SerieController {

    //Atributos
    @Autowired
    private SerieService servico;

    //Métodos GET
    @GetMapping
    public List<SerieDTO> obterTodasSeries() {
        return servico.buscarTodasSeries();
    }

    @GetMapping (path = "/top5")
    public List<SerieDTO> top5Series() {
        return servico.top5Series();
    }

    @GetMapping (path = "/lancamentos")
    public List<SerieDTO> ultimosLancamentos() {
        return servico.ultimosLancamentos();
    }

    @GetMapping (path = "/{id}")
    public SerieDTO buscarSeriePorId(@PathVariable Long id) {
        return servico.buscarSeriePorId(id);
    }

    @GetMapping (path = "/{id}/temporadas/todas")
    public List<EpisodioDTO> buscarTodasTemporadas(@PathVariable Long id) {
        return servico.buscarTodasTemporadas(id);
    }

    @GetMapping (path = "/{id}/temporadas/{numero}")
    public List<EpisodioDTO> buscarTemporadasPorNumero(@PathVariable Long id, @PathVariable Integer numero) {
        return servico.buscarTemporadasPorNumero(id, numero);
    }

    @GetMapping (path = "/categoria/{genero}")
    public List<SerieDTO> buscarSeriesPorGenero(@PathVariable String genero) {
        return servico.buscarSeriesPorGenero(genero);
    }





}
