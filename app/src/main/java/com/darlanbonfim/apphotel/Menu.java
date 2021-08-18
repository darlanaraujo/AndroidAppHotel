package com.darlanbonfim.apphotel;

import static android.system.Os.close;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Intent intent;
    ActivityOptionsCompat animacao;

    // Atributos do menu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Context contexto;

    public void setToolbar(Context context){
        contexto = context;
        // Ligação dos objetos com os atributos da tela;
        drawerLayout = findViewById(R.id.draw_layout); // Corpo do menu;
        navigationView = findViewById(R.id.nav_view); // Topo do menu;
        toolbar = findViewById(R.id.toolbar); // Barra de menu suerior com icone para chamar a tela;

        // Comando para adicionar a barra do menu;
        setSupportActionBar(toolbar);

        // Comando que permite mostrar a barra lateral do menu na tela sobre a tela atual;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.txt_abrir_navegacao, R.string.txt_fechar_navegacao);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Comando para colocar uma seleção ao clicar no menu;
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        android.view.Menu menu = navigationView.getMenu();
        menu.findItem(R.id.menu_logoff).setVisible(false);
        menu.findItem(R.id.menu_MinhaConta).setVisible(false);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        // Instanciamento da classe Menu que permite o uso do método setMenu();
        //Menu dados = new Menu();
        setMenu(menuItem, this);

        // Comando para fechar a barra lateral do menu depois do click;
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    public void setMenu(@NonNull MenuItem menuItem, Context context){

        switch (menuItem.getItemId()) {
            case R.id.menu_home:

                setAnimacao(context, TelaPrincipal.class);
                break;

            case R.id.menu_hotel:

                setAnimacao(context, TelaHotel.class);
               break;

            case R.id.menu_reserva:

                setAnimacao(context, TelaReserva.class);
                break;

            case R.id.menu_servicos:

                setAnimacao(context, TelaServicos.class);
                break;

            case R.id.menu_acomodacoes:

                setAnimacao(context, TelaAcomodacoes.class);
                break;

            case R.id.menu_explore:

                setAnimacao(context, TelaExplore.class);
                break;

            case R.id.menu_contato:

                setAnimacao(context, TelaContato.class);
                break;

            case R.id.menu_login:

                setAnimacao(context, TelaLogin.class);
                break;

            case R.id.menu_facebook:
                setNavegacao(context,"https://github.com/darlanaraujo");
                break;

            case R.id.menu_instagram:
                setNavegacao(context,"https://github.com/darlanaraujo");
                break;

            case R.id.menu_twitter:
                setNavegacao(context,"https://github.com/darlanaraujo");
                break;

            case R.id.menu_sair:
                AlertDialog.Builder pop = new AlertDialog.Builder(context);
                pop.setIcon(R.drawable.icone_g);
                pop.setTitle("Confirmação!");
                pop.setMessage("Deseja Fechar o Aplicativo?");

                pop.setNegativeButton("Voltar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                pop.setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                pop.show();
                break;
        }

    }

    public void setAnimacao(Context context, Class tela){
        intent = new Intent(context, tela);
        ActivityOptionsCompat animacao = ActivityOptionsCompat.makeCustomAnimation(context, R.anim.fade_in, R.anim.anim_cair);
        ActivityCompat.startActivity(context, intent, animacao.toBundle());

        try {
            finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void setNavegacao(Context context, String caminho){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(caminho));
        Toast.makeText(context, "Você será direcionado para nossa página!", Toast.LENGTH_LONG).show();
        //startActivity(intent);
    }

    @Override
    public void finish(){
        super.finish();

    }

}
