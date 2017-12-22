package db;

public class DBConnectionFactory {
	private static final String DEFAULT_DB = "mysql";
	
	public static DBConnection getDBConnection(String db) {
		switch(db) {
		case("mysql"):
			return null;
		default:
			throw new IllegalArgumentException("Invalid db: " + db);
		}
	}
	
	public static DBConnection getDBConnection() {
		return getDBConnection(DEFAULT_DB);
	}
}
