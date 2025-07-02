package dev.lab.screensound.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "musicas")
public class Musica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true, nullable = false)
	private String nome;
	private String album;
	@ManyToOne(fetch = FetchType.EAGER)
	private Artista artista;
	
	public Musica() {}
	
	public Musica(String nome, String album, Artista artista) {
		this.nome = nome;
		this.album = album;
		this.artista = artista;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public Artista getArtista() {
		return artista;
	}
	
	@Override
	public String toString() {
		return "musica: " + nome + "\n" +
			   "album: " + album + "\n" +
			   "artista: " + artista.getNome() + "\n";
	}
	
	public void setArtista(Artista artista) {
		this.artista = artista;
	}
}
