package Donnees;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectionJdbc.ConnectionJdbc;

public class College {
	public final String nom;
	public final int numeroAcademique;
	public final Adresse adresse;
	public String siteInternet;
	
	public College() {
		this.nom = "";
		this.numeroAcademique = 0;
		this.adresse = new Adresse ();
	}
	
	public College(int numeroAcademique) {
		
		Connection conn = ConnectionJdbc.getInstance();
		this.numeroAcademique = numeroAcademique;
		
		String nom = "";
		double[] coord = {0,0};
		Adresse adresse = new Adresse("adresse inconnue",coord);
		String siteInternet = "";
		
		try {
			
			
			Statement state = conn.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM college WHERE numero_academique = " + numeroAcademique);
			result.next();
			
			
			nom = result.getString("nom");
			adresse = new Adresse(result.getInt("id_adr"));
			siteInternet = result.getString("site_internet");
			
			result.close();
			state.close();
		}
		catch(Exception e){
			e.printStackTrace(); // pour gerer les erreurs (pas de pilote, base inexistante, etc.)
		}
		
		this.nom = nom;
		this.adresse = adresse;
		this.siteInternet = siteInternet;
		
		}
			
			
	
		
		
	
	public List<Etudiant> listeEtudiant(){
		Connection conn = ConnectionJdbc.getInstance();
		List<Etudiant> l = new ArrayList<Etudiant>();
		Etudiant e;
		
		try {
			
			
			Statement state = conn.createStatement();
			ResultSet result = state.executeQuery("SELECT id FROM etudiant WHERE id_college = " + this.numeroAcademique);
			
			
			while(result.next()) {
				e = new Etudiant(result.getInt("id"));
				l.add(e);
			}
			
			result.close();
			state.close();
		}
		catch(Exception ex){
			ex.printStackTrace(); // pour gerer les erreurs (pas de pilote, base inexistante, etc.)
		}
		
		return l;
		
	}
	 
	public List<Enseignant> listeEnseignant(){
		Connection conn = ConnectionJdbc.getInstance();
		List<Enseignant> l = new ArrayList<Enseignant>();
		Enseignant e;
		
		try {
			
			
			Statement state = conn.createStatement();
			ResultSet result = state.executeQuery("SELECT id FROM enseignant WHERE id_college_principal = " + this.numeroAcademique + "OR id_college_secondaire = " + this.numeroAcademique);
			
			
			while(result.next()) {
				e = new Enseignant(result.getInt("id"));
				l.add(e);
			}
			
			result.close();
			state.close();
		}
		catch(Exception ex){
			ex.printStackTrace(); // pour gerer les erreurs (pas de pilote, base inexistante, etc.)
		}
		
		return l;
	}
	
}
	 
	
	


	
