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

    }

    public String getUserDescription(String login) throws SQLException {
        String res = "";

        rset = stmt.executeQuery("SELECT opisUzytk FROM Uzytkownicy WHERE loginUzytk = '" + login + "';");
        rset.next();

        return rset.getString(1);
    }

    public void closeConnection() throws SQLException {
        rset.close();
        stmt.close();
        c.close();
    }




}
