package com.darlanbonfim.apphotel;

import android.content.DialogInterface;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;

public class Login extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    String loginOk = "admin";
    String senhaOk = "admin";

    ImageView imgFotoLogin;
    TextView txtNomeLogin;
    NavigationView navigationView;



    public void setLogin(String login, String senha){

        if(login.equals(loginOk) && senha.equals(senhaOk)){


            imgFotoLogin.setImageResource(R.drawable.foto_login);
            txtNomeLogin.setText("Darlan Araujo");

            setAlerta("Login realizado com sucesso!");

        } else{
            setAlerta("Login ou Senha incorretos!");
        }

    }

    public void setAlerta(String msg){
        AlertDialog.Builder pop = new AlertDialog.Builder(getApplicationContext());
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
