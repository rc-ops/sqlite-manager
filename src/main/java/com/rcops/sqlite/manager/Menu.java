package com.rcops.sqlite.manager;

import java.util.Scanner;

import static com.rcops.sqlite.manager.Operations.conn;

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
            System.out.println("2 - Mais opções");
            System.out.println("3 - Sair");
            System.out.print("Opção: ");

            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    Operations.listTables();
                    break;
                case 2:
                    while (!selected) {
                        System.out.println("==== Mostrando mais opções ====");
                        System.out.println("1 - Visualizar os registros");
                        System.out.println("2 - Inserir um novo registro");
                        System.out.println("3 - Atualizar um registro");
                        System.out.print("Opção: ");
                        opcao = sc.nextInt();

                        switch (opcao){
                            case 1:
                                System.out.println("Visualizando os registros");
                                break;
                            case 2:
                                System.out.println("Inserindo um novo registro");
                                selected = true;
                                break;
                            case 3:
                                System.out.println("Atualizando um registro");
                                selected = true;
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                        }
                    }
                    selected = true;
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
