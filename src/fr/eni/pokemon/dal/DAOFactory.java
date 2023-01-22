package fr.eni.pokemon.dal;

import fr.eni.pokemon.dal.jdbc.PokemonDAOJdbcImpl;

/**
 * L'accès à la couche DAL se fait uniquement via le DAOFactory
 * Deux utilités :
 * 1. Affecter une valeur à pokemonDAO depuis le package DAL. Si on veut changer d'implémentation 
 * 	  on ne changera que le code du package DAL, l n'y aura aucun imapct sur le reste de l'appli
 * 
 * 2.utilisattion du pattern Singleton : il n'y aura qu'une seule instance de PokemonDAO dans toute notre appli
 * 	 Le DAO est une classe qui contient diverses méthodes qui se conenctent à la bdd = pas d'intérêt d'avoir pls DAO, instances
 *
 */

public class DAOFactory {
	private static PokemonDAO pokemonDAO;
	
	public static PokemonDAO getPokemonDAO() {
		/**
		 * Pattern Singleton
		 */
		if(pokemonDAO == null) {
			pokemonDAO = new PokemonDAOJdbcImpl();
		}
		return pokemonDAO;
	}

}
