package br.com.alura.screenmatch2.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

@Entity
@Table (name = "episodios")
public class Episodio {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer temporada;
    private Integer numeroEpisodio;
    private String titulo;
    private LocalDate datalancamento;
    private Double notaImdb;
    private String tituloSerie;

    @ManyToOne
    @JoinColumn (name = "serie_id")
    private Serie serie;


    //Construtores
    public Episodio() {}

    public Episodio(String temporada, DadosEpisodio dadosEpisodio) {
        this.temporada = Integer.parseInt(temporada);
        this.numeroEpisodio = Integer.parseInt(dadosEpisodio.numeroEpisodio());
        this.titulo = dadosEpisodio.titulo();

        try {
            this.datalancamento = LocalDate.parse(dadosEpisodio.datalancamento());
        } catch (DateTimeParseException exception) {
            this.datalancamento = null;
        }

        try {
            this.notaImdb = Double.parseDouble(dadosEpisodio.notaImdb());
        } catch (NumberFormatException exception) {
            this.notaImdb = 0.0;
        }

    }


    //Getters e Setters
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Integer getTemporada() {return temporada;}
    public void setTemporada(Integer temporada) {this.temporada = temporada;}
    public Integer getNumeroEpisodio() {return numeroEpisodio;}
    public void setNumeroEpisodio(Integer numeroEpisodio) {this.numeroEpisodio = numeroEpisodio;}
    public String getTitulo() {return titulo;}
    public void setTitulo(String titulo) {this.titulo = titulo;}
    public LocalDate getDatalancamento() {return datalancamento;}
    public void setDatalancamento(LocalDate datalancamento) {this.datalancamento = datalancamento;}
    public Double getNotaImdb() {return notaImdb;}
    public void setNotaImdb(Double notaImdb) {this.notaImdb = notaImdb;}
    public Serie getSerie() {return serie;}
    public void setSerie(Serie serie) {this.serie = serie;}
    public String getTituloSerie() {return tituloSerie;}
    public void setTituloSerie(String tituloSerie) {this.tituloSerie = tituloSerie;}


    //Equals e HashCode do Id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Episodio episodio = (Episodio) o;
        return Objects.equals(id, episodio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    //toString
    @Override
    public String toString() {
        return "Série: " + serie.getTitulo() + " - Temporada: " + temporada + " - Episodio: " + numeroEpisodio +
                " - Título: " + titulo + " - Nota Imdb: " + notaImdb;
    }
}


