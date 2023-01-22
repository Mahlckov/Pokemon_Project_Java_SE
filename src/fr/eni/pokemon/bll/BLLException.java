package fr.eni.pokemon.bll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Permet de renvoyer une liste d'erreurs d'un coup
 */
public class BLLException extends Exception{
	private static final long serialVersionUID = 1L;
	private List<String> erreurs;
	
	public BLLException() {
		erreurs = new ArrayList<String>();
	}
	
	public void ajouterErreur(String message) {
		erreurs.add(message);
	}
	
	public BLLException(String message, Throwable exc) {
		super(message, exc);

	}
	
	public List<String> getErreurs() {
		return erreurs;
	}
	
	public String getMessage() {
		return "BBL - " + Arrays.toString(erreurs.toArray()) ;
	}
}
