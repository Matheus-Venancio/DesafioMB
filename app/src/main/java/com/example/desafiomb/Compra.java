package com.example.desafiomb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media.app.NotificationCompat;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.Context;

public class Compra extends AppCompatActivity {

    TextView txtValor, txtResult, txtNameEvent;
    Button btnOne, btnTwo, btnThree, btnFour, btnPurchaseCom, btnConfirm, btnCancel;

    Double valorTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        txtValor = findViewById(R.id.txtTesteValor);
        txtResult = findViewById(R.id.txtTesteResult);
        txtNameEvent = findViewById(R.id.txtNameEvent);
        btnOne = findViewById(R.id.btnTesteContaOne);
        btnTwo = findViewById(R.id.btnTesteConta);
        btnThree = findViewById(R.id.btnTesteContaThree);
        btnFour = findViewById(R.id.btnTesteContaFour);
        btnPurchaseCom = findViewById(R.id.btnPurchaseCom);

        String nameEvent = getIntent().getStringExtra("nameEvent");

        String valor = getIntent().getStringExtra("valoringre");
        valorTwo = Double.parseDouble(valor);

        txtNameEvent.setText(nameEvent);
        txtValor.setText("R$ " + valor);

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtResult.setText("R$ " + valor);
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double num2 = 2.0;
                double result = valorTwo * num2;
                txtResult.setText("R$ " + result);
            }
        });
        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double num2 = 3.0;
                double result = valorTwo * num2;
                txtResult.setText("R$ " + result);
            }
        });

        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double num2 = 4.0;
                double result = valorTwo * num2;
                txtResult.setText("R$ " + result);
            }
        });

        btnPurchaseCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Compra.this, "Compra realizada com sucesso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Compra.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}