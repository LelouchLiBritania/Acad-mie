package User;

public class User {
	/*
	 * Attributes
	 */
	public String id;
	private String mdp;
	
	/*
	 * Getters and Setters
	 */
	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	/*
	 * Constructor
	 */
	public User(String id, String mdp) {
		this.id = id;
		setMdp(mdp);
	}
}
