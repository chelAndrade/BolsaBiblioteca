package com.example.livraria1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Toolbar*/ toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /*DrawerLayout*/ drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        //toggle.syncState();

        /*NavigationView*/ navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(getApplicationContext(),	item.getTitle() + " selected", Toast.LENGTH_SHORT).show();

        switch (item.getItemId()) {
            case R.id.listar_livro:
                Toast.makeText(getBaseContext(),"Listar Livro",Toast.LENGTH_SHORT).show();
                break;
            case R.id.inserir_livro:
                Toast.makeText(getBaseContext(),"Listar Autor",Toast.LENGTH_SHORT).show();
                break;
            case R.id.listar_Autores:
                Toast.makeText(getBaseContext(),"Inserir Livro",Toast.LENGTH_SHORT).show();
                break;
            case R.id.inserir_autor:
                Toast.makeText(getBaseContext(),"Inserir Autor",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inserirLivros) {
            Intent listarlivro = new Intent(this,MenuLivro.class);
            startActivity(listarlivro);

        } else if (id == R.id.nav_listarLivros) {
            Intent listarlivro = new Intent(this,ListarLivro.class);
            startActivity(listarlivro);

        } else if (id == R.id.nav_inserirAutores) {
            Intent listarautor = new Intent(this,MenuAutor.class);
            startActivity(listarautor);

        } else if (id == R.id.nav_listarAutores) {
            Intent listarautor = new Intent(this,ListarAutor.class);
            startActivity(listarautor);

        }

        /*DrawerLayout*/ drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
