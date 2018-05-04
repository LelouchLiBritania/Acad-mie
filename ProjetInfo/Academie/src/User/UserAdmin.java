package User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import ConnectionJdbc.ConnectionJdbc;
import Donnees.Adresse;
import Donnees.College;
import Donnees.Enseignant;
import Donnees.Etudiant;

public class UserAdmin extends User {

	// Attribute
	public College college;

	/*
	 * Constructors
	 */
	public UserAdmin(String id, String mdp) {
		super(id, mdp);
	}
		
	public UserAdmin(String id, String mdp, College college) {
		super(id, mdp);
		this.college = college;	
	}
	
	/*
	 * Methods
	 */
	public void imprimerFicheSignaletiqueEnseignant(String nom, String prenom) {
		Enseignant enseignant = new Enseignant(nom, prenom);
		System.out.println(enseignant.toString());
	}
	
	public void imprimerFicheSignaletiqueEtudiant(String nom, String prenom, boolean bool) {
		Etudiant etudiant = new Etudiant(nom, prenom);
		System.out.println(etudiant.toString() + etudiant.voirMatieres());
	}
	
	public void creerEnseignant(String nom, String prenom, String dept, Date datePriseDeFonction, String adresse, College collegePrincipal ) {
		
		Connection conn = ConnectionJdbc.getInstance();
		
		creerAdresse(adresse);
		
		try {
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery("SELECT id FROM adresse WHERE adresse LIKE "+ adresse);
			result.next();
			int id_adresse = result.getInt(id);
			
			ResultSet result2 = st.executeQuery("SELECT id FROM departement WHERE nom LIKE " + dept);
			result.next();
			int id_dept_principal = result.getInt(id);
			
			
			PreparedStatement state = conn.prepareStatement("INSERT INTO enseignant(nom, prenom, prise_de_fonction, id_adresse, id_dept_principal ) VALUES (?,?,?,?,?) ");
			state.setString(1,nom);
			state.setString(2, prenom);
			state.setDate(3, datePriseDeFonction);
			state.setInt(4, id_adresse);
			state.setInt(5, id_dept_principal);
			state.executeUpdate();
			
			state.close();
			st.close();
		}
		catch(Exception e){
			e.printStackTrace(); 
		}
	}
	
	public void creerEtudiant(String nom, String prenom, College college, Date anneeEntreeCollege) {
		
		Connection conn = ConnectionJdbc.getInstance();
		
		try {			
			PreparedStatement state = conn.prepareStatement("INSERT INTO enseignant(nom, prenom, college, anneeEntreeCollege ) VALUES (?,?,?,?) ");
			state.setString(1,nom);
			state.setString(2, prenom);
			state.setInt(3,college.numeroAcademique);
			state.setDate(4, anneeEntreeCollege);
			state.executeUpdate();
			
			state.close();
		}
		catch(Exception e){
			e.printStackTrace(); 
		}
	}	

	
	
	public double calculerDistance(Enseignant enseignant) {
		double dist = enseignant.getAdresse().calculerDistance(college.adresse);
		return dist; 
	}
	
	
	
	public void creerAdresse(String adresse) {
		Connection conn = ConnectionJdbc.getInstance();
		
		try {
			
		}
	}
	
	
}
