package com.example.dm2.examen2ev;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class multimedia extends AppCompatActivity {
    private MediaPlayer mp;
    private int posicion = 0;
    private Spinner cmbOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);
        cmbOpciones =(Spinner) findViewById(R.id.CmbOpciones);
        final String[] datos = new String[] {getString(R.string.burro),getString(R.string.caballo),
                getString(R.string.cabra),getString(R.string.gallina), getString(R.string.gallo), getString(R.string.gato), getString(R.string.oveja), getString(R.string.vaca)};

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);
        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        cmbOpciones.setAdapter(adaptador);

        cmbOpciones.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView,
                                               View view, int i, long l) {
                        iniciar(adapterView.getSelectedItemPosition());
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
    }

    public void destruir() {
        if (mp != null)
            mp.release();
    }

    public void iniciar(int i) {
        destruir();
        switch(i){
            case 0: mp = MediaPlayer.create(this, R.raw.burro);
                mp.start();
                break;
            case 1: mp = MediaPlayer.create(this, R.raw.caballos);
                mp.start();
                break;
            case 2:mp = MediaPlayer.create(this, R.raw.cabra);
                mp.start();
                break;
            case 3:mp = MediaPlayer.create(this, R.raw.gallina);
                mp.start();
                break;
            case 4:mp = MediaPlayer.create(this, R.raw.gallo);
                mp.start();
                break;
            case 5:mp = MediaPlayer.create(this, R.raw.gato);
                mp.start();
                break;
            case 6:mp = MediaPlayer.create(this, R.raw.ovejas);
                mp.start();
                break;
            case 7:mp = MediaPlayer.create(this, R.raw.vaca);
                mp.start();
                break;
        }
    }

    public void salir(View v){
        finish();
    }
}
