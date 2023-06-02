package com.example.gerenciamento_de_transito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.gerenciamento_de_transito.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        mainBinding.btnEntrar.setOnClickListener(
                v ->{
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                }
        );

        mainBinding.textTelaCadastro.setOnClickListener(
                 v ->{
                     Intent i= new Intent(LoginActivity.this, CadastroActivity.class);
                     startActivity(i);
                 }
        );
    }
}