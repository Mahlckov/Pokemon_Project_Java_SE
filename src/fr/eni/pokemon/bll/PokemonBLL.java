package fr.eni.pokemon.bll;

import java.util.List;

import fr.eni.pokemon.dal.DAOException;
import fr.eni.pokemon.bo.Pokemon;
import fr.eni.pokemon.dal.DAOFactory;
import fr.eni.pokemon.dal.PokemonDAO;
/**
 * Le rôle du BLL est de contrôler les donnees envoyees vers la BDD
 * Il a donc besoin d'une reference vers le DAO (ma variable dao)
 * Il definit les regles d'acceptation d'un pokemon avant INSERT
 */

public class PokemonBLL {
private PokemonDAO dao;
	
	public PokemonBLL() {
		dao = DAOFactory.getPokemonDAO();
	}
	
	public void insert(Pokemon pokemon) throws BLLException {
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
		
		//si une erreur intervient dans la DAL, on l'intercepte au niveau du BLL, et on l'ajouter à la liste des erreurs à restituer à l'utilisateur
				try {
					dao.insert(pokemon);
				} catch (DAOException e) {
					BLLException blle = new BLLException();
					blle.ajouterErreur(e.getMessage());
					throw blle;
				}
		
	}
	
	public List<Pokemon> selectAll() throws BLLException{
		try {
			return dao.selectAll();
		} catch (DAOException e) {
			BLLException blle = new BLLException();
			blle.ajouterErreur(e.getMessage());
			throw blle;
		}
	}
	
	public Pokemon selectById(int id) throws BLLException {
		try {
			return dao.selectById(id);
		} catch (DAOException e) {
			BLLException blle = new BLLException();
			blle.ajouterErreur(e.getMessage());
			throw blle;
		}
	}
	
	public void delete(int id) throws BLLException {
		 try {
			dao.delete(id);
		 } catch (DAOException e) {
			BLLException blle = new BLLException();
			blle.ajouterErreur(e.getMessage());
			throw blle;
		}
	}
	
	public void update(Pokemon pokemon) throws BLLException {
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
		
		try {
			dao.update(pokemon);
		} catch (DAOException e) {
			BLLException blle = new BLLException();
			blle.ajouterErreur(e.getMessage());
			throw blle;
		}
	}
}
