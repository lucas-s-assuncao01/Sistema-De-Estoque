package com.lucasassuncao.controller;

import com.lucasassuncao.model.Categoria;
import com.lucasassuncao.model.Item;

import java.io.*;
import java.util.ArrayList;

public class BancoDeDados {

    private final ArrayList<Categoria> categorias;

    public BancoDeDados() {
        ArrayList<String> linhas = lerArquivo();

        if (linhas == null) {
            linhas = new ArrayList<>();
        }

        categorias = transformarLinhasEmObjetos(linhas);
    }

    public ArrayList<String> lerArquivo() {
        try {
            InputStream is = new FileInputStream("itens.txt");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            ArrayList<String> linhas = new ArrayList<>();
            String linha = br.readLine();

            while (linha != null) {

                if (!linha.trim().isEmpty()) {
                    linhas.add(linha);
                    System.out.println(linha);
                }

                linha = br.readLine();
            }

            br.close();
            isr.close();
            is.close();

            System.out.println("");
            System.out.println("Finalizei de ler o arquivo!");
            System.out.println("");

            return linhas;

        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo!");
            System.out.println("");
            return new ArrayList<>();
        }
    }

    public ArrayList<Categoria> transformarLinhasEmObjetos(ArrayList<String> linhas) {

        String[] dados;
        String nome;
        double preco;
        String categoria;

        ArrayList<Categoria> categorias = new ArrayList<>();

        for (int i = 0; i < linhas.size(); i++) {

            dados = linhas.get(i).split(",");

            if (dados.length < 3) {
                System.out.println("Linha inválida: " + linhas.get(i));
                continue;
            }

            nome = dados[0].trim();
            preco = Double.parseDouble(dados[1].trim().replace(",", "."));
            categoria = dados[2].trim();

            Item item = new Item(nome, preco);

            Categoria c = null;
            boolean existe = false;

            for (int j = 0; j < categorias.size(); j++) {
                if (categorias.get(j).getNome().equals(categoria)) {
                    categorias.get(j).adicionarItem(item);
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                c = new Categoria(categoria);
                c.adicionarItem(item);
                categorias.add(c);
            }
        }

        return categorias;
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public void escreverArquivo(Item item, String categoria) {
        try {
            OutputStream os = new FileOutputStream("itens.txt", true);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            String linha = item.getNome() + "," + item.getPreco() + "," + categoria;

            bw.write(linha);
            bw.newLine();

            bw.close();
            osw.close();
            os.close();

        } catch (Exception e) {
            System.out.println("Não foi possível escrever no arquivo de texto.");
        }
    }

    public void adicionarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    public boolean categoriaExiste(String nome) {

        for (Categoria c : categorias) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public boolean itemExiste(String nomeItem, String nomeCategoria) {

        for (Categoria c : categorias) {

            if (c.getNome().equalsIgnoreCase(nomeCategoria)) {

                for (Item i : c.getItens()) {
                    if (i.getNome().equalsIgnoreCase(nomeItem)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public Item buscarItemPorNome(String nome) {

        for (Categoria c : categorias) {

            for (Item i : c.getItens()) {

                if (i.getNome().equalsIgnoreCase(nome)) {
                    return i;
                }
            }
        }

        return null;
    }

    public boolean removerItem(String nomeItem) {

        for (Categoria c : categorias) {

            for (Item i : c.getItens()) {

                if (i.getNome().equalsIgnoreCase(nomeItem)) {

                    c.getItens().remove(i);
                    salvarTudoNoArquivo();
                    return true;
                }
            }
        }

        return false;
    }

    public void salvarTudoNoArquivo() {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("itens.txt"));

            for (Categoria c : categorias) {
                for (Item i : c.getItens()) {

                    String linha = i.getNome() + "," + i.getPreco() + "," + c.getNome();
                    bw.write(linha);
                    bw.newLine();
                }
            }

            bw.close();

        } catch (Exception e) {
            System.out.println("Erro ao salvar arquivo");
        }
    }
}
