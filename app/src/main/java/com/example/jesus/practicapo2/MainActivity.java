package com.example.jesus.practicapo2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText txtNombre,txtClave;
    Spinner spEdad,spPais,spEdu;
    Button btnRegistrar,btnCancelar;
    String Genero;

    String valNombre, nombre,clave, edad, pais,edu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs=getSharedPreferences("prefs",Context.MODE_PRIVATE);
        valNombre=prefs.getString("nombre","");

        if (valNombre!=""){
            Intent bienvenida = new Intent(getApplicationContext(),BienvenidaActivity.class);
            startActivity(bienvenida);
        }
        else{
            txtNombre=(EditText)findViewById(R.id.txtNombre);
            txtClave=(EditText)findViewById(R.id.txtClave);
            spEdad=(Spinner)findViewById(R.id.spEdad);
            spPais=(Spinner)findViewById(R.id.spPais);
            spEdu=(Spinner)findViewById(R.id.spEduca);
            btnRegistrar=(Button)findViewById(R.id.btnRegistrar);
            btnCancelar=(Button)findViewById(R.id.btnCancelar);

            List<String> edades=new ArrayList<>();
            List<String> paises=new ArrayList<>();
            final List<String> educacion=new ArrayList<>();
            edades.add("10-17");
            edades.add("18-24");
            edades.add("25-35");
            edades.add("36-45");
            edades.add("Mas de 45");
            paises.add("Congo");
            paises.add("Irán");
            paises.add("Arabia Saudita");
            educacion.add("Básica");
            educacion.add("Secundaria");
            educacion.add("Preparatoria");
            educacion.add("Universidad");
            llenarSpinner(edades,spEdad);
            llenarSpinner(paises,spPais);
            llenarSpinner(educacion,spEdu);

            btnRegistrar.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            registrarClicked();
                        }
                    }
            );
            btnCancelar.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            cancelarClicked();
                        }
                    }
            );

            spEdad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    TextView tV= (TextView) view;
                    edad=tV.getText().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            spPais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    TextView tV= (TextView) view;
                    pais=tV.getText().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            spEdu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    TextView tV= (TextView) view;
                    edu=tV.getText().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

    }

    //Apaptador
    public void llenarSpinner(List<String> items,Spinner spinner){
        ArrayAdapter<String> adp= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        spinner.setAdapter(adp);
    }

    //RadioButton
    public void onRadioButtonClicked(View view){
        boolean checked=((RadioButton)view).isChecked();

        switch (view.getId()){
            case R.id.rbtnMasc:
                if (checked)
                    this.Genero="M";
                break;
            case R.id.rbtnFem:
                if (checked)
                    this.Genero="F";
                break;
        }
    }

    public void registrarClicked(){
        nombre=txtNombre.getText().toString();
        clave=txtClave.getText().toString();
        SharedPreferences prefs = this.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        if (nombre.length()<1||clave.length()<1||Genero==null){
            Toast.makeText(this,"Verifique que todos los campos esten correctamente llenados",Toast.LENGTH_SHORT).show();
        }
        else{
            editor.putString("nombre",nombre);
            editor.putString("clave",clave);
            editor.putString("edad",edad);
            editor.putString("pais",pais);
            editor.putString("educacion",edu);
            editor.putString("genero",Genero);
            editor.commit();
            Toast.makeText(this,nombre+" "+clave+" "+edad+" "+pais+" "+edu+" "+Genero,Toast.LENGTH_SHORT).show();

            Intent bienvenida = new Intent(getApplicationContext(),BienvenidaActivity.class);
            startActivity(bienvenida);
        }
    }
    public void cancelarClicked(){
        Intent main = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(main);
    }

}
