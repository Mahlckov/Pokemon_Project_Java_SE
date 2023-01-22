package fr.eni.pokemon.ihm;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import fr.eni.pokemon.bll.BLLException;
import fr.eni.pokemon.bll.PokemonManager;
import fr.eni.pokemon.bo.Pokemon;

/**
 * Role : interagir avec la bll pour aller chercher les infos en bdd
 * @author tchapeau2022
 *
 */

public class MonModele extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	private PokemonManager pm;
	private List<Pokemon> pokedex;
	private String[] entetes = {"Id", "Nom", "Type", "PC", "PV", "Shiny"};

	public MonModele() {
		pm = new PokemonManager();
		try {
			pokedex = pm.getPokedex();
		} catch (BLLException e) {
			BLLException blle = new BLLException();
			blle.ajouterErreur(e.getMessage());
		}
	}
	
	// gere le nombre de lignes affichees par le tableau
	@Override
	public int getRowCount() {
		return pokedex.size();
	}

	// gere le nombre de colonnes affichees par le tableau
	@Override
	public int getColumnCount() {
		return entetes.length;
	}
	
	// affecte les entetes de colonnes aux bonnes colonnes grace aux id des entetes
	@Override
	public String getColumnName(int column) {
		return entetes[column];
	}

	/**
	 *  affecte la valeur de cellule en fonction des donnees
	 *  Retourne un Object = n'importe quelle valeur (int, string, boolean etc)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Pokemon p = pokedex.get(rowIndex);
		switch (columnIndex) {
		case 0: // id
			return p.getIdPokemon();
		case 1: // reference
			return p.getNom();
		case 2: // reference
			return p.getType();
		case 3: // marque
			return p.getPC();
		case 4: // designation
			return p.getPV();
		case 5: // prix
			return p.getShiny();
		default: // pas cense arrive mais on sait jamais
			 throw new IllegalArgumentException("Colonne inexistante");
		}
	}
	
	public void reloadModel() {
		try {
			pokedex = pm.getPokedex();
		} catch (BLLException e) {
			BLLException blle = new BLLException();
			blle.ajouterErreur(e.getMessage());
		}
	}

}

















