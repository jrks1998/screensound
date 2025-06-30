package dev.lab.screensound.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.lab.screensound.model.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

}
