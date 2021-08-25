package com.example.desafiomb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Purchase extends AppCompatActivity {

    private TextView txtValor, txtResult;
    private Button btnComprar, btnOne,btnTwo,btnThree,btnFour, btnConfirm, btnCancel;

    String valor = "";

    Account account;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        valor = getIntent().getStringExtra("valor");

        txtValor = findViewById(R.id.txtNum);
        txtResult = findViewById(R.id.txtTotal);
        btnComprar = findViewById(R.id.btnPurchase);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnConfirm = findViewById(R.id.btnConfrimPurchase);
        btnCancel = findViewById(R.id.btnCancelPurchase);

        dialog = new Dialog(Purchase.this);
        dialog.setContentView(R.layout.custom_dialog);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background_dialog));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        txtValor.setText(valor);

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtResult.setText("R$ "+valor);
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Double valor2 = Double.parseDouble(valor);
            account.buttonTwo(valor2);
            txtResult.setText("R$ "+valor2);
            }
        });

        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double valor2 = Double.parseDouble(valor);
                account.buttonThree(valor2);
                txtResult.setText("R$ "+valor2);
            }
        });

        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double valor2 = Double.parseDouble(valor);
                account.buttonFour(valor2);
                txtResult.setText("R$ "+valor2);
            }
        });

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Purchase.this, "Compra realizada com sucesso", Toast.LENGTH_SHORT).show();
           dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Purchase.this, "Cancelada", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

}