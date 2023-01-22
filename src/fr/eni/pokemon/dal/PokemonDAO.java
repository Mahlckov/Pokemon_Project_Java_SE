package fr.eni.pokemon.dal;

import java.util.List;

import fr.eni.pokemon.bll.BLLException;
import fr.eni.pokemon.bo.Pokemon;

/**
 * interface ≠ classe
 * classe : implémente des focntionnalités = méthode A + description
 * interface : je dispoe des méthodes abcd, mais sans dire ce qu'elles font, elles doivent juste exister
 * intérêt : quand 1 seule classe qui l'implémente => potentiellement on peut utiliser une autre classe utilisant une autre technique
 * de récupération des data = autre comportement, syntaxe ≠ avec le même CRUD
 * 
 */

public interface PokemonDAO {
	public List<Pokemon> selectAll() throws DAOException ;
	
	public Pokemon selectById (int id) throws DAOException ;

	public void insert(Pokemon pokemon) throws DAOException,BLLException ;
	
	public void update(Pokemon pokemon) throws DAOException ;
	
	public void delete(int id) throws DAOException ;
}
