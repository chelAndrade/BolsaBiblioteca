package com.example.livraria1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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


    }
}
