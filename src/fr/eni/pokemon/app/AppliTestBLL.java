package fr.eni.pokemon.app;

import java.util.ArrayList;
import java.util.List;

import fr.eni.pokemon.bll.BLLException;
import fr.eni.pokemon.bll.PokemonManager;
import fr.eni.pokemon.bo.Pokemon;

public class AppliTestBLL {

	public static void main(String[] args) {
		// Instanciation du jeu d'essai
		List<Pokemon> pokemon = new ArrayList<>();
		Pokemon pkmn = new Pokemon("Mucuscule", "Dragon", 391, 71, false);
		pokemon.add(pkmn);
		pokemon.add(new Pokemon("Sucroquin", "Fée", 666, 102, false));
		pokemon.add(new Pokemon("Zorua", "Ténèbres", 356, 59, false));
		pokemon.add(new Pokemon("Cosmog", "Psy", 178, 71, false));
		pokemon.add(new Pokemon("Crabagarre", "Combat", 1170, 105, false));

		PokemonManager pmng = new PokemonManager();
		

		// Ajout d'un pokémon au Pokedex
		try {
			for (Pokemon pkmn1 : pokemon) {
				pmng.addPokemon(pkmn1);
			}
			System.out.println(pmng.getPokedex());

		} catch (BLLException e) {
			e.printStackTrace();
		}

		// Modification d'un pokémon
		try {
			pkmn.setNom("Archéomire");
			pkmn.setType("Acier");
			pkmn.setShiny(true);
			pmng.updatePokemon(pkmn);
			System.out.println("Article après modification  : " + pkmn.toString());
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
}