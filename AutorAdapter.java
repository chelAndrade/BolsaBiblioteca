package com.example.livraria1;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class AutorAdapter extends CursorAdapter {
    private Banco db;

    public AutorAdapter(Context context, Cursor c) {
        super(context, c, 0);
        db = new Banco(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.listar_autores, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtAutor = (TextView) view.findViewById(R.id.autor);
        String autor = cursor.getString(cursor.getColumnIndexOrThrow(BancoContract.Autor.COLUMN_NAME_NOME));
        txtAutor.setText(autor);
    }

    public void atualizaAutores() {
        this.changeCursor(db.atualizaAutor());
    }

}
