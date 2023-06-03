package com.example.gerenciamento_de_transito;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gerenciamento_de_transito.databinding.ActivityLoginBinding;
import com.example.gerenciamento_de_transito.model.Usuario;
import com.example.gerenciamento_de_transito.model.Util.CondiguraBD;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;

public class LoginActivity extends AppCompatActivity {

    EditText campoEmail, campoSenha;
    Button botaoacessar;
    private FirebaseAuth auth;

    private ActivityLoginBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        auth = CondiguraBD.Firebaseautenticacao();
        inicializarComponentes();



        mainBinding.textTelaCadastro.setOnClickListener(
                 v ->{
                     Intent i= new Intent(LoginActivity.this, CadastroActivity.class);
                     startActivity(i);
                 }
        );
    }
    public void validarAutenticao(View view) {
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if(!email.isEmpty()){
            if(!senha.isEmpty()){

                Usuario usuario = new Usuario();

                usuario.setEmail(email);
                usuario.setSenha(senha);

                logar(usuario);

            }else{
                Toast.makeText(this, "Preencha a senha", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Preencha o email", Toast.LENGTH_SHORT).show();
        }
    }

    private void logar(Usuario usuario) {

        auth.signInWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    abrirMain();
                }else{
                    String excecao="";
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Usuario nao esta cadastrado";
                    } catch (Exception e){
                        excecao = "Erro ao conectar usuario"+e.getMessage();
                                e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void abrirMain() {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }


    private void inicializarComponentes(){
        campoEmail = findViewById(R.id.edit_emailLogin);
        campoSenha = findViewById(R.id.edit_senhaLogin);
        botaoacessar = findViewById(R.id.btn_entrar);
    }
}