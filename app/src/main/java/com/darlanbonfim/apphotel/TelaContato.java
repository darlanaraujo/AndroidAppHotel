package com.darlanbonfim.apphotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;

public class TelaContato extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Atributos do menu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    // Animações
    LinearLayout layConteudo;
    Animation animItens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_contato);

        // CONFIGURAÇÃO PADRÃO DAS TELAS ===========================================================

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

        // CONFIGURAÇÕES ESPECIFICAS DA TELA =======================================================

        // Animações;
        layConteudo = findViewById(R.id.layConteudo);
        animItens = AnimationUtils.loadAnimation(this, R.anim.anim_itens);
        layConteudo.setAnimation(animItens);

    }

    // CONFIGURAÇÃO PADRÃO DAS TELAS ===========================================================
    @Override
    public void finish(){
        super.finish();

        overridePendingTransition(R.anim.fade_in, R.anim.anim_cair);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        // Instanciamento da classe Menu que permite o uso do método setMenu();
        Menu dados = new Menu();
        dados.setMenu(menuItem, this);

        // Comando para fechar a barra lateral do menu depois do click;
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
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

    // CONFIGURAÇÕES ESPECIFICAS DA TELA =======================================================

}