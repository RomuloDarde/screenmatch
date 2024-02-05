package br.com.alura.screenmatch2.repository;

import br.com.alura.screenmatch2.model.Categoria;
import br.com.alura.screenmatch2.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    //Tipo da Entidade que o repository irá representar: Serie
    //Tipo de identificador que será representado: Id > Long

    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);
    //Só pelo nome, a JPA já entende a implementação do método

    List<Serie> findByAtoresContainingIgnoreCase(String nomeAtor);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByAvaliacaoGreaterThanEqual(Double avaliacao);

    List<Serie> findByGenero(Categoria categoria);

    List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(Integer temporadas, Double avaliacao);

    @Query("SELECT s FROM Serie s " +
            "JOIN s.episodios e " +
            "GROUP BY s " +
            "ORDER BY MAX(e.datalancamento) DESC LIMIT 5")
    List<Serie> encontrarEpisodiosMaisRecentes();


}

