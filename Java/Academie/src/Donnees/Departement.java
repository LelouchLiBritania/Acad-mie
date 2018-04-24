package Donnees;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectionJdbc.ConnectionJdbc;

public class Departement {
	
	public College college;
	public List<Enseignant> listeEnseignants;
	public Enseignant responsable;
	
	public Departement (Enseignant responsable) {
		this.responsable = responsable;
		this.listeEnseignants = new ArrayList<Enseignant>();
		this.listeEnseignants.add(responsable);
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
