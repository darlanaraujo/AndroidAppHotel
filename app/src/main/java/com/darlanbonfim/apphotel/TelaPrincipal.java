package com.darlanbonfim.apphotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.motion.widget.Animatable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;

public class TelaPrincipal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Atributos variados
    ImageView imgSlide;
    Intent intent;

    // Atributos do menu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    // Animação
    Animation animItens;
    LinearLayout layConteudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);

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

        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        //getSupportActionBar().setTitle(""); // Comando para deixar o titulo vazio;


        // Animação;
        animItens = AnimationUtils.loadAnimation(this, R.anim.anim_itens);
        layConteudo = findViewById(R.id.layConteudo);
        layConteudo.setAnimation(animItens);


        // Comando que permite gerar um slide nas fotos;
        imgSlide = findViewById(R.id.imgSlide);
        AnimationDrawable animacao = (AnimationDrawable) imgSlide.getDrawable();
        animacao.start();

    }

    // BOTÕES DOS MENUS NA TELA ====================================================================
    public void setHotel(View view){
        setAnimacao(TelaHotel.class);
    }

    public void setReserva(View view){
        setAnimacao(TelaReserva.class);
    }

    public void setServicos(View view){
        setAnimacao(TelaServicos.class);
    }

    public void setAcomodacoes(View view){
        setAnimacao(TelaAcomodacoes.class);
    }

    public void setExplore(View view){
        setAnimacao(TelaExplore.class);
    }

    public void setContato(View view){
        setAnimacao(TelaContato.class);
    }

    // FERRAMENTAS =================================================================================
    public void setAnimacao(Class tela){
        intent = new Intent(this, tela);
        ActivityOptionsCompat animacao = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.anim_cair);
        ActivityCompat.startActivity(this, intent, animacao.toBundle());

    }

    // Esse método foi implementado automaticamente ao adicionar o comando para mostrar uma seleção no menu ao escolher uma opção;
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        // Instanciamento da classe Menu que permite o uso do método setMenu();
        Menu dados = new Menu();
        dados.setMenu(menuItem, this);

        // Comando para fechar a barra lateral do menu depois do click;
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }


    // Método que faz com que a barra do menu superior sobreponha a barra de navegação
    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }

    /** Método que oculta a barra de navegação no aparelho;
     * Esse comando é chamada no método onResume() por ser resposável por execultar o comando no
     * momento é que a classe é chamada.
     * A barra de navegação é oculta, mas pode ser acessada passando o dedo na tela de baixo para
     * cima. Ela fica visivel por alguns segundos e volta a ocultar-se.
     */
    @Override
    protected void onResume() {
        super.onResume();

        // Comandoqueocultaabarradenavegação;
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // Ocultaabarrainferior
                // | View.SYSTEM_UI_FLAG_FULLSCREEN // Ocultaabarrasuperior;
                //|View.SYSTEM_UI_FLAG_IMMERSIVE // Faz a barra inferior aparecer permanete se passar o dedo debaixo para cima na tela;
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY; // Faz a barra inferior aparecer por algum tempo se passar o dedo debaixo para cima na tela;

        decorView.setSystemUiVisibility(uiOptions);
    }

}