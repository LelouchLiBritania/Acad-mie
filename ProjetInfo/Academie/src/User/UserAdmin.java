package User;

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
	public void imprimerFicheSignaletique(Enseignant enseignant) {
		System.out.println(enseignant.toString());
	}
	
	public void imprimerFicheSignaletique(Etudiant etudiant) {
		System.out.println("L'eleve : " + etudiant.prenom + " " + etudiant.nom + " est entre(e) au college " + etudiant.college + " en " + etudiant.anneeEntreeCollege);
		System.out.println("Son mail est : " + etudiant.getMail() + " et son numero de telephone est : " + etudiant.getTelephone());
		System.out.println("L'eleve a pour matieres : " + etudiant.matieres);
	}
	
	public void creerEnseignant(String nom, String prenom) {
		Enseignant ens = new Enseignant(nom, prenom);
	}
	
	public void creerEtudiant(String nom, String prenom, College college) {
		Etudiant et = new Etudiant(nom, prenom, college);
	}	

	public double calculerDistance(Enseignant enseignant) {
		double dist = enseignant.getAdresse().calculerDistance(college.adresse);
		return dist; 
	}
}
