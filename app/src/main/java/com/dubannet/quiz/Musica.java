package com.dubannet.quiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Musica extends AppCompatActivity {
    public static final String dataUserCache = "datauser";
    private static final int modoPrivado = Context.MODE_PRIVATE;
    SharedPreferences sh;
    SharedPreferences.Editor editor;
    AppCompatButton btnCerrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_musica);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnCerrar=findViewById(R.id.btnCerrar);
        sh = getSharedPreferences(dataUserCache, modoPrivado);
        editor = sh.edit();
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarSesion();
            }
        });
    }
    public void cerrarSesion(){
        SharedPreferences sh = getSharedPreferences(MainActivity.dataUserCache, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();

        editor.clear();
        editor.commit();

        Intent intent = new Intent(Musica.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}