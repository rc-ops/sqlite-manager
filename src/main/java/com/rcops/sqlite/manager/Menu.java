package com.rcops.sqlite.manager;

import java.util.Scanner;

public class Menu {
    protected static void mostrarMenu() {
        System.out.println("Bem-vindo! Por favor insira o caminho do banco de dados no seguinte formato");
        System.out.println("C:\\Users\\YourUserName\\Documents\\banco-dados.db");
        Scanner sc = new Scanner(System.in);
        System.out.print("Caminho: ");
        String path = sc.nextLine();
        Operations.connect(EditPath.ChangeBackSlashes(path));

        boolean selected = false;
        while (!selected) {
            System.out.println("==== Menu de opções ====");
            System.out.println("1 - Ver tabelas disponíveis");
            System.out.println("2 - Consultar tabela");
            System.out.println("3 - Sair");
            System.out.print("Opção: ");

            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    Operations.listTableNames();
                    break;
                case 2:
                    System.out.println("Insira o nome da tabela a ser consultada");
                    System.out.print("Tabela: ");
                    String tableName = sc.next();
                    Operations.listColumnNames(tableName);
                    System.out.println("O que deseja fazer com a tabela selecionada? ");
                    System.out.println("1 - Inserir um novo registro");
                    System.out.println("2 - Atualizar um registro");
                    System.out.println("3 - Sair");
                    break;
                case 3:
                    Operations.disconnect();
                    selected = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
