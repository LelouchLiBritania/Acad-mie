package Donnees;

import java.util.Date;

public class Enseignant {
	
	public final String nom;
	public final String prenom;
	private String telephone;
	private String mail;
	protected final Date datePriseDeFonction;
	
	public Enseignant(String nom, String prenom, Date datePriseDeFonction) {
		this.nom = nom;
		this.prenom = prenom;
		this.datePriseDeFonction = datePriseDeFonction;
	}
	
	
	
	

}
