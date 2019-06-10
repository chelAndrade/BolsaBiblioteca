package com.example.livraria1;

class LivroModel {
    private String titulo;
    private String editora;
    private String autor;
    private String ano;
    //private int ID;

    public LivroModel(String titulo, String autor, String editora,String ano){
        this.titulo = titulo;
        this.editora = editora;
        this.autor = autor;
        this.ano = ano;
        //this.ID = ID;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAno(){return ano;}

    public void setAno(String ano) {
        this.ano = ano;
    }
}
