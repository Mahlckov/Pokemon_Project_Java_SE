package fr.eni.pokemon.dal;

import java.io.IOException;
import java.util.Properties;
/**
 * Permet de charger les propriétés
 * @author tchapeau2022
 *
 */

public class Settings {
	//classe qui contient un attribut properties de la classe Properties => permet de gérer des paramètres type clé/valeur
	private static Properties properties; 
	
	//instancier la valeur properties grâce a un bloc d'initialisation static
	 static {
		 properties = new Properties();
		 //charger un fichier de configuration (texte ou XML)
		 try {
			properties.load(Settings.class.getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}		 
	 }
	//Méthode qui vient récupérer les valeurs par key de url, user, password
	public static String getProperty(String key) {
		return properties.getProperty(key); 
	}
}
