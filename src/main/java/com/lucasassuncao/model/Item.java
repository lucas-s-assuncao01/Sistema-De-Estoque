package com.lucasassuncao.model;

public class Item {

    private static int contador = 1;

    private int codigo;
    private String nome;
    private double preco;

    public Item(String nome, double preco) {
        this.codigo = contador++;
        this.nome = nome;
        this.preco = preco;
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
