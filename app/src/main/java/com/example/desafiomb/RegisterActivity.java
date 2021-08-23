package com.example.desafiomb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private EditText edName, edEmail, edCpf, edPhone, edPassword;
    private Button btRegister;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Tirando a barra do app
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        edName = findViewById(R.id.edtName);
        edEmail = findViewById(R.id.edtEmail);
        edCpf = findViewById(R.id.edtCpf);
        edPhone = findViewById(R.id.edtPhone);
        edPassword = findViewById(R.id.edtPassword);
        btRegister = findViewById(R.id.btnRegister);


        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                People user = new People(edName.getText().toString(),
                        edEmail.getText().toString(),
                        edCpf.getText().toString(),
                        edPhone.getText().toString(),
                        edPassword.getText().toString());

                db.collection("users")
                        .document(edEmail.getText().toString())
                        .set(user)

                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                verificarRegister();
                                if (edName.getText().toString().length() < 5 ||
                                        edPhone.getText().toString().length() != 14 ||
                                        edEmail.getText().toString().length() < 8 ||
                                        edCpf.getText().toString().length() != 14 ||
                                        edPassword.getText().toString().length() < 5
                                ) {
                                    Toast.makeText(RegisterActivity.this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                            }
                        })

                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(RegisterActivity.this, "Erro!! Revise seus dados", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    //Verificador do cpf
    public boolean cpfVerify() {
        //Tirando a máscara do CPF
        String CPF;
        CPF = edCpf.getText().toString().replaceAll("[-.]", "");
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999")) {
            return false;
        }
        //Criando array onde guardo cada carctere
        int[] numerosCPF = new int[11];
        //Adicionando valores na array
        for (int i = 0; i < CPF.length(); i++) {
            int selecionador = Character.getNumericValue(CPF.charAt(i));
            numerosCPF[i] = selecionador;
        }
        //Fazendo 1° conta
        int soma1 = 0;
        for (int i = 0, ValSoma1 = 10; i < numerosCPF.length && ValSoma1 >= 2; i++, ValSoma1--) {
            int x = numerosCPF[i] * ValSoma1;
            soma1 += x;
        }
        //Fazendo 2° conta
        int soma2 = 0;
        for (int i = 0, ValSoma2 = 11; i < numerosCPF.length && ValSoma2 >= 2; i++, ValSoma2--) {
            int x = (numerosCPF[i] * ValSoma2);
            soma2 += x;
        }
        //Digitos verificadores
        int digito1 = soma1 * 10 % 11;
        int digito2 = soma2 * 10 % 11;

        //MUDANDO PRA COMPARAR COM OS DIGITOS
        if (digito1 != numerosCPF[9] && digito2 != numerosCPF[10]) {
            return false;
        } else {
            return true;
        }
    }

    //Verifica se o usuario ja esta cadastrado
    public void estaCadastrado() {

        String EmailfId = edEmail.getText().toString();
        DocumentReference docRef = db.collection("users").document(EmailfId);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                assert document != null;
                if (document.exists()) {
                    String userCPF = document.getId();
                    if (userCPF.equals(EmailfId)) {
                        Toast.makeText(RegisterActivity.this, "Usuario já cadastrado", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    People user = new People(edName.getText().toString(),
                            edEmail.getText().toString(),
                            edCpf.getText().toString(),
                            edPhone.getText().toString(),
                            edPassword.getText().toString());
                }
            }
        });
    }


    public void verificarRegister() {

        if (edPassword.getText().toString().length() < 9) {
            edPassword.setError("Insira uma senha com mais de 8 caracteres");
            edPassword.requestFocus();
            return;
        }
        if (!this.cpfVerify()) {
            Toast.makeText(RegisterActivity.this, "CPF inválido", Toast.LENGTH_SHORT).show();
        } else {
            this.estaCadastrado();
        }

    }
}