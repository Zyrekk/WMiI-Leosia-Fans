import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DbConnector.connection()) {
            Statement stmt = conn.createStatement();
            String q2 = "INSERT INTO tmp (imie, id) VALUES (?,?)";
            PreparedStatement pstmt2 = conn.prepareStatement(q2);
            pstmt2.setString(1, "Marcin");
            pstmt2.setInt(2, 1);
            ResultSet rset = stmt.executeQuery("SELECT version()");
            pstmt2.executeUpdate();
            pstmt2.close();
        }
        catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
    }
}
