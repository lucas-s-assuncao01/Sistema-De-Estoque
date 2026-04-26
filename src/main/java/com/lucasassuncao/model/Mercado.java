package com.lucasassuncao.model;

import java.util.ArrayList;

public class Mercado {

    private String nome;
    private ArrayList<Categoria> categorias;

    public Mercado(String nome) {
        this.nome = nome;
        this.categorias = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Categoria> getCategorias() {
        return new ArrayList<>(categorias);
    }

    public void adicionarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    public void removerCategoria(int codigo) {
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getCodigo() == codigo) {
                categorias.remove(i);
                System.out.println("Categoria removida com sucesso!");
                return;
            }
        }
        System.out.println("Categoria não encontrada.");
    }

    public void apresentarCategorias() {
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
            return;
        }

        for (Categoria categoria : categorias) {
            System.out.println(
                    "Código da categoria: " + categoria.getCodigo() +
                            ", Nome: " + categoria.getNome()
            );
        }
    }

    public void apresentarItens() {
        if (categorias.isEmpty()) {
            System.out.println("Nenhum item cadastrado.");
            return;
        }

        for (Categoria categoria : categorias) {
            System.out.println("Categoria " + categoria.getCodigo() + " - " + categoria.getNome());

            for (Item item : categoria.getItens()) {
                System.out.println(
                        "Código do Item: " + item.getCodigo() +
                                ", Nome do Item: " + item.getNome() +
                                " e Preço do Item: " + item.getPreco()
                );
            }

            System.out.println();
        }
    }

    public void apresentarCategoriaPorCodigo(int codigo) {
        for (Categoria categoria : categorias) {

            if (categoria.getCodigo() == codigo) {

                System.out.println("Categoria: " + categoria.getNome());

                for (Item item : categoria.getItens()) {
                    System.out.println(
                            "Código: " + item.getCodigo() +
                                    ", Nome: " + item.getNome()
                    );
                }

                System.out.println();
                return;
            }
        }

        System.out.println("Categoria não encontrada.");
    }
}