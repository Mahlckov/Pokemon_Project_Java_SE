package fr.eni.pokemon.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.eni.pokemon.dal.Settings;

public class JdbcTools {
	private static Connection con;
	/**
	 * A chaque fois qu'on utilise une méthde ou proprete siatic => appartient a la classe jdbcTools et pas une instance de JdbcTools
	 * pas besoin d'instancier JdbcTools pour utiliser ses éléments => on peut même mettre la classe en abstract pour 
	 * empêcher toute instanciation
	 */
	
	//Récupérer la connexion après de la bdd à condition qu'il n'y en ait pas déjà une
	public static Connection getConnection() throws SQLException{
		if(con == null || con.isClosed()) {
			con = DriverManager.getConnection(
					Settings.getProperty("url"),
					Settings.getProperty("user"),
					Settings.getProperty("password"));
		}
		return con;
	}
	
	//Fermer la connexion avec la bdd si elle existe
	public static void closeConnection() throws SQLException{
		/**
		 * 1- je vérifie d'abord si la condition est nulle
		 * 2- si la condition 1 est vérifiée je teste si la connextion est ouverte
		 * if ne vérifie les 2 conditions que si la 1ère est vraie = lazy Interpretation
		 */
		if (con != null && !con.isClosed()) {
			con.close();
			con=null;
		}
	}

}
