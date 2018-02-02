package com.example.dm2.examen2ev;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void tiempo(View v){
        Intent Ejer1 = new Intent(MainActivity.this, datos_tiempo.class);
        startActivity(Ejer1);
    }
    public void datosAt(View v){
        Intent Ejer2 = new Intent(MainActivity.this, datos_atomicos.class);
        startActivity(Ejer2);
    }
    public void multi(View v){
        Intent Ejer3 = new Intent(MainActivity.this, multimedia.class);
        startActivity(Ejer3);
    }

    public void salir(View v){
            finish();
    }
}
