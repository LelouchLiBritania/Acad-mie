package Donnees;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
	
	public Etudiant(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
		
		Connection conn = ConnectionJdbc.getInstance();
		
		int n = 0;
		List<Integer> listeEtudiants = new ArrayList<Integer>();
		
		try {
			
			
			Statement state = conn.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM etudiant WHERE nom ILIKE = " + nom + " AND prenom LIKE " + prenom);
			ResultSet result2;
			
			System.out.println("n°       Nom       Prenom       College");
			
			while(result.next()) {
				result2 = state.executeQuery("SELECT nom FROM college WHERE id ILIKE = " + result.getInt("id_college"));
				n+=1;
				listeEtudiants.add(result.getInt("id"));
				System.out.println(n + " : " + result.getString("nom")+ result.getString("prenom")+ result.getString("nom")+result2.getString("nom"));
			}
			
			result.close();
			state.close();
		}
		catch(Exception e){
			e.printStackTrace(); // pour gerer les erreurs (pas de pilote, base inexistante, etc.)
		}
		if (n==0) {
			System.out.println("Elève inconnu");

			this.id=0;
		}
		
		else {
			Scanner sc = new Scanner(System.in);
			int id = -1;
			System.out.println("n° de l'étudiant?");
			while(0<=id && id<=(n-1)){
				id = sc.nextInt();
			}
			sc.close();
			
			
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
			
		
		
	}
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
		
		String filePath = "C:/Users/Florent/eclipse-workspace/Academie/fiche_"+this.prenom+"_"+this.nom; // chemin absolu vers le fichier
		Path logFile = Paths.get(filePath);
		if (!Files.exists(logFile)) { // si le fichier n’existe pas on le cree
			try {
				Files.createFile(logFile);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		String s = "";
		try (BufferedWriter writer = Files.newBufferedWriter(logFile,StandardCharsets.UTF_8, StandardOpenOption.WRITE)) { // buffer en ecriture (ecrase l’existant), encodage UTF8
			s+="Nom : "+this.nom+"\n";
			s+="Prenom : "+this.prenom+"\n";
			s+="Mail : "+this.mail+"\n";
			s+="Téléphone : "+this.telephone+"\n";
			s+="Année d'entrée au collège : "+this.anneeEntreeCollege+"\n";
			s+="College : " + this.college.nom+"\n";
			
			writer.write(s);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
