package com.rcops.sqlite.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Operations {

    static Connection conn = null;

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
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
