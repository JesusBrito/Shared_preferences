package com.example.jesus.practicapo2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConsultaActivity extends AppCompatActivity {

    TextView lblNombre,lblClave,lblEdad,lblPais,lblEducacion,lblGenero;
    Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        SharedPreferences prefs=getSharedPreferences("prefs",Context.MODE_PRIVATE);

        String nombre=prefs.getString("nombre","");
        String clave=prefs.getString("clave","");
        String edad=prefs.getString("edad","");
        String pais=prefs.getString("pais","");
        String educacion=prefs.getString("educacion","");
        String genero=prefs.getString("genero","");


        lblNombre=(TextView)findViewById(R.id.lblNombre);
        lblClave=(TextView)findViewById(R.id.lblClave);
        lblEdad=(TextView)findViewById(R.id.lblEdad);
        lblGenero=(TextView)findViewById(R.id.lblGenero);
        lblPais=(TextView)findViewById(R.id.lblPais);
        lblEducacion=(TextView)findViewById(R.id.lblEducacion);
        btnRegresar=(Button)findViewById(R.id.btnRegresar);

        lblNombre.setText(nombre);
        lblClave.setText(clave);
        lblEdad.setText(edad);
        lblPais.setText(pais);
        lblEducacion.setText(educacion);
        lblGenero.setText(genero);

        btnRegresar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent bienvenida = new Intent(getApplicationContext(),BienvenidaActivity.class);
                        startActivity(bienvenida);
                    }
                }
        );
    }
}
