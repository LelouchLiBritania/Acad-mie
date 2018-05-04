package User;

import Donnees.College;
import Donnees.Etudiant;

public class UserEtudiant extends User {
	/*
	 *  Attributes
	 */
	public College college;
	public Etudiant etudiant;
	
	/*
	 * Constructor
	 */
	public UserEtudiant(String id, String mdp) {
		super(id, mdp);
	}
	
	/*
	 * Methods
	 */
	public void modifierTelephone(String numero) {
		this.etudiant.setTelephone(numero);
	}
	
	public void modifierMail(String mail) {
		this.etudiant.setMail(mail);
	}
	
	public void afficherMatieres() {
		System.out.println("Les matieres de l'eleve sont : " + etudiant.voirMatieres());
	}
	
	public void afficherInfo(boolean bool) {
		if(bool) {
			System.out.println(etudiant.toString());
			afficherMatieres();
		}
		else {
			System.out.println(etudiant.toString());
		}
		
	}
	
}
