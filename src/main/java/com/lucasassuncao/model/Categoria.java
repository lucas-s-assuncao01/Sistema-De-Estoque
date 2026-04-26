package com.lucasassuncao.model;

import java.util.ArrayList;

public class Categoria {

    private static int contador = 1;

    private int codigo;
    private String nome;
    private ArrayList<Item> itens;


    public Categoria(String nome) {
        this.codigo = contador++;
        this.nome = nome;
        itens = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Item> itens) {
        this.itens = new ArrayList<>(itens);
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public void removerItem(Item item) {
        if (itens.remove(item)) {
            System.out.printf(
                    "Item '%s' (código: %d) removido da categoria '%s' com sucesso!\n",
                    item.getNome(),
                    item.getCodigo(),
                    this.nome
            );
        } else {
            System.out.println("Item não encontrado na categoria.");
        }
    }
}

