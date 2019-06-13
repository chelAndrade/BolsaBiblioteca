package com.example.livraria1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.*;

import static android.provider.MediaStore.Audio.Playlists.Members._ID;

public class ListarLivro extends Activity {
    private ListView lista;
    private Button botaoVoltar;

    private Button editar;
    private Button excluir;
    private Banco BancoDbHelper;

    private EditText titulo;
    private EditText editora;
    private EditText ano;

    private AlertDialog alerta1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_livro);

        lista = (ListView) findViewById(R.id.list);
        botaoVoltar = (Button) findViewById(R.id.voltar);


        editar = findViewById(R.id.Editar);
        excluir = findViewById(R.id.Excluir);
        BancoDbHelper = new Banco(getBaseContext());

        titulo = findViewById(R.id.titulo);
        editora = findViewById(R.id.editora);
        ano = findViewById(R.id.ano);

        //alerta1 = findViewById(R.id.alerta);


        LivroAdapter adapter = new LivroAdapter(getApplicationContext(), null);
        lista.setAdapter(adapter);
        adapter.atualiza();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent infolivro = new Intent(ListarLivro.this, MenuLivro.class);
                int i = (int) id;
                infolivro.putExtra("id", Integer.toString(i));
                startActivity(infolivro);
                finish();
            }
        });

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListarLivro.this, MenuLivro.class);
                startActivity(i);
                finish();
            }

        });


        /*private void alerta(){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Autor");
            builder.setMessage("Deseja exxluir o autor");
            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(ListarLivro.this, "", Toast.LENGTH_SHORT).show();

                }
            });
            builder.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(ListarLivro.this,"",Toast.LENGTH_SHORT).show();
                }
            });

            alerta1 = builder.create();

            alerta.show();
        }*/


    }
}
