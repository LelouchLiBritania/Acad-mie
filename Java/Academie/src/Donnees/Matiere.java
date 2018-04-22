package Donnees;

import java.util.List;

public class Matiere {
	
	public final String nom;
	public List<Enseignant> enseignantsMatiere;
	public Salle salle;
	
	public Matiere(String nom) {
		this.nom = nom;
	}
	
	public Matiere(String nom, Salle salle) {
		this.nom = nom;
		this.salle = salle;
	}
	
	public double moyenne() {
		return(0);
	}

}