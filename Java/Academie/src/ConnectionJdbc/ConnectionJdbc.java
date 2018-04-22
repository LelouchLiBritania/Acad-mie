package ConnectionJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJdbc {
	
	private final String DB_URL = "jdbc:postgresql://localhost:5432/academie"; // driver JDBC utilise et url de la base de donnees
	private final String USER = "postgres"; // nom d’utilisateur
	private final String PASS = "postgres"; // mot de passe associe
	private static Connection conn;
	
	private ConnectionJdbc() {
		try{
			conn = DriverManager.getConnection(DB_URL, USER, PASS); // ouvre une connexion vers la base de donnees academie
		}
		catch(Exception e){
			e.printStackTrace(); // pour gerer les erreurs (pas de pilote, base inexistante, etc.)
		}
		
	}
	
	public static Connection getInstance() {
		if (conn == null) {
			new ConnectionJdbc();
		}
		
		return (conn);
	}

}
