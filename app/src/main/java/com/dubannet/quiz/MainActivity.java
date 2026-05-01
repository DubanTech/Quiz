package com.dubannet.quiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public static final String dataUserCache = "datauser";
    private static final int modoPrivado = Context.MODE_PRIVATE;
    SharedPreferences sh;
    SharedPreferences.Editor editor;
    EditText nombre;
    EditText edad;

    Spinner categoria;

    AppCompatButton btnIniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nombre=findViewById(R.id.nombre);
        edad = findViewById(R.id.edad);
        categoria=findViewById(R.id.categoria);
        btnIniciar=findViewById(R.id.btniniciar);
        sh = getSharedPreferences(dataUserCache, modoPrivado);
        editor = sh.edit();

        categoria = findViewById(R.id.categoria);

        String[] categoriaarray = {"Musica", "Deporte", "Cine"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                categoriaarray
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        categoria.setAdapter(adapter);
        validar();

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=nombre.getText().toString();
                int ed=Integer.parseInt(edad.getText().toString());
                String cate = categoria.getSelectedItem().toString();

                if(!name.isEmpty()){
                    editor.putString("Nombre",name);
                    editor.putString("Categoria",cate);
                    editor.putInt("Edad",ed);

                    editor.commit();
                    Intent intent = null;
                    if(cate.equalsIgnoreCase("Musica")){
                        intent = new Intent(MainActivity.this, Musica.class);
                    } else if (cate.equalsIgnoreCase("Deporte")) {
                        intent = new Intent(MainActivity.this, Deporte.class);
                    } else if (cate.equalsIgnoreCase("Cine")) {
                        intent = new Intent(MainActivity.this, Cine.class);
                    }

                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this,"Asegurese de completar todos los campos",Toast.LENGTH_LONG).show();
                }
            }

        });

    }
    public void validar(){

        String na = this.getSharedPreferences(dataUserCache,modoPrivado).getString("Nombre","0");
        int e = this.getSharedPreferences(dataUserCache,modoPrivado).getInt("Edad",0);
        String cate = this.getSharedPreferences(dataUserCache,modoPrivado).getString("Categoria","0");
        if(!na.equalsIgnoreCase("0") && e !=0 && !cate.equalsIgnoreCase("0")){
            Intent intent = null;
            if(cate.equalsIgnoreCase("Musica")){
                intent = new Intent(MainActivity.this, Musica.class);
            } else if (cate.equalsIgnoreCase("Deporte")) {
                intent = new Intent(MainActivity.this, Deporte.class);
            } else if (cate.equalsIgnoreCase("Cine")) {
                intent = new Intent(MainActivity.this, Cine.class);
            }
            startActivity(intent);
            finish();
        }
    }

    public void cerrarSesion(){
        SharedPreferences sh = getSharedPreferences(MainActivity.dataUserCache, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();

        editor.clear();
        editor.commit();

        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}