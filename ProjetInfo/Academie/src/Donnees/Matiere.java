package Donnees;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectionJdbc.ConnectionJdbc;

public class Matiere {
	
	public final int id;
	public final String nom;
	public List<Enseignant> enseignantsMatiere;
	public Salle salle;
	
	public Matiere(int id, String nom) {
		this.nom = nom;
		this.id = id;
	}
	
	public Matiere(int id, String nom, Salle salle) {
		this.nom = nom;
		this.salle = salle;
		this.id = id;
	}
	
	public Matiere (int id) {
		Connection conn = ConnectionJdbc.getInstance();
		
		String nom = "";
		this.enseignantsMatiere = new ArrayList<Enseignant>();
		this.id = id;
		try {
			
			
			Statement state = conn.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM matiere WHERE id = " + id);
			result.next();
			
			
			nom = result.getString("nom");
			this.salle = new Salle(result.getInt("id_salle"));
			
			ResultSet result2 = state.executeQuery("SELECT id FROM enseignant WHERE id_matiere = " + id);
			Enseignant ens;
			while(result2.next()) {
				ens = new Enseignant(result2.getInt("id"));
				this.enseignantsMatiere.add(ens);
			}
			
			
			result.close();
			state.close();
		}
		catch(Exception e){
			e.printStackTrace(); // pour gerer les erreurs (pas de pilote, base inexistante, etc.)
		}
		
		this.nom = nom;
	}
	
	public double moyenne() {
		Connection conn = ConnectionJdbc.getInstance();
		double valeur = 0;
		int n = 0;
	
		try {
			
			
			Statement state = conn.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM note WHERE id_matiere = " + this.id);
			result.next();
			
			while(result.next()) {
				valeur += result.getDouble("valeur");
				n+=1;
			}
			
			
			result.close();
			state.close();
		}
		catch(Exception e){
			e.printStackTrace(); // pour gerer les erreurs (pas de pilote, base inexistante, etc.)
		}
		
		if (n==0) {
			return(-1);
		}
		
		else {
			return(valeur/n);
		}
		
	}

}