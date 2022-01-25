package com.example.galigeometriaanalitica;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class PuntoRecta extends AppCompatActivity {
    EditText a, b, c, x, y;
    TextView distancia, Af, xf, masf1, Bf, yf, masf2, Cf, Afc, Bfc, masf3, numerador, denominador, igual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punto_recta);


        a = (EditText) findViewById(R.id.et_ax_pr);
        b = (EditText) findViewById(R.id.et_by_pr);
        c = (EditText) findViewById(R.id.et_c_pr);
        x = (EditText) findViewById(R.id.et_xpr);
        y = (EditText) findViewById(R.id.et_ypr);

        distancia = (TextView) findViewById(R.id.tv_distancia_pr);
        Af = (TextView) findViewById(R.id.tv_af_pr);
        xf = (TextView) findViewById(R.id.tv_xf_pr);
        masf1 = (TextView) findViewById(R.id.tv_mas1_pr);
        Bf = (TextView) findViewById(R.id.tv_bf_pr);
        yf = (TextView) findViewById(R.id.tv_yf_pr);
        masf2 = (TextView) findViewById(R.id.tv_mas2_pr);
        Cf = (TextView) findViewById(R.id.tv_cf_pr);
        Afc = (TextView) findViewById(R.id.tv_acuadradof_pr);
        Bfc = (TextView) findViewById(R.id.tv_bcuadradof_pr);
        masf3 = (TextView) findViewById(R.id.tv_mas3_pr);
        numerador = (TextView) findViewById(R.id.tv_numerador_pr);
        denominador = (TextView) findViewById(R.id.tv_denominador_pr);

        igual = (TextView) findViewById(R.id.tv_igual_pr);


    }

    public void calcular_y_validarpr(View view) {
        String as = a.getText().toString();
        String bs = b.getText().toString();
        String cs = c.getText().toString();
        String xs = x.getText().toString();
        String ys = y.getText().toString();

        if (as.length() != 0 && bs.length() != 0 && cs.length() != 0 && xs.length() != 0 && ys.length() != 0) {//validamos campos
            double a = Double.parseDouble(as);
            double b = Double.parseDouble(bs);
            double c = Double.parseDouble(cs);
            double x = Double.parseDouble(xs);
            double y = Double.parseDouble(ys);

            //ponemos los valores en las formulas
            Af.setText(as);
            xf.setText(xs);
            if (b < 0) {
                masf1.setText("-");
                double b1 = b + (b * -2);
                Bf.setText(String.format("%.1f", b1));
            } else {
                Bf.setText(bs);
            }
            yf.setText(ys);
            if (c < 0) {
                masf2.setText("-");
                double c1 = c + (c * -2);
                Cf.setText(String.format("%.1f", c1));
            } else {
                Cf.setText(cs);
            }
            Afc.setText(as);
            Bfc.setText(bs);


            ///calculo
            double multax = a * x;
            double multby = b * y;
            double numeradord = multax + multby + c;
            double numeradorAbs = Math.abs(numeradord);
            double Aelevada = a * a;
            double Belevada = b * b;
            double denominadord = Aelevada + Belevada;
            double denominadorsqrt = Math.sqrt(denominadord);
            double distanciad = numeradorAbs / denominadorsqrt;


            numerador.setText(String.format("%.1f", numeradorAbs));

            denominador.setText("√" + String.format("%.1f", denominadord));
            igual.setText("=");
            distancia.setText(String.format("%.4f", distanciad));


        } else {
            Toast.makeText(this, "¡Verifica los campos!", Toast.LENGTH_SHORT).show();
        }
    }

    public void Borrarpr(View view) {
        a.setText("");
        a.setHint("A");
        b.setText("");
        b.setHint("B");
        c.setText("");
        c.setHint("C");
        x.setText("");
        x.setHint("x");
        y.setText("");
        y.setHint("y");

        distancia.setText("");
        Af.setText("A");
        xf.setText("x");
        masf1.setText("+");
        Bf.setText("B");
        yf.setText("y");
        masf2.setText("+");
        Cf.setText("C");
        Afc.setText("A");
        Bfc.setText("B");
        masf3.setText("+");
        numerador.setText("");
        denominador.setText("");
        igual.setText("");

    }
}