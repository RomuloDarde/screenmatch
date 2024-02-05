package br.com.alura.screenmatch2.util;

import br.com.alura.screenmatch2.model.Episodio;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MetodosQueRetornamEstatisticas {
    MetodosQueRetornamListas metodosListas = new MetodosQueRetornamListas();

    //Métodos que retornam estatísticas, utlizando a lista de episódios gerada na outra classe
    public Map<Integer, Double> avaliacoesPorTemporada(String nomeSerie) {
        List<Episodio> episodios = metodosListas.listaEspisodios(nomeSerie);

        return episodios.stream()
                .filter(e -> e.getNotaImdb() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getNotaImdb)));
    }

    public DoubleSummaryStatistics estatisticasAvaliacoes(String nomeSerie) {
        List<Episodio> episodios = metodosListas.listaEspisodios(nomeSerie);

        return episodios.stream()
                .filter(e -> e.getNotaImdb() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getNotaImdb));
    }
}
