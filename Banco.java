package com.example.livraria1;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import static android.app.Activity.RESULT_OK;

public class Banco extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "Biblioteca.db";
    private String id;
    private Cursor cursor;

    public Banco(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BancoContract.SQL_CREATE_AUTOR);
        db.execSQL(BancoContract.SQL_CREATE_LIVRO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(BancoContract.SQL_DROP_LIVRO);
        db.execSQL(BancoContract.SQL_CREATE_LIVRO);
        db.execSQL(BancoContract.SQL_DROP_AUTOR);
        db.execSQL(BancoContract.SQL_CREATE_AUTOR);//criar o banco para o autor
    }

    public Boolean salvar(LivroModel livro) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(BancoContract.Livro.COLUMN_NAME_TITULO, livro.getTitulo());
            values.put(BancoContract.Livro.COLUMN_NAME_AUTOR, livro.getAutor());
            values.put(BancoContract.Livro.COLUMN_NAME_EDITORA, livro.getEditora());
            values.put(BancoContract.Livro.COLUMN_NAME_ANO, livro.getAno());
            db.insert(BancoContract.Livro.TABLE_NAME, null, values);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Cursor atualizaLivro() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String[] visao = {
                    BancoContract.Livro._ID,
                    BancoContract.Livro.COLUMN_NAME_TITULO,
                    BancoContract.Livro.COLUMN_NAME_AUTOR,
                    BancoContract.Livro.COLUMN_NAME_EDITORA,
                    BancoContract.Livro.COLUMN_NAME_ANO
            };

            Cursor c = db.query(BancoContract.Livro.TABLE_NAME, visao, null, null, null, null, null);
            return c;

        } catch (Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
            return null;
        }
    }

    public LivroModel getLivro(int i) {
        try {
            //SQLiteDatabase db = this.getWritableDatabase();
            String[] visao = {
                    BancoContract.Livro._ID,
                    BancoContract.Livro.COLUMN_NAME_TITULO,
                    BancoContract.Livro.COLUMN_NAME_AUTOR,
                    BancoContract.Livro.COLUMN_NAME_EDITORA,
                    BancoContract.Livro.COLUMN_NAME_ANO
            };
            String sel = BancoContract.Livro._ID + "=?";
            String arg[] = {Integer.toString(i)};
            Cursor c = db.query(BancoContract.Livro.TABLE_NAME, visao, sel, arg, null, null, null);
            c.moveToFirst();
            LivroModel livro = new LivroModel(c.getString(c.getColumnIndex(BancoContract.Livro.COLUMN_NAME_TITULO)),
                    c.getString(c.getColumnIndex(BancoContract.Livro.COLUMN_NAME_AUTOR)), c.getString(
                    c.getColumnIndex(BancoContract.Livro.COLUMN_NAME_EDITORA)), c.getString(c.getColumnIndex(BancoContract.Livro.COLUMN_NAME_ANO)));
            return livro;

        } catch (Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
            return null;
        }
    }

    public void deletar(int i) {
        String onde = BancoContract.Livro._ID + "=" + i;
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BancoContract.Livro.TABLE_NAME, onde, null);
        db.close();
    }

    public void editar(LivroModel livro, int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BancoContract.Livro.COLUMN_NAME_TITULO, livro.getTitulo());
        values.put(BancoContract.Livro.COLUMN_NAME_AUTOR, livro.getAutor());
        values.put(BancoContract.Livro.COLUMN_NAME_EDITORA, livro.getEditora());
        Log.i("titulo", livro.getTitulo());
        String sel = BancoContract.Livro._ID + "=?";
        String arg[] = {Integer.toString(i)};
        try {
            db.update(BancoContract.Livro.TABLE_NAME, values, sel, arg);
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
    }

    public Cursor atualizaAutor() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String[] visao = {
                    BancoContract.Autor._ID,
                    BancoContract.Autor.COLUMN_NAME_NOME,

            };

            Cursor c = db.query(BancoContract.Autor.TABLE_NAME, visao, null, null, null, null, null);
            return c;

        } catch (Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
            return null;
        }
    }

    public ArrayList<String> getTodosAutores() {
        try {
            ArrayList<String> autor = new ArrayList<>();
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM " + BancoContract.Autor.TABLE_NAME, null);
            if (c != null && c.moveToFirst()) {
                do {
                    autor.add(c.getString(c.getColumnIndex(BancoContract.Autor.COLUMN_NAME_NOME)));
                } while (c.moveToNext());
            }
            return autor;
        } catch (Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
            return null;
        }

    }

    public Boolean salvarAutor(AutorModel autor) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(BancoContract.Autor.COLUMN_NAME_NOME, autor.getNome());
            db.insert(BancoContract.Autor.TABLE_NAME, null, values);

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public Cursor carregaDadoById(String id) {
        Cursor cursor;
        String[] campos = {BancoContract.Livro._ID, BancoContract.Livro.COLUMN_NAME_TITULO, BancoContract.Livro.COLUMN_NAME_ANO,
                BancoContract.Livro.COLUMN_NAME_EDITORA, BancoContract.Livro.COLUMN_NAME_AUTOR};
        String where = BancoContract.Livro._ID + "=" + id;
        db = this.getWritableDatabase();
        cursor = db.query(BancoContract.Livro.TABLE_NAME, campos, where, null, null, null, null, null);


        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoByIdAutor(String id) {
        Cursor cursor;
        String[] campos = {BancoContract.Autor._ID,BancoContract.Autor.COLUMN_NAME_NOME};
        String where = BancoContract.Autor._ID + "=" + id;
        db = this.getWritableDatabase();
        cursor = db.query(BancoContract.Autor.TABLE_NAME, campos, where, null, null, null, null, null);


        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    /*public void alteraLivro(int id, String titulo, String ano, String editora) {
        ContentValues values;
        String where;

        SQLiteDatabase db = this.getWritableDatabase();

        where = BancoContract.Livro._ID + "=" + id;

        values = new ContentValues();
        values.put(BancoContract.Livro.COLUMN_NAME_TITULO, titulo);
        //values.put(BancoContract.Livro.COLUMN_NAME_AUTOR, autor);
        values.put(BancoContract.Livro.COLUMN_NAME_EDITORA, editora);
        values.put(BancoContract.Livro.COLUMN_NAME_ANO, ano);

        db.update(BancoContract.Livro.TABLE_NAME, values, where, null);
        db.close();

    }

    public void deletaLivro(int id , SQLiteDatabase db) {
        String where = BancoContract.Livro._ID + "=" + id;
        db = this.getWritableDatabase();
        db.delete(BancoContract.Livro.TABLE_NAME, where, null);
        db.close();
    }*/

    public int alteraTitulo(int id, String titulo,String editora,String autor,String ano) {
        ContentValues values;
        String where;

        SQLiteDatabase db = this.getWritableDatabase();

        where = BancoContract.Livro._ID + "= ?";
        String[] whereargs = {Integer.toString(id)};


        values = new ContentValues();
        values.put(BancoContract.Livro.COLUMN_NAME_TITULO, titulo);
        values.put(BancoContract.Livro.COLUMN_NAME_EDITORA, editora );
        values.put(BancoContract.Livro.COLUMN_NAME_AUTOR, autor);
        values.put(BancoContract.Livro.COLUMN_NAME_ANO,ano);


        int r = db.update(BancoContract.Livro.TABLE_NAME, values, where, whereargs);
        db.close();
        return r;
    }

    public int deletaTitulo(int id,String titulo,String editora, String autor,String ano) {
        String where = BancoContract.Livro._ID + " = " + id;


        db = this.getWritableDatabase();
        //db = (SQLiteDatabase) BancoContract.getWritableDatabase();
        int r3 = db.delete(BancoContract.Livro.TABLE_NAME,where,null);
        db.close();
        return r3;
    }

    public int alteraAutor(int id, String nome) {
        ContentValues values;
        String where;

        SQLiteDatabase db = this.getWritableDatabase();

        where = BancoContract.Autor._ID + "= ?";
        String[] whereargs = {Integer.toString(id)};
        values = new ContentValues();
        values.put(BancoContract.Autor.COLUMN_NAME_NOME, nome);

        int r1 = db.update(BancoContract.Autor.TABLE_NAME, values, where, whereargs);
        db.close();
        return r1;
    }

    public int deletaAutor(int id, final String nome) {
        String where = BancoContract.Livro._ID + " = " + id;

        db = this.getWritableDatabase();
        //db = (SQLiteDatabase) BancoContract.getWritableDatabase();
        int r3 = db.delete(BancoContract.Autor.TABLE_NAME,where,null);
        /*if(autor == titulo){
            Toast toast = Toast.makeText(getApplicationContext(), "Este livro nao pode ser excluido", Toast.LENGTH_SHORT);
            toast.show();
        }*/
        db.close();
        return r3;
    }
    

}

