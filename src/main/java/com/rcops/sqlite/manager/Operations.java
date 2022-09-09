package com.rcops.sqlite.manager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Operations {

    static Connection conn = null;
    static Statement statement;
    static ResultSet rs;
    public static void connect(String databasePath){
        try {
            // db parameters
            String url = "jdbc:sqlite:" + databasePath;
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Conexão com o banco de dados estabelecida!");
        } catch (SQLException e) {
            System.out.println("Banco de dados não encontrado.");
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
    public static void listTableNames(){
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

    public static void listColumnNames(String tableName){
        try{
            statement = conn.createStatement();
            statement.setQueryTimeout(30);
            rs = statement.executeQuery("SELECT * from " + tableName);
            ResultSetMetaData rsMetaData = rs.getMetaData();
            System.out.println("===== Mostrando as colunas da tabela " + tableName + " =====");
            int count = rsMetaData.getColumnCount();
            for (int i = 1; i <= count; i++){
                System.out.println(rsMetaData.getColumnName(i));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static List<String> retrieveColumnNames(String tableName){
        List<String> columnNames = new ArrayList<>();
        try{
            statement = conn.createStatement();
            statement.setQueryTimeout(30);
            rs = statement.executeQuery("SELECT * from " + tableName);
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int count = rsMetaData.getColumnCount();
            for (int i = 1; i <= count; i++){
                columnNames.add(rsMetaData.getColumnName(i));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return columnNames;
    }
    public static void listColumnContents(int operation, String tableName, String... columnNames){
        // 1 - Retorna os registros de todas as colunas
        // 2 - Retorna apenas das especificadas
        if (operation == 1){
            try{
                statement = conn.createStatement();
                statement.setQueryTimeout(30);
                rs = statement.executeQuery("SELECT * FROM " + tableName);
                System.out.println("===== Mostrando os registros de todas as colunas da tabela " + tableName + " =====");
                List<String> nomesColunas = retrieveColumnNames(tableName);
                while (rs.next()) {
                    for (String nome : nomesColunas) {
                        System.out.println(rs.getString(nome));
                    }
                }

            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
//        try{
//            statement = conn.createStatement();
//            statement.setQueryTimeout(30);
//            rs = statement.executeQuery("SELECT * from " + columnNames);
//            System.out.println("===== Mostrando as colunas da tabela " + columnNames + " =====");
//            while (rs.next()){
//                System.out.println("aa");
//            }
//        } catch (SQLException e){
//            System.out.println(e.getMessage());
//        }
    }
}
