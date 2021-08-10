package com.darlanbonfim.apphotel;

import static android.system.Os.close;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

public class Menu {

    Intent intent;
    ActivityOptionsCompat animacao;

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

            case R.id.menu_sair:

                break;

        }

    }

    public void setAnimacao(Context context, Class tela){
        intent = new Intent(context, tela);
        ActivityOptionsCompat animacao = ActivityOptionsCompat.makeCustomAnimation(context, R.anim.fade_in, R.anim.anim_cair);
        ActivityCompat.startActivity(context, intent, animacao.toBundle());
    }


}
