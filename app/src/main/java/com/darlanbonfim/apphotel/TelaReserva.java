package com.darlanbonfim.apphotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class TelaReserva extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Atributos do menu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    // Atributos
    Button btnSaibaMais, btnConfirmar, btnLimpar;
    CalendarView calEntrada, calSaida;
    TextView txtEntrada, txtSaida, txtQuarto;
    RadioButton opQuarto1, opQuarto2, opQuarto3;

    // Atributo que define o quarto escolhido;
    boolean quartoOk = false;

    // Atributos que recebem as datas selecionadas;
    int diaEnt, mesEnt, anoEnt;
    int diaSai, mesSai, anoSai;

    // Atributo que confere se o usuário está logado;
    boolean loginOk = false;

    // Animação;
    Animation animItens, animItens2;
    LinearLayout layQuarto, layDuvida, layCalendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_reserva);

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

        // Comando para mostrar ou ocultar o itens do menu;
        android.view.Menu menu = navigationView.getMenu();
        menu.findItem(R.id.menu_logoff).setVisible(false);
        menu.findItem(R.id.menu_MinhaConta).setVisible(false);

        // Ligação dos objetos;
        layQuarto = findViewById(R.id.layQuarto);
        layDuvida = findViewById(R.id.layDuvida);
        layCalendario = findViewById(R.id.layCalendario);

        // Animações;
        animItens = AnimationUtils.loadAnimation(this, R.anim.anim_itens);
        animItens2 = AnimationUtils.loadAnimation(this, R.anim.anim_itens2);
        layQuarto.setAnimation(animItens);
        layDuvida.setAnimation(animItens2);
        layCalendario.setAnimation(animItens2);

        // CONFIGURAÇÕES ESPECIFICAS DA TELA =======================================================

        // Configuração do botão Saiba Mais;
        btnSaibaMais = findViewById(R.id.btnSaibaMais);
        btnSaibaMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaReserva.this, TelaAcomodacoes.class);
                startActivity(intent);
                finish();
            }
        });

        // Ligação dos objetos;
        txtQuarto = findViewById(R.id.txtQuarto);
        txtEntrada = findViewById(R.id.txtEntrada);
        txtSaida = findViewById(R.id.txtSaida);

        txtEntrada.setText(""); // Comando para deixar o objeto vazio;
        txtSaida.setText(""); // Comando para deixar o objeto vazio;

        // Configuração do botão Limpar;
        btnLimpar = findViewById(R.id.btnLimpar);
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtQuarto.setText("");
                txtEntrada.setText("");
                txtSaida.setText("");

                quartoOk = false;

                opQuarto1.setChecked(false);
                opQuarto2.setChecked(false);
                opQuarto3.setChecked(false);

            }
        });

        // Configuração dos botões da escolha dos quarto;
        opQuarto1 = findViewById(R.id.opQuarto1);
        opQuarto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quartoOk = true;
                setAlerta("Você escolheu se hospedar na Suíte Master! Este quarto acomoda até 2 Adultos");

                opQuarto2.setChecked(false);
                opQuarto3.setChecked(false);

                txtQuarto.setText(R.string.titulo_opcao_quarto1);
            }
        });

        opQuarto2 = findViewById(R.id.opQuarto2);
        opQuarto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quartoOk = true;
                setAlerta("Você escolheu se hospedar na Suíte King! Este quarto acomoda até 2 Adultos.");

                opQuarto1.setChecked(false);
                opQuarto3.setChecked(false);

                txtQuarto.setText(R.string.titulo_opcao_quarto2);
            }
        });

        opQuarto3 = findViewById(R.id.opQuarto3);
        opQuarto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quartoOk = true;
                setAlerta("Você escolheu se hospedar na Suíte Duplex! Este quarto acomoda até 4 Adultos.");

                opQuarto1.setChecked(false);
                opQuarto2.setChecked(false);

                txtQuarto.setText(R.string.titulo_opcao_quarto3);
            }
        });

        // Configuração do calendário de Entrada;
        calEntrada = findViewById(R.id.calEntrada);
        calEntrada.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Comando para adicionar a data aos atributos para fazer comparação;
                diaEnt = dayOfMonth;
                mesEnt = month;
                anoEnt = year;

                // Comando para pegar a data selecionada no calendario;
                String dataEntrada = dayOfMonth + "/" + month + "/" + year;
                txtEntrada.setText(dataEntrada);
            }
        });

        // Configuração do calendário de Saída;
        calSaida = findViewById(R.id.calSaida);
        calSaida.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Comando para adicionar a data aos atributos para fazer comparação;
                diaSai = dayOfMonth;
                mesSai = month;
                anoSai = year;

                // Comando para pegar a data selecionada no calendario;
                String dataSaida = dayOfMonth + "/" + month + "/" + year;
                txtSaida.setText(dataSaida);
            }
        });

        // Configuração do botão Confirmar;
        btnConfirmar = findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setConfereData();

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

    public void setConfereData(){

        // Condição que verifica se um quarto foi escolhido;
        if(!quartoOk){
            setAlerta("Você precisa escolher uma das opções dos nossos quartos!");
        } else {

            // Condição para verificar se as datas de entrada e saída foram selecionadas;
            if(txtEntrada.getText().toString().trim().isEmpty() || txtSaida.getText().toString().trim().isEmpty()){
                setAlerta("Você precisa escolher a data de Entrada e Saída!");
            } else {

                // Condição para verificar se o ano de entrada é menor que o de saída;
                if(anoEnt > anoSai){
                    setAlerta("Data Inválida! Confira o ano de saída...");
                } else if(anoEnt == anoSai && mesEnt > mesSai){
                    setAlerta("Data Inválida! Confira o mês de saída...");
                } else if(mesEnt == mesSai && diaEnt >= diaSai){
                    setAlerta("Data Inválida! Confira o dia de saída...");
                } else {
                    setConfereLogin();
                }
            }
        }

    }

    public void setConfereLogin(){

        if(!loginOk){
            AlertDialog.Builder pop = new AlertDialog.Builder(TelaReserva.this);
            pop.setIcon(R.drawable.icone_g);
            pop.setTitle("Alerta!");
            pop.setMessage("Você precisa está logado para fazer uma reserva! Deseja acessar sua conta?");

            pop.setPositiveButton("Fazer Login", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(TelaReserva.this, TelaLogin.class);
                    startActivity(intent);
                    finish();
                }
            });

            pop.setNeutralButton("Voltar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            pop.show();

        }

    }

    public void setAlerta(String msg){
        AlertDialog.Builder pop = new AlertDialog.Builder(TelaReserva.this);
        pop.setIcon(R.drawable.icone_g);
        pop.setTitle("Alerta!");
        pop.setMessage(msg);

        pop.setNeutralButton("Voltar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        pop.show();
    }

}