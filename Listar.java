package com.example.livraria1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class Listar extends AppCompatActivity {
    private ListView lista;
    private Button botaoVoltar;
    private Button Excluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_livro);

        lista = (ListView)findViewById(R.id.list);
        botaoVoltar = (Button)findViewById(R.id.voltar);
        Excluir = (Button)findViewById(R.id.Excluir);

        LivroAdapter adapter = new LivroAdapter(getApplicationContext(),null);
        lista.setAdapter(adapter);
        adapter.atualiza();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent infolivro = new Intent(Listar.this,InfoLivro.class);
                int i = (int )id;
                infolivro.putExtra("id" , Integer.toString(i));
                finish();
                startActivity(infolivro);
            }
        });

        Excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Excluir = new Intent(Listar.this,InfoLivro.class);
                startActivity(Excluir);
            }
        });

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
