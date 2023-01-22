package fr.eni.pokemon.app;

import fr.eni.pokemon.bll.BLLException;
import fr.eni.pokemon.bll.PokemonManager;
import fr.eni.pokemon.bo.Pokemon;


public class AppliTestBLLEnErreur {

	public static void main(String[] args) {
		PokemonManager pmng = new PokemonManager();
	
	
		System.out.println("***********************************");
		System.out.println("Ajout de tests qui se passent mal");
		System.out.println("***********************************");
		
		Pokemon echecPokemon = new Pokemon(null,null,0,0, null);
		
		System.out.println("Echec ajout d'un pokémon: ");
		//Mention de toutes les erreurs = null mais pas à 0
		try {
			pmng.addPokemon(echecPokemon);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		System.out.println();
		
		
		Pokemon echecPokemon1 = new Pokemon("nom","type",-5,-18, false);
		
		System.out.println();
		System.out.println("Echec ajout d'un pokémon avec PC et PV négatifs: ");

		try {
			pmng.addPokemon(echecPokemon1);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		
		System.out.println();
		System.out.println("***********************************");
		System.out.println("Tests des insertions réussies");
		Pokemon pokemonReussi = new Pokemon("Chinchidou","Normal",151,54,true);
		try {
			pmng.addPokemon(pokemonReussi);
		} catch (BLLException e1) {
			e1.printStackTrace();
		}
		System.out.println("ajout de : "+pokemonReussi);
		
		System.out.println("Tests des MAJ en échec :");
		pokemonReussi.setNom("");
		pokemonReussi.setType("");
		pokemonReussi.setPC(-90);
		pokemonReussi.setPV(-6);
		pokemonReussi.setShiny(null);
		
		try {
			pmng.updatePokemon(pokemonReussi);
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

}
