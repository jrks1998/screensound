package dev.lab.screensound.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.lab.screensound.model.Artista;
import dev.lab.screensound.model.Musica;

public interface MusicaRepository extends JpaRepository<Musica, Long> {
	@Query("SELECT m FROM Musica m WHERE m.artista = :artista")
	public List<Musica> musicasPorArtista(Artista artista);
}
