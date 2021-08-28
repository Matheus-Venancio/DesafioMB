package com.example.desafiomb;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;

public class CodeVerify extends AppCompatActivity {

    EditText edCode;
    TextView txtResend;
    //Button btnConfirm;

    StringBuilder numRev = new StringBuilder();

    String phone = "";

//    String[] permissoes = {Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_verify);

        phone = getIntent().getStringExtra("phone");
        gerarNumero();


        edCode = findViewById(R.id.editTextNumber);
        //btnConfirm = findViewById(R.id.btnConfirm);
        txtResend = findViewById(R.id.txtResendCode);


//        btnConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (edCode.getText().toString().isEmpty()) {
//                    Toast.makeText(CodeVerify.this, "Preencha o campo de codigo", Toast.LENGTH_SHORT).show();
//                } else {
//                    if (edCode.getText().toString().equals(numRev)) {
//                        Toast.makeText(CodeVerify.this, "Codigo não é igual ao enviado.", Toast.LENGTH_SHORT).show();
//
//                    } else {
//
//                    }
//                }
//            }
//        });
        txtResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (numRev.length() == 5) {
                    Toast.makeText(CodeVerify.this, "Código enviado novamente para " + phone, Toast.LENGTH_SHORT).show(); //Ta puxando esse Toast
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phone, null, "Codigo: " + numRev.toString(), null, null);
                }
            }
        });
    }

    public void gerarNumero() {
        Random smsCodigo = new Random();
        for (int i = 0; i < 5; i++) {
            numRev.append(smsCodigo.nextInt(9));//numeros de 1 a 9
        }
        Toast.makeText(CodeVerify.this, "Código enviado para " + phone, Toast.LENGTH_SHORT).show(); //Ta puxando esse Toast
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone, null, "Codigo: " + numRev.toString(), null, null);
    }


    public void onConfirmCode(View view){
        Intent outraTela = new Intent(getApplicationContext(), ChangePassword.class);
        startActivity(outraTela);
    }

}