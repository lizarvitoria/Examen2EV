package com.example.dm2.examen2ev;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import java.net.URLEncoder;

import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class datos_atomicos extends AppCompatActivity {
    private String TAG="Response";
    private Button btnver;
    private String elemento;
    private EditText edElem;
    private SoapPrimitive resultado;
    private TextView txt1;
    private String numAt, simboloAt, pesoAt, puntoEb,densidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_atomicos);
        btnver = (Button)findViewById(R.id.btnver);
        edElem = (EditText)findViewById(R.id.edElem);
        txt1 = (TextView)findViewById(R.id.txt1);
        btnver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                elemento = edElem.getText().toString();
                AsyncPT task = new AsyncPT();
                task.execute(elemento);
            }
        });
    }

    private class AsyncPT extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            try {
                String resultado;
                HttpURLConnection conn;
                URL url = new URL("http://www.webserviceX.NET/periodictable.asmx/GetAtomicNumber");
                String param ="ElementName="+ URLEncoder.encode(params[0],"UTF-8");
                conn = (HttpURLConnection)url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setFixedLengthStreamingMode(param.getBytes().length);
                conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                out.print(param);
                out.close();
                Scanner inStream = new Scanner(conn.getInputStream());

                while (inStream.hasNextLine()) {
                    resultado = inStream.nextLine();
                    resultado = resultado.replaceAll("\\s+","");
                    if(resultado.startsWith("&lt;AtomicNumber&gt;")) {
                        numAt = resultado.replace("&lt;AtomicNumber&gt;", "").replace("&lt;/AtomicNumber&gt;", "");
                    }
                    if(resultado.startsWith("&lt;Symbol&gt;")) {
                        simboloAt = resultado.replace("&lt;Symbol&gt;", "").replace("&lt;/Symbol&gt;", "");
                    }
                    if(resultado.startsWith("&lt;AtomicWeight&gt;")) {
                        pesoAt = resultado.replace("&lt;AtomicWeight&gt;", "").replace("&lt;/AtomicWeight&gt;", "");
                    }
                    if(resultado.startsWith("&lt;BoilingPoint&gt;")) {
                        puntoEb = resultado.replace("&lt;BoilingPoint&gt;", "").replace("&lt;/BoilingPoint&gt;", "");
                    }
                    if(resultado.startsWith("&lt;Density&gt;")) {
                        densidad = resultado.replace("&lt;Density&gt;", "").replace("&lt;/Density&gt;", "");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            txt1.setText(getString(R.string.simboloAt)+simboloAt+"\n"+getString(R.string.numAt)+numAt+"\n"+getString(R.string.pesoAt)+pesoAt+"\n"+getString(R.string.puntoEb)+puntoEb+"\n"+getString(R.string.densidad)+densidad);
        }
    }

    public void salir(View v){
        finish();
    }
}
