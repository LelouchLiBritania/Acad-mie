package Donnees;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import ConnectionJdbc.ConnectionJdbc;

public class Salle {
	
	public String nom;
	public int nombreDePlace;
	
	public Salle(String nom, int nombreDePlace) {
		this.nom = nom;
		this.nombreDePlace = nombreDePlace;
	}
	
	public Salle (int id) {
		Connection conn = ConnectionJdbc.getInstance();
		
		try {
			
			
			Statement state = conn.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM salle WHERE id = " + id);
			result.next();
			
			this.nom = result.getString("nom");
			this.nombreDePlace = result.getInt("nb_place");
			
			result.close();
			state.close();
		}
		catch(Exception e){
			e.printStackTrace(); // pour gerer les erreurs (pas de pilote, base inexistante, etc.)
		}
	}

}
