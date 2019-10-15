package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private String jdbcURL = "jdbc:mysql://study-buddy.ckt1lv24tfan.us-east-1.rds.amazonaws.com:3306/studybuddies";
    private String jdbcUsername = "team3";
    private String jdbcPassword = "Sweng4112019";
    protected Connection jdbcConnection;

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

}
