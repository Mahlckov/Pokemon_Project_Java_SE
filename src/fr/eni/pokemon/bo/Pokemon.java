package fr.eni.pokemon.bo;

public class Pokemon {
	private int idPokemon ;
	private String nom ;
	private String type ;
	private int pc ;
	private int pv ;
	private Boolean shiny ;

	public Pokemon() {
		
	}
	
	public Pokemon(int idPokemon, String nom, String type, int pc,int pv, Boolean shiny) {
		this.idPokemon = idPokemon;
		this.nom=nom;
		this.type=type;
		this.pc=pc;
		this.pv=pv;
		this.shiny=shiny;
	}

	public Pokemon( String nom, String type, int pc,int pv, Boolean shiny) {
		this.nom=nom;
		this.type=type;
		this.pc=pc;
		this.pv=pv;
		this.shiny=shiny;
	}
	
	public int getIdPokemon() {
		return idPokemon ;
	} 
	
	public String getNom() {
		return nom ;
	}
	
	public String getType() {
		return type ;
	} 
	
	public int getPC() {
		return pc ;
	} 
	
	public int getPV() {
		return pv ;
	} 
	
	public Boolean getShiny() {
		return shiny ;
	} 
	
	public void setIdPokemon(int idPokemon) {
		this.idPokemon=idPokemon;
	}
	
	public void setNom(String nom) {
		this.nom=nom;
	}
	
	public void setType(String type) {
		this.type=type;
	}
	
	public void setPV(int pv) {
		this.pv=pv;
	}
	
	public void setPC(int pc) {
		this.pc=pc;
	}
	
	public void setShiny(Boolean shiny) {
		this.shiny=shiny;
	}
	
	public String toString() {
		return "Pokemon [idPokemon= " + idPokemon + ", Nom= " + nom + ", Type= " + type + 
				", PC= " + pc + ", PV= " + pv + ", Shiny?= " + shiny +"]";
	}
}
