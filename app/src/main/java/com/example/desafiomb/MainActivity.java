package com.example.desafiomb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtEventFacul1, txtEventFacu2, txtEventFacul3, txtEventEmp1, txtEventEmp2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEventFacul1 = findViewById(R.id.txtEvent);
        txtEventFacu2 = findViewById(R.id.txtEventFaculTwo);
        txtEventFacul3 = findViewById(R.id.txtEventFaculThree);
        txtEventEmp1 = findViewById(R.id.txtEventEmpOne);
        txtEventEmp2 = findViewById(R.id.txtEventEmpTwo);



        txtEventFacul1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double numValue = 25.00;
                Intent outraTela = new Intent(getApplicationContext(), Compra.class);
                outraTela.putExtra("nameEvent", "FESTA DO CALOURO Á FANTASIA ");
                outraTela.putExtra("valoringre", numValue.toString());
                startActivity(outraTela);
            }
        });

        txtEventFacu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double numValue = 20.00;
                Intent outraTela = new Intent(getApplicationContext(), Compra.class);
                outraTela.putExtra("nameEvent", "MOSTRA CULTURAL");
                outraTela.putExtra("valoringre", numValue.toString());
                startActivity(outraTela);
            }
        });

        txtEventFacul3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double numValue = 30.10;
                Intent outraTela = new Intent(getApplicationContext(), Compra.class);
                outraTela.putExtra("nameEvent", "JORNADA ACADEMICA");
                outraTela.putExtra("valoringre", numValue.toString());
                startActivity(outraTela);
            }
        });

        txtEventEmp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double numValue = 45.00;
                Intent outraTela = new Intent(getApplicationContext(), Compra.class);
                outraTela.putExtra("nameEvent", "BUSINESS COFERENCE");
                outraTela.putExtra("valoringre", numValue.toString());
                startActivity(outraTela);
            }
        });

        txtEventEmp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double numValue = 37.00;
                Intent outraTela = new Intent(getApplicationContext(), Compra.class);
                outraTela.putExtra("nameEvent", "EXPO BUSINESS");
                outraTela.putExtra("valoringre", numValue.toString());
                startActivity(outraTela);
            }
        });
    }
}