package fr.eni.pokemon.app;

import java.util.List;

import fr.eni.pokemon.bll.BLLException;
import fr.eni.pokemon.bll.PokemonBLL;
import fr.eni.pokemon.bo.Pokemon;
import fr.eni.pokemon.dal.DAOException;


public class AppliTestDAL {
	public static void main(String[] args) throws DAOException, BLLException {
		/**
		 * PokemonDAO dao permet de déclarer une variable de type PokemonDAO
		 * ---> cad "je sais quelles méthodes je peux utiliser mais je ne sais aps ce qu'elles font
		 * ---> avec cette interface => si on avait créé une méthode supplémentaire dans PokemonDAOJdbcImpl on aurait pas pu l'utiliser
		 * 
		 * = new PokemonDAOJdbcImpl();
		 * ---> je viens préciser à dao quel traitement est lancé pour répondre aux méthodes utilisées
		 */
		PokemonBLL bll = new PokemonBLL() ;
		/**
		 * SRP : Single Responsibility Principle = chaque classe ne doit avoir qu'un seul rôle, responsabilité
		 */
		System.out.println("******************************************");
		System.out.println("Affichage de tout le pokedex");
		System.out.println("******************************************");
		List<Pokemon> monpokedex;
		monpokedex = bll.selectAll();
		for (Pokemon currentPokemon : monpokedex) {
			System.out.println(currentPokemon);
		}
		System.out.println();
		
		
		Pokemon p1 = bll.selectById(1);
		Pokemon p2 = bll.selectById(2);

		System.out.println("******************************************");
		System.out.println("Affichage des pokemon d'id 1 et 2");
		System.out.println("******************************************");
		System.out.println(p1);
		System.out.println(p2);
		System.out.println();

		
//		System.out.println("******************************************");
//		System.out.println("Insertion d'un pokemon");
//		System.out.println("******************************************");
//		/*
//		 * Les informations de pokemonAInserer sont utilisables tant qu'on est dans le main
//		 * et que je ne lui affecte pas de nouvelle valeur
//		 */
////	Pokemon pokemonAInserer = new Pokemon("Evoli", "normal", 549, 52, true);
//		bll.insert(pokemonAInserer);
//		System.out.println("Le pokemon insere est : ");
//		System.out.println(pokemonAInserer);
//		System.out.println();
		
//		System.out.println("******************************************");
//		System.out.println("Insertion INTERDITE d'un pokemon");
//		System.out.println("******************************************");
//		Pokemon pokemonAInserer = new Pokemon("Evoli", "normal", -2, -1, true);
//		bll.insert(pokemonAInserer);
//		System.out.println("Le pokemon insere est : ");
//		System.out.println(pokemonAInserer);
//		System.out.println();
//
//		System.out.println("******************************************");
//		System.out.println("Modification d'un pokemon");
//		System.out.println("******************************************");
//		System.out.println("Pokemon avant modification : ");
//		System.out.println(pokemonAInserer);
//		System.out.println();
//		
//		pokemonAInserer.setNom("Yohan");
//		pokemonAInserer.setType("humain");
//		pokemonAInserer.setPC(4800);
//		pokemonAInserer.setShiny(true);
//		bll.update(pokemonAInserer);
		
		
		System.out.println("******************************************");
		System.out.println("Modification interdite d'un pokemon");
		System.out.println("******************************************");
		System.out.println("Pokemon avant modification : ");
		
		Pokemon pokemonAModifier = bll.selectById(12);
		System.out.println(pokemonAModifier);
		
		// Pokemon nouveauPokemon = new Pokemon(11, "Evoli", "normal", 345, 49, false);
		pokemonAModifier.setType("");
		pokemonAModifier.setNom(null);
		pokemonAModifier.setPC(-2);
		bll.update(pokemonAModifier);
		
		System.out.println("Pokemon apres modification : ");
		System.out.println(pokemonAModifier);
		System.out.println();

//		
//		System.out.println("******************************************");
//		System.out.println("Modification du pokémon d'identifiant 12");
//		System.out.println("******************************************");
//		System.out.println("Pokemon avant modification : ");
//		
//		Pokemon pokemonAModifier = bll.selectById(12);
//		System.out.println(pokemonAModifier);
//		
//		// Pokemon nouveauPokemon = new Pokemon(11, "Evoli", "normal", 345, 49, false);
//		pokemonAModifier.setType("Poison");
//		pokemonAModifier.setShiny(true);
//		bll.update(pokemonAModifier);
//		
//		System.out.println("Pokemon apres modification : ");
//		System.out.println(pokemonAModifier);
//		System.out.println();
//
//		
//		System.out.println("******************************************");
//		System.out.println("Suppression de toutes mes betises");
//		System.out.println("******************************************");
//		bll.delete(pokemonAInserer.getIdPokemon());
//		// bll.delete(p1.getIdPokemon());

	}
}












