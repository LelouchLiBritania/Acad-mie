package Donnees;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import ConnectionJdbc.ConnectionJdbc;

public class Note {
	
	public Note(double valeur,Matiere matiere,Etudiant etudiant) {
		
		Connection conn = ConnectionJdbc.getInstance();
		
		try {
			
			
			Statement state = conn.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM note WHERE valeur = "+valeur+" AND id_matiere = "+matiere.id+" AND id_etudiant="+etudiant.id);
			if (result.next()) {
				System.out.println("Note déjà attribuée");
			}
			else {
				state.executeUpdate("INSERT INTO note VALUES("+valeur+","+etudiant.id+","+matiere.id+")");
			}
			
			state.close();
		}
		catch(Exception e){
			e.printStackTrace(); // pour gerer les erreurs (pas de pilote, base inexistante, etc.)
		}

	}

}

