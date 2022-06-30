package com.example.hangman;

import java.sql.*;

public class DbConnection {
    Connection c;
    Statement stmt;
    ResultSet rset;

    public DbConnection() throws SQLException {
        c = null;
        rset = null;
        c = DriverManager
                .getConnection("jdbc:mysql://remotemysql.com/aeCwkdZjzZ",
                        "aeCwkdZjzZ", "jaCfRCsMNb");
        stmt = c.createStatement();

    }

    public boolean checkIfUserExists(String login) throws SQLException {
        boolean val;

        rset = stmt.executeQuery("SELECT COUNT(loginUzytk) FROM Uzytkownicy WHERE loginUzytk = '" + login + "';");
        rset.next();
        val = rset.getInt(1) != 0;

        return val;
    }

    public boolean checkIfPasswordValid(String login, String password) throws SQLException {
        boolean val;

        rset = stmt.executeQuery("SELECT COUNT(loginUzytk) FROM Uzytkownicy WHERE loginUzytk = '" + login + "' AND hasloUzytk = '" + password + "';");
        rset.next();
        val = rset.getInt(1) != 0;

        return val;
    }

    public void addUser(String login, String password, String description) throws SQLException {
        stmt.executeUpdate("INSERT INTO Uzytkownicy(loginUzytk, hasloUzytk, opisUzytk) VALUES ('" +
                login +"','" +
                password + "','" +
                description + "');");

        // zerowanie najwyższej punktacji trybu speedrun nowego uzytkownika
        stmt.executeUpdate("INSERT INTO Ranking(loginUzytk, liczbaPkt) VALUES ('" + login + "', 0);");
    }

    public void changeUserPassword(String login, String password) throws SQLException {
        stmt.executeUpdate("UPDATE Uzytkownicy SET hasloUzytk='" + password + "' WHERE loginUzytk='" + login + "';");
    }

    public void changeUserDescription(String login, String description) throws SQLException {
        stmt.executeUpdate("UPDATE Uzytkownicy SET opisUzytk='" + description + "' WHERE loginUzytk='" + login + "';");
    }

    public String getUserDescription(String login) throws SQLException {
        String res = "";

        rset = stmt.executeQuery("SELECT opisUzytk FROM Uzytkownicy WHERE loginUzytk = '" + login + "';");
        rset.next();

        return rset.getString(1);
    }

    public int countNormalPoints(String login) throws SQLException {
        rset = stmt.executeQuery("SELECT COUNT(idHasla) FROM RozwiazaneHasla WHERE loginUzytk = '" + login + "';");
        rset.next();

        return rset.getInt(1);
    }

    public int getSpeedrunHighScore(String login) throws SQLException {
        rset = stmt.executeQuery("SELECT liczbaPkt FROM Ranking WHERE loginUzytk = '" + login + "';");
        rset.next();

        return rset.getInt(1);
    }

    public String getTopNormalScore(int place, String type) throws SQLException {


        rset = stmt.executeQuery("SELECT loginUzytk, COUNT(idHasla) AS pkt FROM RozwiazaneHasla GROUP BY loginUzytk ORDER BY pkt DESC LIMIT 5;");

        //zliczanie wyniku według argumentu (n-te miejsce)
        for(int i=1; i<=place; i++){
            rset.next();
        }

        if(type == "login"){
            return rset.getString(1);
        }
        else if (type == "points"){
            return rset.getString(2);
        }
        else if (type == "both"){
            return rset.getString(1) + " (" + rset.getString(2) + ")";
        }
        else {
            return "<brak>";
        }

    }

    public String getTopSpeedrunScore(int place, String type) throws SQLException {
        rset = stmt.executeQuery("SELECT loginUzytk, liczbaPkt FROM Ranking ORDER BY liczbaPkt DESC LIMIT 5;");

        //zliczanie wyniku według argumentu (n-te miejsce)
        for(int i=1; i<=place; i++){
            rset.next();
        }

        // dopasowanie struktury zwracanego stringa do potrzeb kontrolera
        if(type == "login"){
            return rset.getString(1);
        }
        else if (type == "points"){
            return rset.getString(2);
        }
        else if (type == "both"){
            return rset.getString(1) + " (" + rset.getString(2) + ")";
        }
        else {
            return "<brak>";
        }
    }



    public void closeConnection() throws SQLException {
        rset.close();
        stmt.close();
        c.close();
    }




}
