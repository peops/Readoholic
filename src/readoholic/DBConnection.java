package readoholic;

import java.sql.*;

public class DBConnection {
    private static String db_url;
    private static String db_port;
    private static String db_name;
    private static String db_user;
    private static String db_password;
    private static Connection connection;

    private DBConnection() {
        db_url = "jdbc:postgresql://localhost";
        db_port = "5432";
        db_name = "postgres";
        db_user = "postgres";
        db_password = "postgres";
        connection = setConnection();
    }
    private static Connection setConnection() {
        try {
            String url = "" + db_url + ":" + db_port + "/" + db_name + "";
            Class.forName("org.postgresql.Driver");
            java.sql.Connection conn = DriverManager.getConnection(url, db_user, db_password);
            return conn;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException e) {
        	System.out.println(e.getMessage());
		}
        return null;
    }
    private static class DbSingletonManagerHolder {
        private final static DBConnection instance = new DBConnection();
    }
    public static DBConnection getInstance() {
        try {
            return DbSingletonManagerHolder.instance;
        } catch (ExceptionInInitializerError ex) {

        }
        return null;
    }
    public static Connection getStatement() {
        return connection;
    }
}
