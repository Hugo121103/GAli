package com.example.galigeometriaanalitica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*poner el icono en el action Bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.gali);*/
    }

    //Abrir Activity Distancai entre dos puntos
    public void distancia(View view){
        Intent siguiente = new Intent(this, Distancia_entre_dos_puntos.class);
        startActivity(siguiente);
    }

    //Abrir Activity punto medio
    public void punto_medio (View view){
        Intent siguiente = new Intent(this, PuntoMedio.class);
        startActivity(siguiente);
    }

    //Abrir Activity  de pendiente
    public void pendiente (View view){
        Intent siguiente = new Intent(this,Pendiente.class);
        startActivity(siguiente);
    }

    //Abrir Activity  de PuntoRecta
    public void punto_recta (View view){
        Intent siguiente = new Intent(this, PuntoRecta.class);
        startActivity(siguiente);
    }

    //Abrir Activity  de Vectores
    public void vector (View view){
        Intent siguiente = new Intent(this, Vector.class);
        startActivity(siguiente);
    }

    //Abrir Activity  de Circunferencia
    public void circunferencia (View view){
        Intent siguiente = new Intent(this, Circunferencia.class);
        startActivity(siguiente);
    }


    //Abrir Activity  de hipervola
    public void hipervola (View view){
        Intent siguiente = new Intent(this, Hipervola.class);
        startActivity(siguiente);
    }

    //Abrir Activity  de conica
    public void comica (View view){
        Intent siguiente = new Intent(this, Conica.class);
        startActivity(siguiente);
    }

    //Abrir Activity  de paravola
    public void paravola (View view){
        Intent siguiente = new Intent(this, Paravola.class);
        startActivity(siguiente);
    }
}
