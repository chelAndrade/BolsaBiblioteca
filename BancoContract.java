package com.example.livraria1;

import android.provider.BaseColumns;

public class BancoContract {
    public static final String TEXT_TYPE = " TEXT";
    public static final String INT_TYPE = " INTEGER";
    public static final String SEP = ",";
    public static final String SQL_CREATE_LIVRO = "CREATE TABLE " + Livro.TABLE_NAME + " (" +
            Livro._ID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT" + SEP +
            Livro.COLUMN_NAME_TITULO + TEXT_TYPE +" UNIQUE " + SEP +
            Livro.COLUMN_NAME_AUTOR + TEXT_TYPE + SEP +
            Livro.COLUMN_NAME_EDITORA + TEXT_TYPE + SEP +
            Livro.COLUMN_NAME_ANO + TEXT_TYPE + ")";

            //Livro."CONSTRAINT fk_LivAutor FOREIGN KEY  (ID_Autor) REFERENCES Livro.ID_Autor)";


    public static final String SQL_DROP_LIVRO = "DROP TABLE IF EXISTS " + Livro.TABLE_NAME;

    public static final String SQL_CREATE_AUTOR = "CREATE TABLE " + BancoContract.Autor.TABLE_NAME + " (" +
            //BancoContract.Autor._ID + INT_TYPE +" FOREIGN KEY AUTOINCREMENT" + SEP +
            BancoContract.Autor._ID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT" + SEP +
            BancoContract.Autor.COLUMN_NAME_NOME + TEXT_TYPE + " UNIQUE " + ")";
    public static final String SQL_DROP_AUTOR = "DROP TABLE IF EXISTS " + BancoContract.Autor.TABLE_NAME;

    /*public static final String SQL_CREATE_AUTOR = "CREATE TABLE" + Autor.TABLE_AUTOR + "(" +
           Autor._ID + INT_TYPE + "PRIMARY KEY AUTOINCREMENT" + SEP +
            Autor.COLUMN_AUTOR + TEXT_TYPE +  "UNIQUE"*/

    public BancoContract(){}

    public static Object getReadableDatabase() {
        return null;
    }


    public static final class Livro implements BaseColumns {
        public static final String TABLE_NAME = "livro";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_AUTOR = "autor";
        public static final String COLUMN_NAME_EDITORA = "editora";
        public static final String COLUMN_NAME_ANO = "ano";

    }

    public static final class Autor implements  BaseColumns{
        public static final String TABLE_NAME = "autor";
        public static final String COLUMN_NAME_NOME = "nome";

    }
}
