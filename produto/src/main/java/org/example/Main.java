package org.example;

import java.util.*;

public class Main {
    private static Map<Integer, Produto> produtos = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Buscar produto por código");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = lerOpcao(scanner);

            switch (opcao) {
                case 1:
                    cadastrarProduto(scanner);
                    break;
                case 2:
                    buscarProduto(scanner);
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 3);

        scanner.close();
    }

    private static int lerOpcao(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
            scanner.next(); // descarta a entrada inválida
            return -1; // Retorna um valor inválido para repetir o loop
        }
    }

    private static void cadastrarProduto(Scanner scanner) {
        int codigo;
        do {
            codigo = gerarCodigoAleatorio();
        } while (produtos.containsKey(codigo)); // Garante que o código seja único

        scanner.nextLine();  // Consumir a nova linha

        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        double preco = lerPrecoProduto(scanner);

        Produto produto = new Produto(codigo, nome, preco);
        produtos.put(codigo, produto);
        System.out.println("Produto cadastrado com sucesso! Código: " + codigo);
    }

    private static int gerarCodigoAleatorio() {
        Random random = new Random();
        return random.nextInt(9000) + 1000; // Gera um número entre 1000 e 9999
    }

    private static double lerPrecoProduto(Scanner scanner) {
        double preco;
        while (true) {
            System.out.print("Digite o preço do produto: ");
            try {
                preco = scanner.nextDouble();
                if (preco <= 0) {
                    System.out.println("Preço inválido. O preço deve ser maior que zero.");
                } else {
                    break; // Sai do loop se o preço for válido
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número válido para o preço.");
                scanner.next(); // descarta a entrada inválida
            }
        }
        return preco;
    }



    private static void buscarProduto(Scanner scanner) {
        System.out.print("Digite o código do produto: ");
        int codigo = scanner.nextInt();



        Produto produto = produtos.get(codigo);
        if (produto != null) {
            System.out.println(" ");
            System.out.println("=========================================================");
            System.out.println("Produto encontrado: " + produto);
            System.out.println("=========================================================");
            System.out.println(" ");

        } else {
            System.out.println("Produto não encontrado.");
        }
    }
}