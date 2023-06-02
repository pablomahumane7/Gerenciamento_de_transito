package com.example.gerenciamento_de_transito;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.gerenciamento_de_transito.databinding.ActivityCadastroBinding;

public class CadastroActivity extends AppCompatActivity {

    private ActivityCadastroBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
    }
}