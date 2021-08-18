package com.darlanbonfim.apphotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.os.Bundle;
//import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class TelaLogin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Atributos do menu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    // Atributos

    Button btnEntrarLogin, btnCadastrar;
    EditText txtUsuario, txtSenha;

    LinearLayout layLogin, layCadastro;
    Animation animItens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

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

        // Comando para dar animação na tela;
        layLogin = findViewById(R.id.layLogin);
        layCadastro = findViewById(R.id.layCadastro);
        animItens = AnimationUtils.loadAnimation(this, R.anim.anim_itens);
        layLogin.setAnimation(animItens);
        layCadastro.setAnimation(animItens);

        // Comando que mostra ou oculta itens do menu;
        android.view.Menu menu = navigationView.getMenu();
        menu.findItem(R.id.menu_logoff).setVisible(false);
        menu.findItem(R.id.menu_MinhaConta).setVisible(false);

        // Configuração do botão Entrar
        btnEntrarLogin = findViewById(R.id.btnEntrarLogin);
        btnEntrarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtUsuario = findViewById(R.id.txtUsuario);
                txtSenha = findViewById(R.id.txtSenha);

                String login = txtUsuario.getText().toString();
                String senha = txtSenha.getText().toString();

                // Chamada do método que valida os dados do usuário;
                setLogin(login, senha);
            }
        });

        // Configuração do botão Cadastrar;
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlerta("(EM CONSTRUÇÃO) Aqui você seria levado para tela de Cadastro!");
            }
        });

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

    public void setLogin(String login, String senha){

        String loginOk = "admin";
        String senhaOk = "admin";

        boolean dadosOk = true;

        if(txtUsuario.getText().toString().trim().isEmpty()){
            dadosOk = false;
            txtUsuario.setError("Opps! Você esqueceu de preencher esse campo!");
            Snackbar.make(txtUsuario, "Por favor preencha o Nome de Usuário", Snackbar.LENGTH_LONG).setAction("Erro!", null).show();
        } else if(txtSenha.getText().toString().trim().isEmpty()){
            dadosOk = false;
            txtSenha.setError("Opps! Você esqueceu de preencher esse campo!");
            Snackbar.make(txtSenha, "Por favor preencha a Senha", Snackbar.LENGTH_LONG).setAction("Erro!", null).show();
        }

        if(dadosOk){

            if(loginOk.equals(login) && senhaOk.equals(senha)){

                // Comando para mostrar ou ocultar o itens do menu;
                android.view.Menu menuItens = navigationView.getMenu();
                menuItens.findItem(R.id.menu_login).setVisible(false);
                menuItens.findItem(R.id.menu_logoff).setVisible(true);
                menuItens.findItem(R.id.menu_MinhaConta).setVisible(true);

                setAlerta("Login Realizado com Sucesso! Aqui você seria direcionado para sua página aonde poderá ter acesso as suas reservas...");
                limpaTexto();

            } else {

                setAlerta("Login ou Senha inválido!");
                limpaTexto();
            }

        }

    }

    public void limpaTexto(){
        txtUsuario.setText("");
        txtSenha.setText("");
    }

    public void setAlerta(String msg){

        AlertDialog.Builder pop = new AlertDialog.Builder(this);
        pop.setIcon(R.drawable.icone_g);
        pop.setTitle("Tela Login");
        pop.setMessage(msg);

        pop.setNeutralButton("Voltar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        pop.show();
    }

}