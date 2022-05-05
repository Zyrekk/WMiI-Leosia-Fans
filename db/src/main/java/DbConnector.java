import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private static String url1= "jdbc:postgresql://sxterm.mat.umk.pl/konradzyra?user=konradzyra&password=haslo!&ssl=false";
    public static Connection connection() throws SQLException {
        Connection conn = DriverManager.getConnection(url1);
        return conn;
    }

}
