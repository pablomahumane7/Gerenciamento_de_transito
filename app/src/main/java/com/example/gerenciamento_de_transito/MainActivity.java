package com.example.gerenciamento_de_transito;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.gerenciamento_de_transito.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        
    }
}