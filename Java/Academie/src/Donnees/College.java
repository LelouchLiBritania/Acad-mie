package Donnees;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ConnectionJdbc.ConnectionJdbc;

public class College {
	public final String nom;
	public final int numeroAcademique;
	public final Adresse adresse;
	public String siteInternet;
	
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
			
			
	}
		
		
	
	/*public Etudiant listeEtudiant(){}
	 *
	 * public Enseignant listeEnseignant(){}
	 */
	
	


	
