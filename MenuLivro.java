package com.example.livraria1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MenuLivro extends AppCompatActivity {
    private EditText titulo;
    private Spinner autor;
    private EditText editora;
    private EditText ano;
    private Button cadastro;
    private Button Excluir;
    private Button voltar;
    private Banco BancoDbHelper;
    private String codigo;


    //static final int DATE_DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_livro);

        titulo = findViewById(R.id.titulo);

        editora = findViewById(R.id.editora);
        cadastro = findViewById(R.id.btncadastro);
        Excluir = findViewById(R.id.btnExcluir);
        voltar = findViewById(R.id.Voltar);


        codigo = this.getIntent().getStringExtra("id");

        ano = (EditText) findViewById(R.id.ano);
        String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
        ano.setText(currentDateTimeString);

        ArrayList<String> autores = new ArrayList<String>();

        autor = (Spinner) findViewById(R.id.Autor);
        try {
            BancoDbHelper = new Banco(getApplicationContext());
            autores = BancoDbHelper.getTodosAutores();
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, autores);
            autor.setAdapter(adapter);

            if (codigo == null) {
                cadastro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String Titulo = titulo.getText().toString();
                        String Editora = editora.getText().toString();
                        String Ano = ano.getText().toString();

                        if (Titulo.isEmpty()) {
                            titulo.requestFocus();
                        } else if (Editora.isEmpty()) {
                            editora.requestFocus();
                        } else if (Ano.isEmpty()) {
                            ano.requestFocus();

                        } else {
                            LivroModel livro = new LivroModel(Titulo, autor.getSelectedItem().toString(), Editora, Ano);
                            if (BancoDbHelper.salvar(livro)) {
                                Log.e("test", livro.getTitulo());
                                Toast toast = Toast.makeText(getApplicationContext(), "Adicionado com sucesso", Toast.LENGTH_SHORT);
                                toast.show();
                                LivroAdapter adapter = new LivroAdapter(getBaseContext(), null);
                                adapter.atualiza();
                                BancoDbHelper.close();
                            } else {
                                titulo.requestFocus();
                                Toast toast = Toast.makeText(getApplicationContext(), "Ja existe um livro com esse nome", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                    }
                });
            } else {
                Cursor c = BancoDbHelper.carregaDadoById(codigo);
                titulo.setText(c.getString(c.getColumnIndexOrThrow(BancoContract.Livro.COLUMN_NAME_TITULO)));
                editora.setText(c.getString(c.getColumnIndexOrThrow(BancoContract.Livro.COLUMN_NAME_EDITORA)));
                ano.setText(c.getString(c.getColumnIndexOrThrow(BancoContract.Livro.COLUMN_NAME_ANO)));// ano ter numerico e o fazer uma vericacao com ano corrente + 1

                // quando clicar voltar pra tela de listagem
                //gitHub viva sem tabaco fazer o download e testar o aplicativo

                autor.setSelection(adapter.getPosition(c.getString(c.getColumnIndexOrThrow(BancoContract.Livro.COLUMN_NAME_AUTOR))));

                cadastro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Titulo = titulo.getText().toString();
                        String Editora = editora.getText().toString();
                        String Autor = autor.getSelectedItem().toString();
                        String Ano = ano.getText().toString();


                        int r = BancoDbHelper.alteraTitulo(Integer.parseInt(codigo), Titulo,Editora,Autor,Ano);
                        if (r == 1) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Alterado com sucesso", Toast.LENGTH_SHORT);
                            toast.show();
                            Intent i = new Intent(MenuLivro.this, ListarLivro.class);
                            startActivity(i);
                            //finish();
                        }
                    }
                });

                Cursor c1 = BancoDbHelper.carregaDadoById(codigo);
                titulo.setText(c1.getString(c1.getColumnIndexOrThrow(BancoContract.Livro.COLUMN_NAME_TITULO)));
                editora.setText(c1.getString(c1.getColumnIndexOrThrow(BancoContract.Livro.COLUMN_NAME_EDITORA)));
                ano.setText(c1.getString(c1.getColumnIndexOrThrow(BancoContract.Livro.COLUMN_NAME_ANO)));

                autor.setSelection(adapter.getPosition(c1.getString(c1.getColumnIndexOrThrow(BancoContract.Livro.COLUMN_NAME_AUTOR))));

                Excluir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Titulo = titulo.getText().toString();
                        String Editora = editora.getText().toString();
                        String Autor = autor.getSelectedItem().toString();
                        String Ano = ano.getText().toString();
                        BancoDbHelper.deletaTitulo(Integer.parseInt(codigo),Titulo,Editora,Autor,Ano);
                        Log.e("codigo:",codigo);
                        Toast toast = Toast.makeText(getApplicationContext(), "Deletado com sucesso", Toast.LENGTH_SHORT);
                        toast.show();
                        Intent i = new Intent(MenuLivro.this, ListarLivro.class);
                        startActivity(i);
                        //finish();
                    }
                });
            }
        //}


        }catch(Exception e){
        e.printStackTrace();
        }

        /*listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listar = new Intent(MenuLivro.this, ListarLivro.class);
                startActivity(listar);
            }
        });*/

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuLivro.this, ListarLivro.class);
                startActivity(i);
                finish();
            }
        });
    }
}
