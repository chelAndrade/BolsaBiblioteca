package com.example.livraria1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MenuAutor extends AppCompatActivity {

    private EditText nome;
    private Button cadastro;
    private Button listar;
    private Button voltar;
    private Banco BancoDbHelper;
    private String codigo;
    private Cursor cursor;
    private Button Excluir;
    //private Button Alerta;
    private AlertDialog alerta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__autor);

        nome = findViewById(R.id.nome);
        cadastro = findViewById(R.id.btncadastro1);
        Excluir = findViewById(R.id.btnExcluir);
        voltar = findViewById(R.id.Voltar);

        /*AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Autor");
        builder.setMessage("Deseja exclui este autor");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(MenuAutor.this,"sim=" + arg1, Toast.LENGTH_SHORT);
                }
                });

                buider.setNegativeButton("Nao", new DialogInterface.OnClickListener(){
                @Override
                  public void onClick(DialogInterface arg0, int arg1){
                   Toast.makeText(MenuAutor.this,"nao=" + arg1, Toast.LENGHT_SHORT);

            }
        });

        alerta = builfer.create();

        alerta.show();r

        }*/


        codigo = this.getIntent().getStringExtra("id");

        try{
            BancoDbHelper = new Banco(getApplicationContext());
            if (codigo == null) {
            cadastro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Nome = nome.getText().toString();
                    if (Nome.isEmpty()) {
                        nome.requestFocus();
                    } else {
                        AutorModel autor = new AutorModel(Nome);
                        if (BancoDbHelper.salvarAutor(autor)) {
                            Log.e("test", autor.getNome());
                            Toast toast = Toast.makeText(getApplicationContext(), "Adicionado com sucesso", Toast.LENGTH_SHORT);
                            toast.show();
                            AutorAdapter adapter = new AutorAdapter(getBaseContext(), null);
                            adapter.atualizaAutores();
                            BancoDbHelper.close();
                        } else {
                            nome.requestFocus();
                            Toast toast = Toast.makeText(getApplicationContext(), "Ja existe um autor com esse nome", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                }
            });
        } else {
            Cursor c2 = BancoDbHelper.carregaDadoByIdAutor(codigo);
            nome.setText(c2.getString(c2.getColumnIndexOrThrow(BancoContract.Autor.COLUMN_NAME_NOME)));
            cadastro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Nome = nome.getText().toString();
                    int r1 = BancoDbHelper.alteraAutor(Integer.parseInt(codigo), Nome);
                    if (r1 == 1) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Alterado com sucesso", Toast.LENGTH_SHORT);
                        toast.show();
                        Intent i = new Intent(MenuAutor.this, ListarAutor.class);
                        startActivity(i);
                        //finish();
                    }
                }
            });

            Cursor c = BancoDbHelper.carregaDadoByIdAutor(codigo);
            nome.setText(c.getString(c.getColumnIndexOrThrow(BancoContract.Autor.COLUMN_NAME_NOME)));


            Excluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Nome = nome.getText().toString();

                    BancoDbHelper.deletaAutor(Integer.parseInt(codigo), Nome);
                    Log.e("codigo:", codigo);
                    Toast toast = Toast.makeText(getApplicationContext(), "Deletado com sucesso", Toast.LENGTH_SHORT);
                    toast.show();
                    Intent i = new Intent(MenuAutor.this, ListarAutor.class);
                    startActivity(i);
                }
            });
        }
        }catch(Exception e){
            e.printStackTrace();
        }




        /*listar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent listar = new Intent(MenuAutor.this, ListarAutor.class);
                    startActivity(listar);
                }
            });*/

            /*Alerta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent alerta = new Intent(MenuAutor.this,Banco.class);
                    startActivity(alerta);
                }
            });*/



            voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MenuAutor.this, ListarAutor.class);
                    startActivity(i);
                    finish();
                }
            });
        }
    }
//}