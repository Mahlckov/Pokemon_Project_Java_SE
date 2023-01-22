package fr.eni.pokemon.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.pokemon.bo.Pokemon;
import fr.eni.pokemon.dal.DAOException;
import fr.eni.pokemon.dal.PokemonDAO;

public class PokemonDAOJdbcImpl implements PokemonDAO {
	
	
	private Connection con;
	Statement stmt;
	private static final String SELECT_ALL = "SELECT * FROM Pokedex";
	private static final String SELECT_BY_ID = "SELECT * FROM Pokedex WHERE idPokemon = ?";
	private static final String INSERT = "INSERT INTO Pokedex (nom, type, pc, pv, shiny) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE Pokedex SET nom = ?, type = ?, pc = ?, pv = ?, shiny = ? WHERE idPokemon = ?";
	private static final String DELETE = "DELETE FROM Pokedex WHERE idPokemon = ?";
	
	@Override
	public List<Pokemon> selectAll() throws DAOException{
		List<Pokemon> resultat = new ArrayList<Pokemon>();
		try {
			con=JdbcTools.getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_ALL);
			ResultSet rs =ps.executeQuery();

			// Parcourt une a une les lignes du resultat
			while (rs.next()) {
				// Je commence par recuperer le type pour savoir si j'ai affaire a un stylo ou a une ramette
				int id = rs.getInt("idPokemon");
				String nom = rs.getString("nom");
				String type = rs.getString("type");
				int pc = rs.getInt("pc");
				int pv = rs.getInt("pv");
				Boolean shiny = rs.getBoolean("shiny");
				Pokemon pokemon = new Pokemon(id, nom, type, pc, pv, shiny);
				resultat.add(pokemon);
			}
			JdbcTools.closeConnection();
		}catch(SQLException e){
			throw new DAOException("selectAll failed " + e); //=constructeur qui prend en paramètre un String + exception
		}
		return resultat;
	}
	
	@Override
	public Pokemon selectById(int id) throws DAOException {
		Pokemon resultat = null;
		try {
			con=JdbcTools.getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, id);
			ResultSet rs =ps.executeQuery();
			
			if (rs.next()) {
				String nom = rs.getString("nom");
				String type = rs.getString("type");
				int pc = rs.getInt("pc");
				int pv = rs.getInt("pv");
				Boolean shiny = rs.getBoolean("shiny");
				resultat = new Pokemon(id, nom, type, pc, pv, shiny);
			}
			JdbcTools.closeConnection();
		} catch (SQLException e) {
			throw new DAOException("selectById failed - id = " + id, e); //=constructeur qui prend en paramètre un String + exception
		}
		return resultat;
	}

	@Override
	public void insert(Pokemon pokemon) throws DAOException{
		try {
			con=JdbcTools.getConnection();
			// "PreparedStatement.RETURN_GENERATED_KEYS" permet d'indiquer que je veux que la bdd me renvoie l'id genere
			PreparedStatement ps = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, pokemon.getNom());
			ps.setString(2, pokemon.getType());
			ps.setInt(3, pokemon.getPC());
			ps.setInt(4, pokemon.getPV());
			ps.setBoolean(5, pokemon.getShiny());
			
			ps.executeUpdate(); // declenche l'execution de ma requete
			
			// Me permet de recuperer la reponse de la bdd : l'id genere pour mon pokemon
			ResultSet rs = ps.getGeneratedKeys();
			// Me permet de lire la premiere ligne du resultat
			if (rs.next()) {
				pokemon.setIdPokemon(rs.getInt(1)); 
			}
			JdbcTools.closeConnection();
		} catch (SQLException e) {
			throw new DAOException("insert failed - " + e); //=constructeur qui prend en paramètre un String + exception
		}
	}

	@Override
	public void delete(int id) throws DAOException {
		try {
			con=JdbcTools.getConnection();
			PreparedStatement ps = con.prepareStatement(DELETE);
			ps.setInt(1, id);
			ps.executeUpdate();
			JdbcTools.closeConnection();
		} catch (SQLException e) {
			throw new DAOException("delete failed - id = " + id, e); //=constructeur qui prend en paramètre un String + exception
		}
		
	}

	@Override
	public void update(Pokemon pokemon) throws DAOException {
		try {
			con=JdbcTools.getConnection();
			PreparedStatement ps = con.prepareStatement(UPDATE);
			ps.setString(1, pokemon.getNom());
			ps.setString(2, pokemon.getType());
			ps.setInt(3, pokemon.getPC());
			ps.setInt(4, pokemon.getPV());
			ps.setBoolean(5, pokemon.getShiny());
			// J'ai besoin d'une info de plus compare au insert : l'identifiant du pokemon que je souhaite modifier
			ps.setInt(6, pokemon.getIdPokemon());
			ps.executeUpdate();
			
			JdbcTools.closeConnection();
		} catch (SQLException e) {
			throw new DAOException("update failed - " + e); //=constructeur qui prend en paramètre un String + exception
		}
	}

	

	

}
