package Donnees;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import ConnectionJdbc.ConnectionJdbc;

public class Enseignant {
	
	public final int id;
	public final String nom;
	public final String prenom;
	private String telephone;
	private String mail;
	protected final Date datePriseDeFonction;
	protected College collegePrincipale;
	protected College collegeSecondaire;
	
	public Enseignant(int id) {
		Connection conn = ConnectionJdbc.getInstance();
		
		String nom = "";
		String prenom ="";
		Date priseFonction = new Date();
		this.id = id;
		try {
			
			
			Statement state = conn.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM enseignant WHERE id = " + id);
			result.next();
			
			
			nom = result.getString("nom");
			prenom = result.getString("prenom");
			this.mail = result.getString("mail");
			this.telephone = result.getString("telephone");
			priseFonction = result.getDate("prise_de_fonction");
			this.collegePrincipale = new College(result.getInt("id_college_principal"));
			int id_sec = result.getInt("id_college_secondaire");
			if ( !result.wasNull()) {
				this.collegeSecondaire = new College(id_sec);
			}
			
			result.close();
			state.close();
		}
		catch(Exception e){
			e.printStackTrace(); // pour gerer les erreurs (pas de pilote, base inexistante, etc.)
		}
		
		this.nom = nom;
		this.prenom = prenom;
		this.datePriseDeFonction = priseFonction;
	}
	
	
	
	public Enseignant(int id, String nom, String prenom, Date datePriseDeFonction) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.datePriseDeFonction = datePriseDeFonction;
	}
	
	

}
