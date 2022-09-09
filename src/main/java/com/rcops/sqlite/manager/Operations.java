package com.rcops.sqlite.manager;

import java.sql.*;

public class Operations {

    static Connection conn = null;
    static Statement statement;
    static ResultSet rs;
    public static void connect(String databasePath) {
        try {
            // db parameters
            String url = "jdbc:sqlite:" + databasePath;
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Conexão com o banco de dados estabelecida!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
//        } finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException ex) {
//                System.out.println(ex.getMessage());
//            }
//        }
    }
    public static void disconnect(){
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Conexão encerrada com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void listTables(){
        try{
            statement = conn.createStatement();
            statement.setQueryTimeout(30);
            rs = statement.executeQuery("SELECT name FROM sqlite_master WHERE type = 'table' AND name NOT LIKE 'sqlite_%' ORDER BY 1;");
            System.out.println("===== LISTA DE TABELAS =====");
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
