package Donnees;

import java.util.Date;

public class Etudiant {
	
	public final String nom;
	public final String prenom;
	public String mail;
	public String telephone;
	public Date anneeEntreeCollege;
	public College college;
	
	public Etudiant(String nom, String prenom, College college) {
		this.nom = nom;
		this.prenom = prenom;
		this.college = college;
		
		
	}
	
	public double moyenneGenerale() {
		return(0);
	}
	
	public void ficheSignaletique() {
		
	}

}
