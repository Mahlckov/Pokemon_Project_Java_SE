package fr.eni.pokemon.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.eni.pokemon.bll.BLLException;
import fr.eni.pokemon.bll.PokemonManager;
import fr.eni.pokemon.bo.Pokemon;
import fr.eni.pokemon.ihm.MaFenetre;

/**
 * Controller : Pour gerer les interactions avec l'utilisateur
 * role : decrire ce qu'il se passe quand on appuie sur un bouton, quand l'utilisateur inetrgair avec l'appli
 *
 */

public class MyListener implements ActionListener {
	private MaFenetre source; //ref vers la fenetre pour maj les infos apres clic du bouton
	private PokemonManager pokemonManager; //fait appel au manager en bll
	
	public MyListener(MaFenetre source) { //Permet d'enregistrer la fenetre suite au clic
		this.source = source;
		pokemonManager = new PokemonManager(); //Permet d'interagir avec la bdd
	}

	/**
	 * Methode appelee automatiquement au moment du clic sur le bouton ajouter
	 * source : recupere toutes les infos entrees sur la fenetre (view) pour permettre l'insertion en bdd 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String nom = source.getFieldNom().getText();
		String type = source.getFieldType().getText();
		int pc = Integer.valueOf(source.getFieldPC().getText());
		int pv = Integer.valueOf(source.getFieldPV().getText());
		Boolean shiny = Boolean.valueOf(source.getFieldShiny().getText());

		
		System.out.println("Ajout du pokémon : " + nom + " " + type + " " + pc + " " + pv + " " + shiny);
		
		try {
			Pokemon pokemon = new Pokemon(nom, type, pc, pv, shiny); //try ajout pokemon avec donnees recuperees
			pokemonManager.addPokemon(pokemon);
		} catch (BLLException e1) {
			source.handleException(e1.getMessage()); //exception decrite dans MaFenetre qui affiche un message d'erreur de la bll
		}
		source.updateContent(); //recharge le contenu du tableau Pokedex, signale le changement, et ordonne la maj
	}
}
