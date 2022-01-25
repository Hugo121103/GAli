package com.example.galigeometriaanalitica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Circunferencia extends AppCompatActivity {
    EditText et_h, et_k, et_rformula, et_d, et_e, et_f, et_x, et_y, et_r, et_xcuadrada, et_ycuadrada;
    Switch sw1;
    TextView cuadrado;


    private String xsigno = "-";//variable para cambar el signo a la ecuacion canonica en x
    private String ysigno = "-";//variable para cambar el signo a la ecuacion canonica en y
    private String signo;//variable para el metod cambiar signo
    private boolean estado;//marcadores para verificar el estado de la ecuacion, si es f(x) o f(y)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circunferencia);
        et_h = (EditText) findViewById(R.id.et_h_c);
        et_k = (EditText) findViewById(R.id.et_k_c);
        et_rformula = (EditText) findViewById(R.id.et_r_formula_c);
        et_d = (EditText) findViewById(R.id.et_d_c);
        et_e = (EditText) findViewById(R.id.et_e_c);
        et_f = (EditText) findViewById(R.id.et_f_c);
        et_x = (EditText) findViewById(R.id.et_centro_x_c);
        et_y = (EditText) findViewById(R.id.et_centro_y_c);
        et_r = (EditText) findViewById(R.id.et_radio_c);
        et_xcuadrada = (EditText) findViewById(R.id.et_xcuadrada_c);
        et_ycuadrada = (EditText) findViewById(R.id.et_ycuadrada_c);

        sw1 = (Switch) findViewById(R.id.sw_cuadrado_c);
        cuadrado = (TextView) findViewById(R.id.tv_cuadrado_c);
        Button button = findViewById(R.id.button9);

        final Button btn_x = findViewById(R.id.button6);
        final Button btn_y = findViewById(R.id.button15);




        final GraphView graph = (GraphView) findViewById(R.id.graph2);

        iniciar();



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String hs, ks, rformula_s, ds, es, fs, xorigen_s, yorigen_s, xcuadrada_s, ycuadrada_s, radios;


                hs = et_h.getText().toString();
                ks = et_k.getText().toString();
                rformula_s = et_rformula.getText().toString();
                ds = et_d.getText().toString();
                es = et_e.getText().toString();
                fs = et_f.getText().toString();
                xorigen_s = et_x.getText().toString();
                yorigen_s = et_y.getText().toString();
                xcuadrada_s = et_xcuadrada.getText().toString();
                ycuadrada_s = et_ycuadrada.getText().toString();


                radios = et_r.getText().toString();




                //////VALIDACION DE CAMPOS

                if (xorigen_s.length() != 0 && yorigen_s.length() != 0 && radios.length() != 0 && fs.equals("") && es.equals("") && ds.equals("") && rformula_s.equals("") && ks.equals("") && hs.equals("")) { //Calcular a partir de putno y radio
                    double radio = Double.parseDouble(radios);
                    double xcentro = Double.parseDouble(xorigen_s);
                    double ycentro = Double.parseDouble(yorigen_s);
                    cuadrado.setText("");

                    double radio_cuadrado = radio * radio;
                    String radio_elevado = String.valueOf(radio_cuadrado);

                    et_h.setHint(xorigen_s);
                    et_k.setHint(yorigen_s);
                    et_rformula.setHint(radio_elevado);

                    if (xcentro > 0){
                        et_h.setHint(xorigen_s);
                        btn_x.setText("x -");
                    }else {
                        double xabs = Math.abs(xcentro);

                        btn_x.setText("x +");
                        String xabss = String.valueOf(xabs);
                        et_h.setHint(xabss);
                    }
                    if (ycentro > 0){
                        et_k.setHint(yorigen_s);
                        btn_y.setText("y -");
                    }else {
                        double yabs = Math.abs(ycentro);

                        btn_y.setText("y +");
                        String yabss = String.valueOf(yabs);
                        et_k.setHint(yabss);
                    }


                    //Calculaos la ecuacion general

                    //producto del biniomio al caudrado
                    double binomiox_lineal= (2 * xcentro) * -1;
                    double binomioy_lineal = (2 * ycentro) * -1;

                    double binomio1_independiente = xcentro * xcentro;
                    double binomio2_independiente = ycentro * ycentro;


                    double radio_despejado = radio_cuadrado * -1;//pasamos el radio al lado derecho de la igualdad
                    double f = radio_despejado + binomio2_independiente + binomio1_independiente;

                    String d = String.valueOf(binomiox_lineal);
                    String e = String.valueOf(binomioy_lineal);
                    String fr = String.valueOf(f);

                    et_d.setHint(d);
                    et_e.setHint(e);
                    et_f.setHint(fr);


                    /////////////////GRAFICAMOS



                    double xorigen = xcentro - radio;
                    double xfin = 2 * xcentro - xorigen;


                    while (xfin >= xorigen) {
                        double binomio = (xorigen - xcentro);
                        double cuadrado = binomio * binomio;
                        double resta = cuadrado - radio_cuadrado;
                        double raiz = Math.sqrt(Math.abs(resta));
                        double y = raiz + ycentro;


                        double y_inverso = (2 * ycentro) - y;
                        LineGraphSeries<DataPoint> circulo = new LineGraphSeries<>(new DataPoint[]{


                                new DataPoint(xorigen, y),
                                new DataPoint(xorigen, y_inverso)


                        });
                        graph.addSeries(circulo);
                        circulo.setColor(Color.GREEN);//color
                        circulo.setThickness(4);//espesr
                        xorigen = xorigen + .05;

                    }
                    LineGraphSeries<DataPoint> circulof = new LineGraphSeries<>(new DataPoint[]{


                            new DataPoint(xcentro, ycentro),
                            new DataPoint(xfin, ycentro)


                    });
                    graph.addSeries(circulof);
                    circulof.setColor(Color.BLACK);//color
                    circulof.setThickness(6);//espesr

                }

                else if (hs.length() != 0 && ks.length() != 0 && rformula_s.length() != 0 && ds.equals("") && es.equals("") && fs.equals("") && xorigen_s.equals("") && yorigen_s.equals("") && radios.equals("")) {//Ecuacion canonica

                    double h = Double.parseDouble(hs);
                    double k = Double.parseDouble(ks);
                    double rformula = Double.parseDouble(rformula_s);


                    //verificamos ls signos
                    if (xsigno.equals("-") ) {//verificamos el valor
                        et_x.setHint(hs);


                    }
                    else if (xsigno.equals("+")){
                        h = h * -1;
                        String xs = String.valueOf(h);
                        et_x.setHint(xs);



                    }
                    if (ysigno.equals("+")){
                        k = k *-1;
                        String ys = String.valueOf(k);
                        et_y.setHint(ys);


                    }else if (ysigno.equals("-")) {
                        et_y.setHint(ks);
                    }
                    ////////////Hacemos lo procedimientos

                        if (sw1.isChecked()) {//swith prendido, si el radio no esta elevado al cuadrado
                            cuadrado.setText("");
                            double r = Math.sqrt(rformula);
                            String rs = String.valueOf(r);
                            et_r.setHint(rs);


                            /////////////////GRAFICAMOS


                            double xorigen = h - r;//
                            double xfin = 2 * h - xorigen;


                            while (xfin >= xorigen) {//siguiendo la formula y=√[r²-(x-h)]+k
                                double binomio = (xorigen - h);
                                double cuadrado = binomio * binomio;
                                double resta = cuadrado - rformula;
                                double raiz = Math.sqrt(Math.abs(resta));
                                double y = raiz + k;


                                double y_inverso = (2 * k) - y;//lado opuesto en las absisas con respecto al diamrtro d ela circunferencia
                                LineGraphSeries<DataPoint> circulo = new LineGraphSeries<>(new DataPoint[]{


                                        new DataPoint(xorigen, y),
                                        new DataPoint(xorigen, y_inverso)


                                });
                                graph.addSeries(circulo);
                                circulo.setColor(Color.GREEN);//color
                                circulo.setThickness(4);//espesr
                                xorigen = xorigen + .05;

                            }
                            LineGraphSeries<DataPoint> circulof = new LineGraphSeries<>(new DataPoint[]{


                                    new DataPoint(h, k),
                                    new DataPoint(xfin, k)


                            });
                            graph.addSeries(circulof);
                            circulof.setColor(Color.BLACK);//color
                            circulof.setThickness(6);//espesr


                        } else {//si el radioe esta elevado al cuadrado
                            cuadrado.setText("²");
                            et_r.setHint(rformula_s);//el valor del cuadrado sigue siendo el mismo, pues raiz cuadrada de un cuadrado se cancela


                            /////////////////GRAFICAMOS


                            double xorigen = h - rformula;
                            double xfin = 2 * h - xorigen;


                            while (xfin >= xorigen) {
                                double binomio = (xorigen - h);
                                double cuadrado = binomio * binomio;
                                double resta = cuadrado - (rformula * rformula);
                                double raiz = Math.sqrt(Math.abs(resta));
                                double y = raiz + k;


                                double y_inverso = (2 * k) - y;
                                LineGraphSeries<DataPoint> circulo = new LineGraphSeries<>(new DataPoint[]{


                                        new DataPoint(xorigen, y),
                                        new DataPoint(xorigen, y_inverso)


                                });
                                graph.addSeries(circulo);
                                circulo.setColor(Color.GREEN);//color
                                circulo.setThickness(4);//espesr
                                xorigen = xorigen + .05;

                            }
                            LineGraphSeries<DataPoint> circulof = new LineGraphSeries<>(new DataPoint[]{


                                    new DataPoint(h, k),
                                    new DataPoint(xfin, k)


                            });
                            graph.addSeries(circulof);
                            circulof.setColor(Color.BLACK);//color
                            circulof.setThickness(6);//espesr


                        }



                        //producto del biniomio al caudrado
                    double binomiox_lineal= (2 * h) * -1;
                    double binomioy_lineal = (2 * k) * -1;


                    //resto del procedimiento
                        double binomio1_independiente = h * h;//b al cuadrado
                        double binomio2_independiente = k * k;//b al caudrado del segundo miembro de la ecuacion

                        double radio_despejado;
                        if (sw1.isChecked()) {
                            radio_despejado = rformula * -1;//pasamos el radio al lado derecho de la igualdad
                        }else{
                            radio_despejado = (rformula*rformula) *-1;
                        }
                        double f = radio_despejado + binomio2_independiente + binomio1_independiente;

                        String d = String.valueOf(binomiox_lineal);
                        String e = String.valueOf(binomioy_lineal);
                        String fr = String.valueOf(f);

                        et_d.setHint(d);
                        et_e.setHint(e);
                        et_f.setHint(fr);




                }

                else if (ds.length() != 0 && es.length() != 0 && et_xcuadrada.length() == 0 && et_ycuadrada.length() == 0 && fs.length() != 0 && xorigen_s.equals("") && yorigen_s.equals("") && radios.equals("") && rformula_s.equals("") && ks.equals("") && hs.equals("")) {//Ecuacion general
                    cuadrado.setText("²");

                    double d = Double.parseDouble(ds);
                    double e = Double.parseDouble(es);
                    double f = Double.parseDouble(fs);

                    double x = (d*-1) / 2;
                    double y = (e *-1) / 2;


                    double r_suma = (d * d) + (e * e) - (4 * f);
                    double r_sqrt = Math.sqrt(r_suma);
                    double r = .5 * r_sqrt;




                    String xg = String.valueOf(x);
                    String yg = String.valueOf(y);
                    String rg = String.valueOf(r);
                    et_x.setHint(xg);
                    et_y.setHint(yg);
                    et_r.setHint(rg);

                    if (d < 0){
                        et_h.setHint(xg);
                        btn_x.setText("x -");
                    }else {
                        double xabs = Math.abs(x);

                        btn_x.setText("x +");
                        String xabss = String.valueOf(xabs);
                        et_h.setHint(xabss);
                    }
                    if (e < 0){
                        et_k.setHint(yg);
                        btn_y.setText("y -");
                    }else {
                        double yabs = Math.abs(y);

                        btn_y.setText("y +");
                        String yabss = String.valueOf(yabs);
                        et_k.setHint(yabss);
                    }


                    et_rformula.setHint(rg);







                }

                else if (ds.length() != 0 && es.length() != 0 && fs.length() != 0 && et_xcuadrada.length() != 0 && et_ycuadrada.length() != 0 && xorigen_s.equals("") && yorigen_s.equals("") && radios.equals("") && rformula_s.equals("") && ks.equals("") && hs.equals("")) {
                    double xpov_1 = Double.parseDouble(xcuadrada_s);
                    double ypov_1 = Double.parseDouble(ycuadrada_s);

                    if (xpov_1 == ypov_1) {


                        double d_1 = Double.parseDouble(ds);
                        double e_1 = Double.parseDouble(es);
                        double f_1 = Double.parseDouble(fs);


                        double d = d_1 / xpov_1;
                        double e = e_1 / xpov_1;
                        double f = f_1 / xpov_1;


                        cuadrado.setText("");




                        double x = (d / 2)*-1;
                        double y = (e / 2)*-1;

                        double r_suma = (d * d) + (e * e) - (4 * f);
                        double r_sqrt = Math.sqrt(r_suma);
                        double r = .5 * r_sqrt;

                        String xg = String.valueOf(x);
                        String yg = String.valueOf(y);
                        String rg = String.valueOf(r);

                        cuadrado.setText("");
                        if (d < 0){
                            et_h.setHint(xg);
                            btn_x.setText("x -");
                        }else {
                            double xabs = Math.abs(x);

                            btn_x.setText("x +");
                            String xabss = String.valueOf(xabs);
                            et_h.setHint(xabss);
                        }
                        if (e < 0){
                            et_k.setHint(yg);
                            btn_y.setText("y -");
                        }else {
                            double yabs = Math.abs(x);

                            btn_y.setText("y + ");
                            String yabss = String.valueOf(yabs);
                            et_k.setHint(yabss);
                        }
                        et_rformula.setHint(rg);

                        et_x.setHint(xg);
                        et_y.setHint(yg);
                        et_r.setHint(rg);


                    }
                }

                else {
                    Toast.makeText(Circunferencia.this, "Ingresa correctamente los datos. ¡Te sugerimos ver el tutorial de usuario anexado!", Toast.LENGTH_LONG).show();
                }


            }
        });

    }

    public void borrar_c(View view) {

        et_h.setText("");
        et_k.setText("");
        et_rformula.setText("");
        et_d.setText("");
        et_e.setText("");
        et_f.setText("");
        et_x.setText("");
        et_y.setText("");
        et_r.setText("");
        et_xcuadrada.setText("");
        et_ycuadrada.setText("");
        if (sw1.isChecked()) {//swith prendido
            cuadrado.setText("");
        }else {
            cuadrado.setText("²");
        }

        et_h.setHint("h");
        et_k.setHint("k");
        et_rformula.setHint("r");
        et_d.setHint("D");
        et_e.setHint("E");
        et_f.setHint("F");
        et_x.setHint("x");
        et_y.setHint("y");
        et_r.setHint("r");
        et_xcuadrada.setHint("");
        et_ycuadrada.setHint("");
        xsigno = "-";//variable para cambar el signo a la ecuacion canonica en x
        ysigno = "-";//variable para cambar el signo a la ecuacion canonica en y
         Button btn_x = findViewById(R.id.button6);
         Button btn_y = findViewById(R.id.button15);
        btn_x.setText("x " + xsigno);
        btn_y.setText("y " + xsigno);





    }

    private void iniciar() {
        final GraphView graph = (GraphView) findViewById(R.id.graph2);
        //activar el zoom horizontal y el desplazamiento
        graph.getViewport().setScalable(true);


        //activar desplazamiento horizontal
        graph.getViewport().setScrollable(true);

        // activar el zoom vertical y el desplazamiento
        graph.getViewport().setScalableY(true);

        //activar desplazamiento vertical
        graph.getViewport().setScrollableY(true);

        // establecer límites X manuales
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0.5);
        graph.getViewport().setMaxX(200);

        // establecer límites Y manuales
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(3.5);
        graph.getViewport().setMaxY(200);

        LineGraphSeries<DataPoint> seriesOnCreate = new LineGraphSeries<>(new DataPoint[]{


                new DataPoint(-100, 100),
                new DataPoint(-100, -100)
        });
        graph.addSeries(seriesOnCreate);
        seriesOnCreate.setColor(Color.BLACK);//color
        seriesOnCreate.setThickness(1);//espesr

        LineGraphSeries<DataPoint> seriesOnCreate2 = new LineGraphSeries<>(new DataPoint[]{


                new DataPoint(100, 100),
                new DataPoint(100, -100)
        });
        graph.addSeries(seriesOnCreate2);
        seriesOnCreate2.setColor(Color.BLACK);//color
        seriesOnCreate2.setThickness(1);//espesr-
    }

    public void limpiar_c (View view){
        final GraphView graph = (GraphView) findViewById(R.id.graph2);
        graph.removeAllSeries();
        iniciar();

    }

    //metodos para cambiar el signo, de "x" y de "y"

   private void cambiamos_signo (){

    if (estado == false ){

        signo = "+";
        estado = true;
    }else{

        signo = "-";
        estado = false;

    }

    }

    public void cambiar_signo_x_c (View view){
        Button btn_x = findViewById(R.id.button6);
        cambiamos_signo();

        xsigno = signo;
        btn_x.setText("x " + xsigno);

    }

    public void cambiar_signo_y_c (View view){
        Button btn_y = findViewById(R.id.button15);
        cambiamos_signo();
        ysigno = signo;
        btn_y.setText("y "+ ysigno);


    }




   /* public void graficar(double xcentro,
                         double ycentro,
                         double radio,
                         double radio_cuadrado){//luego

    final GraphView graph = (GraphView) findViewById(R.id.graph2);


    /////////////////GRAFICAMOS


    double xorigen = xcentro - radio;
    double xfin = 2 * xcentro - xorigen;





        while(xfin >=xorigen)

    {
        double binomio = (xorigen - xcentro);
        double cuadrado = binomio * binomio;
        double resta = cuadrado - radio_cuadrado;
        double raiz = Math.sqrt(Math.abs(resta));
        double y = raiz + ycentro;


        double y_inverso = (2 * ycentro) - y;
        LineGraphSeries<DataPoint> circulo = new LineGraphSeries<>(new DataPoint[]{


                new DataPoint(xorigen, y),
                new DataPoint(xorigen, y_inverso)


        });
        graph.addSeries(circulo);
        circulo.setColor(Color.GREEN);//color
        circulo.setThickness(4);//espesr
        xorigen = xorigen + .05;

    }

    LineGraphSeries<DataPoint> circulof = new LineGraphSeries<>(new DataPoint[]{


            new DataPoint(xcentro, ycentro),
            new DataPoint(xfin, ycentro)


    });
        graph.addSeries(circulof);
        circulof.setColor(Color.BLACK);//color
        circulof.setThickness(6);//espesr

}*/

}
