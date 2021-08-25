package com.example.desafiomb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ChangePassword extends AppCompatActivity {

    private EditText edNewPassword, edConfirmPassword;
    private Button btnChange;

    String email = "";

    LoginActivity loginActivity;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Bundle i = getIntent().getExtras();
        loginActivity = i.getParcelable("user");

        email = getIntent().getStringExtra("fieldEmail");

        edNewPassword = findViewById(R.id.edNewPassword);
        edConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnChange = findViewById(R.id.btnChange);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edNewPassword.getText().toString().isEmpty()&&edConfirmPassword.getText().toString().isEmpty()){
                    Toast.makeText(ChangePassword.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }else{
                    if (edNewPassword.getText().toString().equals(edConfirmPassword.getText().toString())){
                        String novaSenha = edNewPassword.getText().toString();
                        Update(novaSenha);
                        Toast.makeText(ChangePassword.this, "Alterado com sucesso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChangePassword.this,LoginActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(ChangePassword.this, "Os campos digitados não são iguais", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    //Metodo que faz a alteração de senha no banco de dados
    private void Update(String novaSenha) {
        DocumentReference emailRef = db.collection("users").document(email);
        emailRef.update("password",edNewPassword.getText().toString());
    }
}