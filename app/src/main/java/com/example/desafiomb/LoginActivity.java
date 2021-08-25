package com.example.desafiomb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private Button btnLogin;
    private TextView txtPassword, txtRegister;
    private CheckBox checkPersistence;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

//    StringBuilder num = new StringBuilder();

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_SENHA = "senha";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        permissaoSMS();

        //Tirando a barrinha do app
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        edtEmail = findViewById(R.id.edEmailLogin);
        edtPassword = findViewById(R.id.edtPasswordLogin);
        txtPassword = findViewById(R.id.txtPassword);
        txtRegister = findViewById(R.id.txtRegister);
        btnLogin = findViewById(R.id.btnLogin);
        checkPersistence = findViewById(R.id.checkPersistence);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String edEmailShared = sharedPreferences.getString(KEY_EMAIL, null);

        if (edEmailShared != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void permissaoSMS() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.RECEIVE_SMS) !=
                PackageManager.PERMISSION_GRANTED) {
            //Se a permissão de RECEIVE_SMS" ainda não tiver liberad, exibe a caixa pro usuario escolher sim ou nao
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, 10);
        }

        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED) {
            //Se a permissão de RECEIVE_SMS" ainda não tiver liberad, exibe a caixa pro usuario escolher sim ou nao
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 30);
        }
    }


    public void verificarLogin() {
        if (edtEmail.getText().toString().isEmpty()) {
            edtEmail.setError("Preencha o email");
            edtEmail.requestFocus();
            return;
        }
        if (edtPassword.getText().toString().isEmpty()) {
            edtPassword.setError("Preencha a senha");
            edtPassword.requestFocus();
            return;
        }
        if (edtPassword.getText().toString().length() < 9) {
            edtPassword.setError("Senha menor que 9 dígitos");
            edtPassword.requestFocus();
        }
    }


    public void onEntrar(View view) {
        verificarLogin();

        DocumentReference emailRef = db.collection("users").document(edtEmail.getText().toString());

        emailRef.get().addOnCompleteListener(task -> {
            DocumentSnapshot document = task.getResult();
            if (document.exists()) {
                String userEmail = document.getId();
                if (userEmail.equals(edtEmail.getText().toString())) {
                    String Senha = document.getString("password");
                    if (Senha.equals(edtPassword.getText().toString())) {
                        Toast.makeText(LoginActivity.this, "Entrando...", Toast.LENGTH_SHORT).show();
                        if (checkPersistence.isChecked()) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(KEY_EMAIL, edtEmail.getText().toString());
                            editor.putString(KEY_SENHA, edtPassword.getText().toString());
                            editor.apply();
                            Toast.makeText(this, "Login correto", Toast.LENGTH_SHORT).show();
                        }
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Usuario não encontrado", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Usuario não encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onEsqueceu(View view) {
        if (edtEmail.getText().toString().equals("")){//Conferindo se esta vazio
            edtEmail.setError("Preencha o campo Email");
        }else{
            //Pega o email e ve se existe esse id no banco
            DocumentReference emailRef = db.collection("users").document(edtEmail.getText().toString());
            emailRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String userEmai = document.getId();//Transforma como id
                        if (userEmai.equals(edtEmail.getText().toString())) {
                            String phone = document.getString("phone");//Pega o telefone no banco

//                            randomCodig();

                            Intent intent = new Intent(LoginActivity.this, ChangePassword.class);
                            //Enviando os dados para outra activity
                            intent.putExtra("fieldEmail", userEmai);
                            intent.putExtra("phone", phone);
//                            intent.putExtra("num", num.toString());
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Email não encontrado", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        Toast.makeText(LoginActivity.this, "Usuario não encontrado", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
//
//    public void randomCodig() {
//        Random smsCodigo = new Random();
//        for (int i=0; i<5; i++){
//            num.append(smsCodigo.nextInt(9));//numeros de 1 a 9
//        }
//    }
}