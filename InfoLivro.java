package com.example.livraria1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InfoLivro extends AppCompatActivity {
    private EditText titulo;
    private EditText autor;
    private EditText editora;
    private EditText ano;
    private Button editar;
    private Button excluir;
    private Button voltar;
    private Banco BancoDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_livro);
        Intent i = getIntent();
        String id = i.getStringExtra("id");
        final int j = Integer.parseInt(id);

        titulo = findViewById(R.id.titulo);
        autor = findViewById(R.id.autor);
        editora = findViewById(R.id.editora);
        ano = findViewById(R.id.ano);

        editar = findViewById(R.id.Editar);
        excluir = findViewById(R.id.Excluir);
        voltar = findViewById(R.id.voltar);

        BancoDbHelper = new Banco(getBaseContext());

        LivroAdapter adapter = new LivroAdapter(getApplicationContext(),null);
        LivroModel livro =  adapter.getLivro(j);
        titulo.setText(livro.getTitulo());
        autor.setText(livro.getAutor());
        editora.setText(livro.getEditora());
        ano.setText(livro.getAno());

        /*excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Banco db = new Banco(getApplicationContext());
                db.deletar(j);
                LivroAdapter adapter = new LivroAdapter(getBaseContext(), null);
                adapter.atualiza();
                Toast toast = Toast.makeText(getApplicationContext(), "Excluido com Sucesso", Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }
        });*/

        /*excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = null;
                BancoDbHelper.deletaLivro(Integer.parseInt(_ID), db);
                Intent intent = new Intent(InfoLivro.this, ListarLivro.class);
                startActivity(intent);
                finish();
            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BancoDbHelper.alteraLivro(Integer.parseInt(_ID), titulo.getText().toString(),
                        editora.getText().toString(), ano.getText().toString());
                Intent intent = new Intent(InfoLivro.this, ListarLivro.class);
                startActivity(intent);
                finish();

            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulos = titulo.getText().toString();
                String autores = autor.getText().toString();
                String editoras = editora.getText().toString();
                String anos = ano.getText().toString();
                LivroModel livros = new LivroModel(titulos,autores,editoras,anos);
                Banco db = new Banco(getApplicationContext());
                db.editar(livros,j);
                LivroAdapter adapter = new LivroAdapter(getBaseContext(), null);
                adapter.atualiza();
                Toast toast = Toast.makeText(getApplicationContext(), "Editado com Sucesso", Toast.LENGTH_SHORT);
                toast.show();

                finish();
            }
        });*/

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
