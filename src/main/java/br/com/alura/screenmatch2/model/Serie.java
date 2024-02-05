package br.com.alura.screenmatch2.model;

import br.com.alura.screenmatch2.service.ConsultaChatGPT;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;

@Entity
@Table (name = "series")   //Necessário para alterar no nome da tabela.
public class Serie {

    //Atributos
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (unique = true, nullable = false) //Necessário para adicionar propriedades a coluna.
    private String titulo;
    private String ano;
    private String duracao;
    @Enumerated (EnumType.STRING) //Necessário para enums, marca o tipo do enum como String e não sequencial.--´-
    private Categoria genero;
    private String atores;
    private String sinopse;
    private String premios;
    private String poster;
    private Double avaliacao;
    private Integer totalTemporadas;

    @OneToMany (mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episodio> episodios = new ArrayList<>();


    //Construtores
    public Serie() {}

    public Serie(DadosSerie dadosSerie) {
        this.titulo = dadosSerie.titulo();
        this.ano = dadosSerie.ano();
        this.duracao = dadosSerie.duracao();

        String generos[] = dadosSerie.genero().split(",");
        this.genero = Categoria.fromString(generos[0]);

        this.atores = dadosSerie.atores();
        this.sinopse = ConsultaChatGPT.obterTraducao(dadosSerie.sinpose()).replaceAll("\n", "");
        this.premios = dadosSerie.premios();
        this.poster = dadosSerie.poster();
        this.avaliacao = OptionalDouble.of(Double.parseDouble(dadosSerie.avaliacao())).orElse(0.0);
        this.totalTemporadas = Integer.parseInt(dadosSerie.totalTemporadas());
    }

    //Getters e Setters
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getTitulo() {return titulo;}
    public void setTitulo(String titulo) {this.titulo = titulo;}
    public String getAno() {return ano;}
    public void setAno(String ano) {this.ano = ano;}
    public String getDuracao() {return duracao;}
    public void setDuracao(String duracao) {this.duracao = duracao;}
    public Categoria getGenero() {return genero;}
    public void setGenero(Categoria genero) {this.genero = genero;}
    public String getAtores() {return atores;}
    public void setAtores(String atores) {this.atores = atores;}
    public String getSinopse() {return sinopse;}
    public void setSinopse(String sinopse) {this.sinopse = sinopse;}
    public String getPremios() {return premios;}
    public void setPremios(String premios) {this.premios = premios;}
    public String getPoster() {return poster;}
    public void setPoster(String poster) {this.poster = poster;}
    public Double getAvaliacao() {return avaliacao;}
    public void setAvaliacao(Double avaliacao) {this.avaliacao = avaliacao;}
    public Integer getTotalTemporadas() {return totalTemporadas;}
    public void setTotalTemporadas(Integer totalTemporadas) {this.totalTemporadas = totalTemporadas;}
    public List<Episodio> getEpisodios() {return episodios;}
    public void setEpisodios(List<Episodio> episodios) {
        episodios.forEach(e -> e.setSerie(this));
        episodios.forEach(e -> e.setTituloSerie(this.getTitulo()));
        this.episodios = episodios;
    }

    //Equals e HashCode Id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Serie serie = (Serie) o;
        return Objects.equals(id, serie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //ToString
    @Override
    public String toString() {
        return "Gênero: " + genero + " - Título: " + titulo + " - Ano: " + ano + " - Duração: " + duracao +
                " - Atores: " + atores + " - Sinopse: " + sinopse + " - Prêmios: " + premios +
                " - Imagem da Capa: " + poster + " - Nota Imdb: " + avaliacao +
                " - Total de Temporadas: " + totalTemporadas + " - Id: " + id +
                " - Episódios: " + episodios;

    }
}

