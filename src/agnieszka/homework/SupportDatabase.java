package agnieszka.homework;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import static agnieszka.homework.DatabaseConnection.*;

public class SupportDatabase {
	private Connection connection;
	protected static Statement statement;
	
	public void operationsDB(DBOperations... options) throws SQLException {
        for (DBOperations option : options) {
            switch (option) {
                case INIT_CONNECTION: {
                    initConnection();
                    break;
                }
                case CLOSE_CONNECTION: {
                    closeConnection();
                    break;
                }
            }
        }
    
	}
	private void initConnection() {
        String dbURL = String.format("jdbc:mysql://%s:%d/%s?%s", getHOST_MY(), getPORT(), getDB_NAME(),
                getPARAM_STRING());
        try {
            connection = DriverManager.getConnection(dbURL, getUSER_NAME(), getPASSWORD());
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Wyst¹pi³ problem z po³¹czeniem");
            e.printStackTrace();
        }
    }
	
	private void closeConnection() {
        try {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.out.println("Wyst¹pi³ problem z zamkniêciem po³¹czenia!!!");
            e.printStackTrace();
        }
    }
}
