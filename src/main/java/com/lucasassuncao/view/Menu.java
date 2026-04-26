package com.lucasassuncao.view;

import com.lucasassuncao.controller.BancoDeDados;
import com.lucasassuncao.model.Categoria;
import com.lucasassuncao.model.Item;

import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);
    BancoDeDados banco = new BancoDeDados();

    private void adicionarCategoria() {

        System.out.print("Nome da categoria: ");
        String nome = scanner.nextLine();

        if (banco.categoriaExiste(nome)) {
            System.out.println("Essa categoria já existe!");
            return;
        }

        Categoria categoria = new Categoria(nome);
        banco.adicionarCategoria(categoria);

        System.out.println("Categoria adicionada!");
    }

    private void buscarItem() {

        System.out.print("Nome do item: ");
        String nome = scanner.nextLine();

        Item item = banco.buscarItemPorNome(nome);

        if (item == null) {
            System.out.println("Item não encontrado.");
        } else {
            System.out.println("Item encontrado:");
            System.out.println("Código: " + item.getCodigo());
            System.out.println("Nome: " + item.getNome());
            System.out.println("Preço: " + item.getPreco());
        }
    }

    private void removerItem() {

        System.out.print("Nome do item para remover: ");
        String nome = scanner.nextLine();

        boolean removido = banco.removerItem(nome);

        if (removido) {
            System.out.println("Item removido com sucesso!");
        } else {
            System.out.println("Item não encontrado.");
        }
    }

    public void iniciar() {

        int opcao;

        do {
            System.out.println("====== MENU ======");
            System.out.println("1 - Listar categorias");
            System.out.println("2 - Listar itens");
            System.out.println("3 - Adicionar item");
            System.out.println("4 - Adicionar categoria");
            System.out.println("5 - Buscar item");
            System.out.println("6 - Remover item");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    for (Categoria c : banco.getCategorias()) {
                        System.out.println("Código: " + c.getCodigo() + " | Nome: " + c.getNome());
                    }
                    break;

                case 2:
                    for (Categoria c : banco.getCategorias()) {
                        System.out.println("\nCategoria: " + c.getNome());

                        for (Item i : c.getItens()) {
                            System.out.println(" - " + i.getCodigo() + " | " + i.getNome() + " | R$ " + i.getPreco());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Nome do item: ");
                    String nome = scanner.nextLine();

                    System.out.print("Preço: ");
                    String precoStr = scanner.nextLine();
                    double preco = Double.parseDouble(precoStr.replace(",", "."));

                    System.out.print("Categoria: ");
                    String cat = scanner.nextLine();

                    if (banco.itemExiste(nome, cat)) {
                        System.out.println("Esse item já existe nessa categoria!");
                        return;
                    };

                    Item item = new Item(nome, preco);

                    banco.escreverArquivo(item, cat);

                    System.out.println("Item adicionado!");
                    break;

                case 4:
                    adicionarCategoria();
                    break;

                case 5:
                    buscarItem();
                    break;

                case 6:
                    removerItem();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

            System.out.println();

        } while (opcao != 0);

        scanner.close();
    }
}
