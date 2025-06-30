package dev.lab.screensound.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "artistas")
public class Artista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	@Enumerated(EnumType.STRING)
	private TipoArtista tipo;
	@OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<Musica> musicas = new ArrayList<>();
	
	public Artista () {}
	
	public Artista(String nome, TipoArtista tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public TipoArtista getTipoArtista() {
		return tipo;
	}
	
	@Override
	public String toString() {
		return "nome: " + nome + "\n" +
				"tipo: " + tipo + "\n";
	}
	
	public void setMusicas(List<Musica> musicas) {
		musicas.forEach(m -> m.setArtista(this));
		this.musicas= musicas; 
	}
	
	public long getId() {
		return id;
	}
}
