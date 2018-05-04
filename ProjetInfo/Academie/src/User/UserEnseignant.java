package User;

import Donnees.Adresse;
import Donnees.Enseignant;
import Donnees.Etudiant;
import Donnees.Matiere;
import Donnees.Note;

public class UserEnseignant extends User {
	/*
	 * Attributes
	 */
	public Enseignant enseignant;
	
	/*
	 * Constructor
	 */
	public UserEnseignant(String identifiant, String mdp) {
		super(identifiant, mdp);
	}

	/*
	 * Methods
	 */
	protected Note ajouterNote (Etudiant etudiant, Matiere matiere, int valeur) {
		 Note note = new Note(etudiant, matiere, valeur);
		 return note;
	}
	
	protected void afficherDonnees() {
		System.out.println(this.enseignant.toString());
	}
	
	protected void modifierTelephone(String numero) {
		this.enseignant.setTelephone(numero);
	}
	
	protected void modifierMail(String mail) {
		this.enseignant.setMail(mail);
	}
	
	protected void modifierAdresse(Adresse adresse) {
		this.enseignant.setAdresse(adresse);
	}
	
	
}
