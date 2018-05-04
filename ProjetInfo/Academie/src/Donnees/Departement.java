package Donnees;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectionJdbc.ConnectionJdbc;

public class Departement {
	
	public final String nom;
	public final int id;
	public College college;
	public List<Enseignant> listeEnseignants;
	public Enseignant responsable;
	
	public Departement (Enseignant responsable) {
		this.nom = "";
		this.id = 0;
		this.responsable = responsable;
		this.listeEnseignants = new ArrayList<Enseignant>();
		this.listeEnseignants.add(responsable);
	}
	
	public Departement(int id) {
		String nom = "";
		this.id = id;
		Connection conn = ConnectionJdbc.getInstance();
		this.listeEnseignants = new ArrayList<Enseignant>();
		
		
		try {
			
			
			Statement state = conn.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM departement WHERE id = " + id);
			result.next();
			
			this.college = new College(result.getInt("id_college"));
			this.responsable = new Enseignant(result.getInt("id_responsable"));
			nom = result.getString("nom");
			
			ResultSet result2 = state.executeQuery("SELECT id FROM enseignant WHERE id_dept_principal = "+id+" OR id_dept_secondaire = "+id);
			Enseignant ens;
			while(result2.next()) {
				ens = new Enseignant(result2.getInt("id"));
				this.listeEnseignants.add(ens);
			}
			
			
			
			result.close();
			state.close();
		}
		catch(Exception e){
			e.printStackTrace(); // pour gerer les erreurs (pas de pilote, base inexistante, etc.)
		}
		this.nom=nom;
	}
	
	public double moyenne() {
		Connection conn = ConnectionJdbc.getInstance();
		int n = 0;
		double somme = 0;
		
		try {
			
			for (Enseignant ens : this.listeEnseignants) {
				Statement state = conn.createStatement();
				String s = "SELECT n.valeur FROM note n JOIN matiere m ON n.id_matiere = m.id";
				s+="JOIN enseignant e ON e.id_matiere = m.id ";
				s+="WHERE e.id = "+ ens.id;
				
				ResultSet result = state.executeQuery(s);
				
				
				while(result.next()) {
					n+=1;
					somme+=result.getDouble("valeur");
				}
				
				result.close();
				state.close();
			}
			
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
	
	

}
