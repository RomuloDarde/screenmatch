package br.com.alura.screenmatch2.main;

import br.com.alura.screenmatch2.model.DadosSerie;
import br.com.alura.screenmatch2.model.Episodio;
import br.com.alura.screenmatch2.model.Serie;
import br.com.alura.screenmatch2.repository.SerieRepository;
import br.com.alura.screenmatch2.service.ConsumoAPI;
import br.com.alura.screenmatch2.service.ConverteJson;
import br.com.alura.screenmatch2.util.MetodosQueRetornamListas;

import java.util.*;

public class Principal {
    //Atributos
    private final String ENDERECO_FIXO = "https://www.omdbapi.com/?apikey=acc2c8e0&t=";
    private Scanner leitor = new Scanner(System.in);
    private SerieRepository repositorio;
    private List<Serie> series;
    private Serie serie;


    //Construtor recebendo um repositório
    public Principal(SerieRepository repositorio) {
        this.repositorio = repositorio;
    }


    //Construtor padrão
    public Principal() {}


    public void exibeMenu() {
        var opcao = 1;
        while (opcao != 0) {
            var menu = """
                \n-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
                1 - Buscar uma série e adicionar a lista.
                2 - Buscar os episódios de uma série e adicionar a lista.
                3 - Imprimir a lista de séries.
                
                0 - Sair.
                
                Digite a opção desejada: """;

            System.out.println(menu);
            opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome da série: ");
                    serie = obterSerie(leitor.nextLine());
                    System.out.println(serie);
                    repositorio.save(serie);
                    break;
                case 2:
                    imprimeListaSeries();
                    System.out.print("\nQual dessas séries você deseja buscar os episódios: ");
                    serie = adicionaEpisodiosASerieBuscada(leitor.nextLine());
                    repositorio.save(serie);
                    break;
                case 3:
                    imprimeListaSeries();
                    break;
                case 0:
                    System.out.println("Sistema finalizado.");
                    break;
                default:
                    System.out.println("Opção inválida.");

            }
        }

    }

    //Método que retorna uma série
    public Serie obterSerie (String nomeSerie) {
        String enderecoSerie = ENDERECO_FIXO + nomeSerie.toLowerCase().replace(' ', '+');
        String jsonSerie = ConsumoAPI.obterDados(enderecoSerie);
        var dadosSerie = ConverteJson.obterDados(jsonSerie, DadosSerie.class);
        return new Serie(dadosSerie);
    }

    //Métodos que imprime a Lista de Séries do Banco de Dados
    private void imprimeListaSeries() {
        series = repositorio.findAll();
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }

    //Método que retorna uma serie com os episodios adicionados
    private Serie adicionaEpisodiosASerieBuscada(String nomeSerie) {
        Optional<Serie> optionalSerie = series.stream()
                .filter(s -> s.getTitulo().toLowerCase().contains(nomeSerie.toLowerCase()))
                .findFirst();

        Serie serie = optionalSerie.get();

        if (optionalSerie.isPresent()) {
            List<Episodio> episodios = new MetodosQueRetornamListas().listaEspisodios(serie.getTitulo());
            serie.setEpisodios(episodios);
            return serie;
        } else throw new RuntimeException("Série não pertence a lista de séries.");
    }

}


