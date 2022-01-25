package com.example.galigeometriaanalitica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

public class PuntoMedio extends AppCompatActivity  {
    EditText x1, x2, xpm, y1,y2, ypmn;
    TextView tv_x1, tv_x2, tv_xpm, tv_y1, tv_y2, tv_ypm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punto_medio);

        x1 = (EditText) findViewById(R.id.et_x1pm);
        x2 = (EditText) findViewById(R.id.et_x2pm);
        xpm = (EditText) findViewById(R.id.et_xpm);
        y1 = (EditText) findViewById(R.id.et_y1pm);
        y2 = (EditText) findViewById(R.id.et_y2n);
        ypmn = (EditText) findViewById(R.id.et_ypmn);


        tv_x1 = (TextView) findViewById(R.id.tv_x1);
        tv_x2 = (TextView) findViewById(R.id.tv_x2);
        tv_xpm = (TextView) findViewById(R.id.tv_xpm);
        tv_y1 = (TextView) findViewById(R.id.tv_y1);
        tv_y2 = (TextView) findViewById(R.id.tv_y2);
        tv_ypm = (TextView) findViewById(R.id.tv_ypm);




    }
    public void calcular_y_validarpm (View view)
    {
        String x1s = x1.getText().toString();
        String x2s = x2.getText().toString();
        String xpms = xpm.getText().toString();
       String y1s = y1.getText().toString();
       String y2s = y2.getText().toString();
        String ypms = ypmn.getText().toString();//x2






        // Validamos que campor estn en funcionamiento

            if (xpms.equals("") && ypms.equals("") && x1s.length() != 0 && x2s.length() != 0 && y1s.length() != 0 && y2s.length() != 0) {//para sacar punt medio

                double x1i = Double.parseDouble(x1s);
                double x2i = Double.parseDouble(x2s);
                double y1i = Double.parseDouble(y1s);
                double y2i = Double.parseDouble(y2s);

                    float xpm = (float) (x1i + x2i) / 2;
                    float ypm = (float) (y1i + y2i) / 2;
                    String rxpm = String.valueOf(xpm);
                    String rypm = String.valueOf(ypm);
                    tv_xpm.setText(rxpm);
                    tv_ypm.setText(rypm);





            }

            else if (x1s.equals("") && y1s.equals("") && x2s.length() != 0 && y2s.length() != 0 && xpms.length() != 0 && ypms.length() != 0 ) {//para sacar punto 1

                double x2i = Double.parseDouble(x2s);
                double y2i = Double.parseDouble(y2s);
                double  xpmi = Double.parseDouble(xpms);
                double ypmi = Double.parseDouble(ypms);


                    PuntoMedio_extremos mensajero = new PuntoMedio_extremos(x2i,y2i,xpmi,ypmi);
                    double extremox = mensajero.xr;
                    double extremoy = mensajero.yr;
                    tv_x1.setText(String.format("%.2f", extremox));
                    tv_y1.setText(String.format("%.2f",extremoy));

            }

            else if (x2s.equals("") && y2s.equals("") && x1s.length() != 0 && y1s.length() != 0 &&  xpms.length() != 0 && ypms.length() != 0 ) {//punto dos

                double x1i = Double.parseDouble(x1s);
                double y1i = Double.parseDouble(y1s);
                double  xpmi = Double.parseDouble(xpms);
                double ypmi = Double.parseDouble(ypms);

                PuntoMedio_extremos mensajero = new PuntoMedio_extremos(x1i,y1i,xpmi,ypmi);
                double extremox = mensajero.xr;
                double extremoy = mensajero.yr;
                tv_x1.setText(String.format("%.2f", extremox));
                tv_y1.setText(String.format("%.2f",extremoy));

            }

            else {
                Toast.makeText(this, "Verifica los campos", Toast.LENGTH_SHORT).show();
            }


    }
    public void borrarpm (View view){
        x1.setText("");
        y1.setText("");
        x2.setText("");
        y2.setText("");
        xpm.setText("");
        ypmn.setText("");

        tv_x1.setText("");
        tv_y1.setText("");
        tv_x2.setText("");
        tv_y2.setText("");
        tv_xpm.setText("");
        tv_ypm.setText("");



    }


    public static class PuntoMedio_extremos {
        private double x, y, xpm, ypm;
        double xr, yr;

        public PuntoMedio_extremos (double x,
                                    double y,
                                    double xpm,
                                    double ypm){
            this.x = x;
            this.y =y;
            this.xpm=xpm;
            this.ypm=ypm;


            xr = 2 * xpm - x;
            yr = 2 * ypm - y;


        }
    }
}





