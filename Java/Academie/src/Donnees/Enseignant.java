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

public class Enseignant {
	
	public final int id;
	public final String nom;
	public final String prenom;
	private String telephone;
	private String mail;
	protected final Date datePriseDeFonction;
	protected College collegePrincipal;
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
			this.collegePrincipal = new College(result.getInt("id_college_principal"));
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
	
	
	
	public Enseignant(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
		
		Connection conn = ConnectionJdbc.getInstance();
		
		int n = 0;
		List<Integer> listeEnseignants = new ArrayList<Integer>();
		
		try {
			
			
			Statement state = conn.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM enseignant WHERE nom ILIKE = " + nom + " AND prenom LIKE " + prenom);
			ResultSet result2;
			ResultSet result3;
			
			System.out.println("n°       Nom       Prenom       College Principal       College Secondaire       Prise de fonction");
			
			while(result.next()) {
				result2 = state.executeQuery("SELECT nom FROM college WHERE id ILIKE = " + result.getInt("id_college_principal"));
				result3 = state.executeQuery("SELECT nom FROM college WHERE id ILIKE = " + result.getInt("id_college_secondaire"));
				n+=1;
				listeEnseignants.add(result.getInt("id"));
				System.out.println(n + " : " + result.getString("nom")+ result.getString("prenom")+result2.getString("nom")+result3.getString("nom")+result.getDate("prise_date_secondaire"));
			}
			
			result.close();
			state.close();
		}
		catch(Exception e){
			e.printStackTrace(); // pour gerer les erreurs (pas de pilote, base inexistante, etc.)
		}
		if (n==0) {
			System.out.println("Enseignant inconnu");

			this.id=0;
			this.datePriseDeFonction = new Date();
		}
		
		else {
			Scanner sc = new Scanner(System.in);
			int num = -1;
			System.out.println("n° de l'enseignant?");
			while(0<=num && num<=(n-1)){
				num = sc.nextInt();
			}
			sc.close();
			
			
			this.id = listeEnseignants.get(num);
			
			Date priseFonction = new Date();
			
			try {
				
				
				Statement state = conn.createStatement();
				ResultSet result = state.executeQuery("SELECT * FROM enseignant WHERE id = " + id);
				result.next();
				
				
				nom = result.getString("nom");
				prenom = result.getString("prenom");
				this.mail = result.getString("mail");
				this.telephone = result.getString("telephone");
				priseFonction = result.getDate("prise_de_fonction");
				this.collegePrincipal = new College(result.getInt("id_college_principal"));
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
			
			this.datePriseDeFonction = priseFonction;
		
		
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
			s+="Prise de fonction : "+this.datePriseDeFonction+"\n";
			s+="College principal : " + this.collegePrincipal.nom+"\n";
			s+="College secondaire : "+this.collegeSecondaire.nom+"\n";
			
			writer.write(s);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	

}
