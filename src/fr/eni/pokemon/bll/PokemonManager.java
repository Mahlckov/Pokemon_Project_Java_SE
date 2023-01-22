package fr.eni.pokemon.bll;

import java.util.List;


import fr.eni.pokemon.dal.PokemonDAO;
import fr.eni.pokemon.bo.Pokemon;
import fr.eni.pokemon.dal.DAOException;
import fr.eni.pokemon.dal.DAOFactory;

/**
 * Pour chaque méthode décrite dans la BLL, 
 * si une erreur intervient dans la DAL, 
 * on l'intercepte au niveau du BLL, 
 * et on l'ajouter à la liste des erreurs à restituer à l'utilisateur
 */
public class PokemonManager {
	private PokemonDAO daoPokemon;
	
	public PokemonManager() {
		//instancier le DAO
		daoPokemon = DAOFactory.getPokemonDAO();
	}
	
	public List<Pokemon> getPokedex() throws BLLException{
		try {
			return 	daoPokemon.selectAll();
		} catch (DAOException e) {
			BLLException blle = new BLLException();
			blle.ajouterErreur(e.getMessage());
			throw blle;
		}
	}
	
	public void removePokemon(int index)  throws BLLException{
		try {
			daoPokemon.delete(index);
		} catch (DAOException e) {
			BLLException blle = new BLLException();
			blle.ajouterErreur(e.getMessage());
			throw blle;
		}
	}
	
	public Pokemon getPokemon(int index) throws BLLException{
		try {
			return 	daoPokemon.selectById(index);
		} catch (DAOException e) {
			BLLException blle = new BLLException();
			blle.ajouterErreur(e.getMessage());
			throw blle;
		}
	}
	
	public void addPokemon(Pokemon pokemon) throws BLLException {

		try {
			validerPokemon(pokemon);
			daoPokemon.insert(pokemon);
		} catch (DAOException e) {
			BLLException blle = new BLLException();
			blle.ajouterErreur(e.getMessage());
			throw blle;
		}
	}
	
	
	public void updatePokemon(Pokemon pokemon) throws BLLException {
		try {
			validerPokemon(pokemon);
			daoPokemon.update(pokemon);
		} catch (DAOException e) {
			BLLException blle = new BLLException();
			blle.ajouterErreur(e.getMessage());
			throw blle;
		}
	}
	
	
	public void validerPokemon(Pokemon pokemon) throws BLLException {
		BLLException exception = new BLLException();
		
		if(pokemon.getNom()==null || pokemon.getNom().isBlank()) { //est-ce que le nom a été saisi= pas vide + si y'a pas juste des espaces
			//throw new Exception("Le pokémon doit avoir un nom");
			exception.ajouterErreur("Le pokémon doit avoir un nom");
		}
		
		if(pokemon.getType()==null || pokemon.getType().isBlank()) { //est-ce que le type a été saisi= pas vide + si y'a pas juste des espaces
			//throw new Exception("Le pokémon doit avoir un type");
			exception.ajouterErreur("Le pokémon doit avoir un type");
		}
		
		if(pokemon.getPC() < 0) { 
			//throw new Exception("Le PC du pokémon ne peut être négatif");
			exception.ajouterErreur("Le PC du pokémon ne peut être négatif");
		}
		
		if(pokemon.getPV() < 0) { 
			//throw new Exception("Le nombre de PV du pokémon ne peut être négatif");
			exception.ajouterErreur("Le nombre de PV du pokémon ne peut être négatif");
		}
		
		if(pokemon.getShiny()==null) { //est-ce que Shiny a été saisie= pas vide (Boolean= null, false ou true /boolean=true ou false)
			//throw new Exception("Le pokémon doit être Shiny ou non");
			exception.ajouterErreur("Le pokémon doit être Shiny ou non");
		}
		
		if (exception.getErreurs().size() >0) {
			throw exception;
		}
	}
	
	

}
