package br.com.alura.screenmatch2;


import br.com.alura.screenmatch2.main.Principal;
import br.com.alura.screenmatch2.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Screenmatch2ApplicationSemWeb implements CommandLineRunner {
	@Autowired
	private SerieRepository repositorio;

	public static void main(String[] args) {
		SpringApplication.run(Screenmatch2ApplicationSemWeb.class, args);
	}

	public void run(String... args) throws Exception {
        //new Principal(repositorio).exibeMenu();

		//repositorio.findAll().forEach(System.out::println);


		//repositorio.findByAtoresContainingIgnoreCase("Pascal").forEach(System.out::println);

		//repositorio.findTop5ByOrderByNotaImdbDesc().forEach
		// (e -> System.out.println(e.getTitulo() + " - " + e.getNotaImdb()));

		//repositorio.findByNotaImdbGreaterThanEqual(8.).forEach
		// (e -> System.out.println(e.getTitulo() + " - " + e.getNotaImdb()));

		//repositorio.findByGenero(Categoria.fromPortugues("comédia")).forEach
		//		(e -> System.out.println(e.getGenero() + " - " + e.getTitulo()));

//		repositorio.findByTotalTemporadasLessThanEqualAndNotaImdbGreaterThanEqual(3, 8.5)
//				.forEach(e -> System.out.println(e.getGenero() + " - " + e.getTitulo() + " - Número de Temporadas: "
//						+ e.getTotalTemporadas() + " - Nota IMDB: " + e.getNotaImdb()));
	}

}
