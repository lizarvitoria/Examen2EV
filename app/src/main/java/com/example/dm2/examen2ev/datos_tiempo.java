package com.example.dm2.examen2ev;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class datos_tiempo extends AppCompatActivity {
    private Button btnbilbao, btnvitoria, btndonostia;
    private List<Tiempo> tiempos;
    private TextView txtResultado ,txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_tiempo);
        btnbilbao = (Button)findViewById(R.id.btnbilbao);
        btnvitoria = (Button)findViewById(R.id.btnvitoria);
        btndonostia = (Button)findViewById(R.id.btndonostia);
        txtResultado = (TextView)findViewById(R.id.txtResultado);
        txt1 = (TextView)findViewById(R.id.txt1);
        btnbilbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Carga del XML mediante la Tarea Asincrona
                txt1.setText(btnbilbao.getText());
                CargarXmlTask tarea = new CargarXmlTask();
                tarea.execute("http://xml.tutiempo.net/xml/8043.xml");
            }
        });
        btnvitoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Carga del XML mediante la Tarea Asincrona
                txt1.setText(btnvitoria.getText());
                CargarXmlTask2 tarea = new CargarXmlTask2();
                tarea.execute("http://xml.tutiempo.net/xml/8050.xml");
            }
        });
        btndonostia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Carga del XML mediante la Tarea Asincrona
                txt1.setText(btndonostia.getText());
                CargarXmlTask3 tarea = new CargarXmlTask3();
                tarea.execute("http://xml.tutiempo.net/xml/4917.xml");
            }
        });
    }
    private class CargarXmlTask extends AsyncTask<String,Integer,Boolean> {
        protected Boolean doInBackground(String... params) {
            RSSParserDom parser = new RSSParserDom(params[0]);
            tiempos = parser.parse();
            return true;
        }

        protected void onPostExecute(Boolean result) {
            //Tratamos la lista de noticias
            //Por ejemplo: escribimos los títulos en pantalla
            txtResultado.setText("");
            for(int i=0; i<tiempos.size(); i++)
            {
                txtResultado.setText(
                        txtResultado.getText().toString() +
                                System.getProperty("line.separator")+getString(R.string.hora)+tiempos.get(i).getHora()+"\n"+getString(R.string.temperatura)+tiempos.get(i).getTemp()+"\n"+getString(R.string.cielo)+tiempos.get(i).getCielo());
            }
        }
    }

    private class CargarXmlTask2 extends AsyncTask<String,Integer,Boolean> {
        protected Boolean doInBackground(String... params) {
            RSSParserDom parser = new RSSParserDom(params[0]);
            tiempos = parser.parse();
            return true;
        }

        protected void onPostExecute(Boolean result) {
            //Tratamos la lista de noticias
            //Por ejemplo: escribimos los títulos en pantalla
            txtResultado.setText("");
            for(int i=0; i<tiempos.size(); i++)
            {
                txtResultado.setText(
                        txtResultado.getText().toString() +
                                System.getProperty("line.separator")+getString(R.string.hora)+tiempos.get(i).getHora()+"\n"+getString(R.string.temperatura)+tiempos.get(i).getTemp()+"\n"+getString(R.string.cielo)+tiempos.get(i).getCielo());
            }
        }
    }
    private class CargarXmlTask3 extends AsyncTask<String,Integer,Boolean> {
        protected Boolean doInBackground(String... params) {
            RSSParserDom parser = new RSSParserDom(params[0]);
            tiempos = parser.parse();
            return true;
        }

        protected void onPostExecute(Boolean result) {
            //Tratamos la lista de noticias
            //Por ejemplo: escribimos los títulos en pantalla
            txtResultado.setText("");
            for(int i=0; i<tiempos.size(); i++)
            {
                txtResultado.setText(
                        txtResultado.getText().toString() +
                                System.getProperty("line.separator")+getString(R.string.hora)+tiempos.get(i).getHora()+"\n"+getString(R.string.temperatura)+tiempos.get(i).getTemp()+"\n"+getString(R.string.cielo)+tiempos.get(i).getCielo());
            }
        }
    }

    public void salir(View v){
        finish();
    }


}
