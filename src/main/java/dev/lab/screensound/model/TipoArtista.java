package dev.lab.screensound.model;

public enum TipoArtista {
	SOLO("SOLO"),
	BANDA("BANDA"),
	DUPLA("DUPLA");
	private String tipo;
	
	TipoArtista(String tipo) {
		this.tipo = tipo;
	}
	
	public static TipoArtista fromString(String texto) {
		for (TipoArtista tipoArtista : TipoArtista.values()) {
			if (tipoArtista.tipo.toLowerCase().equals(texto.toLowerCase())) {
				return tipoArtista;
			}
		}
		return null;
	}
	
	public String getTipoArtista() {
		return tipo;
	}
	
	@Override
	public String toString() {
		return "tipo: " + tipo;
	}
}
