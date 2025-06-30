package dev.lab.screensound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.lab.screensound.repository.ArtistaRepository;
import dev.lab.screensound.repository.MusicaRepository;

@SpringBootApplication
public class ScreensoundmusicasApplication implements CommandLineRunner {
	@Autowired
	private ArtistaRepository repositorioArtista;
	@Autowired
	private MusicaRepository repositorioMusica;

	public static void main(String[] args) {
		SpringApplication.run(ScreensoundmusicasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorioArtista, repositorioMusica);
		principal.exibeMenu();
	}

}
