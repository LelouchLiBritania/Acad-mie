package Donnees;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import ConnectionJdbc.ConnectionJdbc;

public class Etudiant {
	
	public final int id;
	public final String nom;
	public final String prenom;
	public String mail;
	public String telephone;
	public Date anneeEntreeCollege;
	public College college;
	
	public Etudiant(int id) {
		Connection conn = ConnectionJdbc.getInstance();
		
		String nom = "";
		String prenom ="";
		this.id = id;
		
		try {
			
			
			Statement state = conn.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM etudiant WHERE id = " + id);
			result.next();
			
			
			nom = result.getString("nom");
			prenom = result.getString("prenom");
			this.mail = result.getString("mail");
			this.telephone = result.getString("telephone");
			this.anneeEntreeCollege = result.getDate("annee_entree_college");
			this.college = new College(result.getInt("id_college"));
			
			result.close();
			state.close();
		}
		catch(Exception e){
			e.printStackTrace(); // pour gerer les erreurs (pas de pilote, base inexistante, etc.)
		}
		
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Etudiant(int id, String nom, String prenom, College college) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.college = college;
		
		
	}
	
	public double moyenneGenerale() {
		Connection conn = ConnectionJdbc.getInstance();
		int n = 0;
		double somme = 0;
		
		try {
			
			
			Statement state = conn.createStatement();
			String s = "SELECT valeur FROM note WHERE id_etudiant = " + this.id;
				
			ResultSet result = state.executeQuery(s);
				
				
			while(result.next()) {
				n+=1;
				somme+=result.getDouble("valeur");
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
			return(somme/n);
		}
	}
	
	public void ficheSignaletique() {
		
	}

}
