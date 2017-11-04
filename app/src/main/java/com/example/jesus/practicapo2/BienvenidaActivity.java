package com.example.jesus.practicapo2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BienvenidaActivity extends AppCompatActivity {
    Button btnConsultar,btnSalir,btnBorrar;
    TextView lblNombre;
    String nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        SharedPreferences prefs=getSharedPreferences("prefs",Context.MODE_PRIVATE);
        nombre=prefs.getString("nombre","");
        lblNombre=(TextView) findViewById(R.id.lblNombre);
        btnConsultar=(Button)findViewById(R.id.btnConsultar);
        btnBorrar=(Button)findViewById(R.id.btnBorrar);
        btnSalir=(Button)findViewById(R.id.btnSalir);

        lblNombre.setText(nombre);
        btnConsultar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent ListSong = new Intent(getApplicationContext(), ConsultaActivity.class);
                        startActivity(ListSong);
                    }
                }
        );
        btnBorrar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        borrarClicked();
                    }
                }
        );
        btnSalir.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }
        );
    }

    public  void borrarClicked(){

        SharedPreferences prefs = this.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("nombre","");
        editor.putString("clave","");
        editor.putString("edad","");
        editor.putString("pais","");
        editor.putString("educacion","");
        editor.putString("genero","");
        editor.commit();
        Intent registro = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(registro);
    }
}
