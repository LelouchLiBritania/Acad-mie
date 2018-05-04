package Donnees;

public class Note {
	/*
	 * Attributes
	 */
	public Matiere matiere;
	public Etudiant etudiant;
	public double note;

	/*
	 * Constructor
	 */
	public Note(Etudiant etudiant, Matiere matiere, int valeur) {
		this.etudiant = etudiant;
		this.matiere = matiere;
		this.note = valeur;
	}

}
