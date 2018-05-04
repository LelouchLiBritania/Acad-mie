package User;

import Donnees.Departement;
import Donnees.Enseignant;

public class UserResponsable {
	public UserEnseignant userEnseignant;
	public Departement departement;
	
	/*
	 * Constructor
	 */
	public UserResponsable(Enseignant enseignant, Departement departement) {
		this.userEnseignant.enseignant = enseignant;
		this.departement = departement;		
	}
	
}
