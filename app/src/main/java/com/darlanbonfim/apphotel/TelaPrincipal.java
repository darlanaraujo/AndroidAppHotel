package com.darlanbonfim.apphotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.Animatable;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

public class TelaPrincipal extends AppCompatActivity {

    ImageView imgSlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);

        imgSlide = findViewById(R.id.imgSlide);
        AnimationDrawable animacao = (AnimationDrawable) imgSlide.getDrawable();
        animacao.start();

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