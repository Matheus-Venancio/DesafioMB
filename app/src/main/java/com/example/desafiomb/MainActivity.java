package com.example.desafiomb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtEventFacul1, txtEvent2;

    Double numValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEventFacul1 = findViewById(R.id.txtEvent);
        txtEvent2 = findViewById(R.id.txtEvent2);



        txtEventFacul1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numValue = 25.00;
                Intent intent = new Intent(MainActivity.this,Purchase.class);
                intent.putExtra("valor",numValue.toString());
                startActivity(intent);
            }
        });

        txtEvent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numValue = 64.00;
                Intent intent = new Intent(MainActivity.this,Purchase.class);
                intent.putExtra("valor",numValue.toString());
                startActivity(intent);
            }
        });

    }
}