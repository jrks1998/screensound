package dev.lab.screensound;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import dev.lab.screensound.model.Artista;
import dev.lab.screensound.model.Musica;
import dev.lab.screensound.model.TipoArtista;
import dev.lab.screensound.repository.ArtistaRepository;
import dev.lab.screensound.repository.MusicaRepository;

public class Principal {
	private ArtistaRepository repositorioArtista;
	private MusicaRepository repositorioMusica;
	private Scanner scan = new Scanner(System.in);
	
	Principal() {}
	
	Principal(ArtistaRepository repositorioArtista, MusicaRepository repositorioMusica) {
		this.repositorioArtista = repositorioArtista;
		this.repositorioMusica = repositorioMusica;
	}
	
	public void exibeMenu() {
		int opc = -1;
		while (opc != 0) {
			System.out.println("SCREENSOUND MUSICAS");
			System.out.println("1 - Cadastrar artista");
			System.out.println("2 - Cadastrar musica");
			System.out.println("3 -Listar artistas cadastrados");
			System.out.println("4 - Listar musicas cadastradas");
			System.out.print("Informe uma opção: ");
			try {
				opc = scan.nextInt();
				scan.nextLine();
				switch (opc) {
					case 1:
						cadastrarArtista();
						break;
					case 2:
						cadastrarMusica();
						break;
					case 3:
//						listarArtistas();
						break;
					case 4:
//						listarMusicas();
						break;
					default:
						System.out.println("Opção inválida");
				}
			} catch(InputMismatchException  e) {
				System.out.println("Entrada inválida. Somente números são aceitos");
				opc = 0;
			}
		}
	}
	
	public void cadastrarArtista() {
		String nome, tipo;
		System.out.println("CASDASTRAR ARTISTA");
		System.out.print("Informe o nome do artista: ");
		nome = scan.nextLine().toUpperCase();
		System.out.print("Informe o tipo do artista (solo/dupla/banda): ");
		tipo = scan.nextLine().toUpperCase();
		TipoArtista tipoArtista = null;
		switch (tipo) {
			case "SOLO":
				tipoArtista = TipoArtista.fromString(tipo);
				break;
			case "DUPLA" :
				tipoArtista = TipoArtista.fromString(tipo);
				break;
			case "BANDA":
				tipoArtista = TipoArtista.fromString(tipo);
				break;
			default:
				System.out.println("tipo não aceito. apenas solo, dupla ou banda são tipos aceitos");
		}
		if (tipoArtista != null) {
			TipoArtista.fromString(tipo);
			repositorioArtista.save(new Artista(nome, tipoArtista));
		} else {
			System.out.println("Artista não cadastrado");
		}
	}
	
	private int escolherArtista(List<Artista> artistas) {
		int i, opc = -1;
		for (i = 0; i < artistas.size(); i++) {
			System.out.println(String.format("%d - %s", i, artistas.get(i)));
		}
		System.out.println(i + " - ARTISTA NÃO ENCONTRADO");
		System.out.print("Escolha uma opção: ");
		try {
			opc = scan.nextInt();
			scan.nextLine();
			while (i < 0 || i > artistas.size()) {
				System.out.println("Opção inválida");
			}
		} catch (InputMismatchException e) {
			System.out.println("Entrada inválida. Somente números são aceitos");
		}
		return opc;
	}
	
	private Artista menuEscolherArtista() {
		Artista artista = new Artista();
		List<Artista> artistas = repositorioArtista.findAll();
		System.out.println("SELECIONE O ARTISTA");
		int escolha = escolherArtista(artistas);
		if (escolha == artistas.size()) {
			cadastrarArtista();
			return menuEscolherArtista();
		} else {
			artista = artistas.get(escolha);
		}
		return artista;
	}
	
	public void cadastrarMusica() {
		String nome, album;
		int opc = -1;
		System.out.println("CADASTRAR MUSICA");
		System.out.print("Informe o nome da música: ");
		nome = scan.nextLine().toUpperCase();
		System.out.println("Informe o nome do álbum que essa música pertence: ");
		album = scan.nextLine().toUpperCase();
		Artista artista = menuEscolherArtista();
		if (artista != null) {
			artista.setMusicas(List.of(new Musica(nome, album, artista)));
			repositorioArtista.save(artista);
		} else {
			System.out.println("Artista inválido");
		}
	}
}
