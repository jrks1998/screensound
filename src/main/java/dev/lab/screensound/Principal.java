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
			System.out.println("3 - Listar artistas cadastrados");
			System.out.println("4 - Listar musicas cadastradas");
			System.out.println("5 - Listar músicas por artista");
			System.out.println("\n0 - Sair");
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
						listarArtistas();
						break;
					case 4:
						listarMusicas();
						break;
					case 5:
						buscarMusicasPorArtista();
						break;
					case 0:
						opc = 0;
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
		TipoArtista tipoArtista = TipoArtista.fromString(tipo);
		if (tipoArtista != null) {
			repositorioArtista.save(new Artista(nome, tipoArtista));
		} else {
			System.out.println("Tipo inválido. Apenas solo, dupla ou banda são aceitos");
			System.out.println("Artista não cadastrado");
		}
	}
	
	private int escolherItemDaLista(int min, int max, String mensagem) {
		int opc = -1;
		boolean valido = false;
		while (!valido) {
			System.out.print(mensagem);
			try {
				opc = scan.nextInt();
				scan.nextLine();
				if (opc >= min && opc <= max) {
					valido = true;
				} else {
					System.out.println("Opção inválida");
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida. Somente números são aceitos");
			}
		}
		return opc;
	}
	
	private Artista menuEscolherArtista() {
		Artista artista;
		List<Artista> artistas = repositorioArtista.findAll();
		System.out.println("SELECIONE O ARTISTA");
		int i;
		for (i = 0; i < artistas.size(); i++) {
			System.out.println(String.format("%d - %s", i, artistas.get(i).getNome()));
		}
		System.out.println(String.format("%d - ARTISTA NÃO CADASTRADO", i));
		int escolha = escolherItemDaLista(0, artistas.size(), "Escolha uma opção: ");
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
			Musica musica = new Musica(nome, album, artista);
			artista.setMusicas(List.of(musica));
			repositorioArtista.save(artista);
		} else {
			System.out.println("Artista inválido");
		}
	}
	
	public void listarArtistas() {
		List<Artista> artistas = repositorioArtista.findAll();
		System.out.println("ARTISTAS CADASTRADOS");
		artistas.forEach(System.out::println);
	}
	
	public void listarMusicas() {
		List<Musica> musicas = repositorioMusica.findAll();
		System.out.println("MUSICAS CADASTRADAS");
		musicas.forEach(System.out::println);
	}
	
	public void buscarMusicasPorArtista() {
		List<Artista> artistas = repositorioArtista.findAll();
		System.out.println("MUSICAS POR ARTISTA");
		for (int i = 0; i < artistas.size(); i++) {
			System.out.println(String.format("%d - %s", i, artistas.get(i).getNome()));
		}
		int escolha = escolherItemDaLista(0, artistas.size() - 1, "Escolha uma opção: ");
		List<Musica> musicas = repositorioMusica.musicasPorArtista(artistas.get(escolha));
		System.out.println("MUSICAS CADASTRADAS DO ARTISTA " + artistas.get(escolha).getNome());
		musicas.forEach(System.out::println);
	}
}
