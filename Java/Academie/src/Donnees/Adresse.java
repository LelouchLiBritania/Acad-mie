package Donnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ConnectionJdbc.ConnectionJdbc;

public class Adresse {
	
	public String adresse;
	protected double[] coordonnees;
	
	public Adresse() {
		
	}
	
	public Adresse(int id_adr) {
		Connection conn = ConnectionJdbc.getInstance();
		try{
			
			
			Statement state = conn.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM adresse WHERE id = " + id_adr);
			result.next();
			
			this.adresse = result.getString("adresse");
			
			
			
			
		}
		catch(Exception e){
			e.printStackTrace(); // pour gerer les erreurs (pas de pilote, base inexistante, etc.)
		}
		finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // toujours fermer les differentes ressources quand il n’y en as plus besoin
			}
		}
		
		
		
	}
	
	public Adresse(String adresse, double[] coordonnees) {
		this.adresse = adresse;
		if (coordonnees.length == 2) {
			this.coordonnees = coordonnees;
		}
	}
	
	public double calculerDistance(Adresse adr) {
		try {
				double dx = this.coordonnees[0]-adr.coordonnees[0];
				double dy = this.coordonnees[1]-adr.coordonnees[1];
				return (Math.sqrt(dx*dx+dy*dy));
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("Coordonnées de l'adresse inconnues");
			return(0);
		}
	}

}
