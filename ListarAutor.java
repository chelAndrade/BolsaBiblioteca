package com.example.livraria1;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class ListarAutor extends AppCompatActivity {
    private ListView lista;
    private Button botaoVoltar;

    private Button alertDialog;

    private Button editar;
    private Button excluir;
    private Banco BancoDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_autor);

        lista = (ListView) findViewById(R.id.list);
        botaoVoltar = (Button) findViewById(R.id.voltar);

        // alertDialog = (Button)findViewById(R.id.buttonTest);


        editar = findViewById(R.id.Editar);
        excluir = findViewById(R.id.Excluir);
        BancoDbHelper = new Banco(getBaseContext());


        AutorAdapter adapter = new AutorAdapter(getApplicationContext(), null);
        lista.setAdapter(adapter);
        adapter.atualizaAutores();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent infolivro = new Intent(ListarAutor.this, MenuAutor.class);
                int i = (int) id;
                infolivro.putExtra("id", Integer.toString(i));
                startActivity(infolivro);
                finish();
            }
        });


        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListarAutor.this, MenuAutor.class);
                startActivity(i);
                finish();
            }
        });
    }
}

