package com.example.livraria1;

public class AutorModel {
    private String nome;
    private int ID;


    public AutorModel(String nome,int ID){
        this.nome = nome;
        this.ID = ID;

    }

    public AutorModel(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
