package com.example.gerenciamento_de_transito;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gerenciamento_de_transito.model.Usuario;
import com.example.gerenciamento_de_transito.model.Util.CondiguraBD;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    Usuario usuario;
    FirebaseAuth autenticaco;

    EditText campoNome, campoEmail, campoSenha;
    Button botaoCadastrar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        inicializar();


    }
    private void inicializar() {

        campoNome = findViewById(R.id.edit_nome);
        campoEmail = findViewById(R.id.edit_emailLogin);
        campoSenha = findViewById(R.id.edit_senhaLogin);
        botaoCadastrar = findViewById(R.id.btnCadastro);
    }
    public void validCampos(View view) {
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if(!nome.isEmpty()) {
            if(!email.isEmpty()) {
                if(!senha.isEmpty()){

                     usuario = new Usuario();

                    usuario.setNome(nome);
                    usuario.setEmail(email);
                    usuario.setSenha(senha);


                    cadastrarUsuario();

                }else{
                    Toast.makeText(this, "Preencha a senha", Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(this, "Preencha o email", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, "Preencha o nome", Toast.LENGTH_SHORT).show();
        }
    }

    private void cadastrarUsuario() {

        autenticaco = CondiguraBD.Firebaseautenticacao();
        autenticaco.createUserWithEmailAndPassword(
                usuario.getEmail(),usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CadastroActivity.this, "Sucesso ao cadastrar usuario", Toast.LENGTH_SHORT).show();
                }else{
                    String excecao = "";

                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        excecao="Digite uma senha forte";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao="Digite um Email valido";
                    }catch (FirebaseAuthUserCollisionException e){
                        excecao="Esta conta ja existe";
                    }catch (Exception e) {
                        excecao="Erro ao cadastrar usuario"+ e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}