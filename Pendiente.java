package com.example.galigeometriaanalitica;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

public class Pendiente extends AppCompatActivity {
    EditText et_x1, et_x2, et_y1, et_y2, et_x1_formula, et_y1_formula, et_m_formula, et_pendiente, et_grados;
    TextView  formula;


    Button Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendiente);
        final GraphView graph = (GraphView) findViewById(R.id.graph1);

        Button calcular = findViewById(R.id.button4);


        et_x1 = (EditText)findViewById(R.id.et_x1m);
        et_x2 = (EditText)findViewById(R.id.et_x2m);
        et_y1 = (EditText)findViewById(R.id.et_y1m);
        et_y2 = (EditText)findViewById(R.id.et_y2m);
        et_x1_formula = (EditText)findViewById(R.id.et_x1_formula);
        et_y1_formula = (EditText)findViewById(R.id.et_y1_formula);
        et_m_formula = (EditText)findViewById(R.id.pendiente_formula);
        et_pendiente = (EditText)findViewById(R.id.et_pendiente);





        formula = (TextView)findViewById(R.id.tv_formula);

       iniciar();
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                String x1s, y1s, x2s, y2s, x1_f, y1_f, m_f, pendientes;



                x1s = et_x1.getText().toString();
                y1s = et_y1.getText().toString();
                x2s = et_x2.getText().toString();
                y2s = et_y2.getText().toString();
                x1_f = et_x1_formula.getText().toString();
                y1_f = et_y1_formula.getText().toString();
                m_f = et_m_formula.getText().toString();
                pendientes = et_pendiente.getText().toString();







                //////////////////////Concionamos la entrada de lso datos
                if (x1s.length() != 0 && x2s.length() != 0 && y1s.length() != 0 && y2s.length() != 0 && x1_f.equals("") && y1_f.equals("") && m_f.equals("") && pendientes.equals("")){//condicional para la calcular si te dan 2 puntos
                    double x1 = Double.parseDouble(x1s);
                    double y1 = Double.parseDouble(y1s);
                    double x2 = Double.parseDouble(x2s);
                    double y2 = Double.parseDouble(y2s);
                    double m =  (y1 - y2) / (x1 - x2);//calculamos la pendiente, y la casteamos
                    et_pendiente.setHint(String.format("%.2f", m));


                    et_m_formula.setHint(String.format("%.2f", m));
                    et_x1_formula.setHint(x1s);
                    et_y1_formula.setHint(y1s);


                    //calculamso de formula despejada
                    double quitamos_parentecis = m * x1 * -1;
                    double multiplicamos_y = -1 * y1;
                    double cambiamos_y = multiplicamos_y * -1;
                    double sumamos = quitamos_parentecis +cambiamos_y;
                    if (sumamos > 0) {
                        formula.setText("y " + "= " + String.format("%.2f", m) + "x " + "+" + String.format("%.3f", sumamos));
                    }else{
                        formula.setText("y " + "= " + String.format("%.2f", m) + "x " +  String.format("%.3f", sumamos));
                    }


                    //graficamos

                    if (x1 > x2) {

                            LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[]{

                                    new DataPoint(x2, y2),
                                    new DataPoint(x1, y1)


                            });
                            graph.addSeries(series2);
                            series2.setColor(Color.GREEN);//color
                            series2.setThickness(4);//espesr

                            /////putnos

                    }else {

                            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{


                                    new DataPoint(x1, y1),

                                    new DataPoint(x2, y2)
                            });
                            graph.addSeries(series);
                            series.setColor(Color.GREEN);//color
                            series.setThickness(4);//espesr


                    }
                    ////////////////////////exportar



                }






                else if(pendientes.equals("")  && x1s.equals("") && x2s.equals("") && y1s.equals("") && y2s.equals("") && x1_f.length() != 0 && y1_f.length() != 0 && m_f.length() != 0){//pendiente dada la ecuacion
                    double x1 = Double.parseDouble(x1_f);
                    double  y1 = Double.parseDouble(y1_f);
                    double m = Double.parseDouble(m_f);//que no se te olvide parsear bien xd
                    double quitamos_parentecis =  m * x1 * -1;
                    double multiplicamos_y = -1 * y1;
                    double cambiamos_y = multiplicamos_y * -1;
                    double sumamos = quitamos_parentecis +cambiamos_y;
                    if (sumamos > 0) {
                        formula.setText("y " + "= " + String.format("%.2f", m) + "x " + "+" + String.format("%.3f", sumamos));
                    }else{
                        formula.setText("y " + "= " + String.format("%.2f", m) + "x " +  String.format("%.3f", sumamos));
                    }

                    /////////////////////////graficamos

                    double x_graficar =  (sumamos * -1)/m;
                    if (x_graficar > 0){
                        LineGraphSeries<DataPoint> seriesOnCreate = new LineGraphSeries<>(new DataPoint[]{


                                new DataPoint(0,sumamos),
                                new DataPoint(x_graficar,0)
                        });
                        graph.addSeries(seriesOnCreate );
                        seriesOnCreate .setColor(Color.GREEN);//color
                        seriesOnCreate .setThickness(1);//espesr
                    }else{
                        LineGraphSeries<DataPoint> seriesOnCreate = new LineGraphSeries<>(new DataPoint[]{

                                new DataPoint(x_graficar,0),
                                new DataPoint(0,sumamos)

                        });
                        graph.addSeries(seriesOnCreate );
                        seriesOnCreate .setColor(Color.GREEN);//color
                        seriesOnCreate .setThickness(1);//espesr
                    }





                }

                else if (x1s.length() != 0 && y1s.length() != 0 && pendientes.length() != 0  && x2s.equals("") && y2s.equals("") && m_f.equals("") && x1_f.equals("")  && y1_f.equals("")){//punto pendiente
                    double y1 = Double.parseDouble(y1s);
                    double x1 = Double.parseDouble(x1s);
                    double m = Double.parseDouble(pendientes);
                    et_x1_formula.setHint(x1s);
                    et_y1_formula.setHint(y1s);
                    et_m_formula.setHint(String.format("%.2f", m));



                    double quitamos_parentecis = m * x1 * -1;
                    double multiplicamos_y = -1 * y1;
                    double cambiamos_y = multiplicamos_y * -1;
                    double sumamos = quitamos_parentecis +cambiamos_y;


                    if (sumamos > 0) {
                        formula.setText("y " + "= " + String.format("%.2f", m) + "x " + "+" + String.format("%.3f", sumamos));
                    }else{
                        formula.setText("y " + "= " + String.format("%.2f", m) + "x " +  String.format("%.3f", sumamos));
                    }

                    //////////////////////grafficamos
                    double x_graficar =  (sumamos * -1)/m;
                    if (x_graficar > 0){
                        LineGraphSeries<DataPoint> seriesOnCreate = new LineGraphSeries<>(new DataPoint[]{


                                new DataPoint(0,sumamos),
                                new DataPoint(x_graficar,0)
                        });
                        graph.addSeries(seriesOnCreate );
                        seriesOnCreate .setColor(Color.GREEN);//color
                        seriesOnCreate .setThickness(4);//espesr
                    }else{
                        LineGraphSeries<DataPoint> seriesOnCreate = new LineGraphSeries<>(new DataPoint[]{

                                new DataPoint(x_graficar,0),
                                new DataPoint(0,sumamos)

                        });
                        graph.addSeries(seriesOnCreate );
                        seriesOnCreate .setColor(Color.GREEN);//color
                        seriesOnCreate .setThickness(4);//espesr
                    }

                }

                else{
                  Toast.makeText(Pendiente.this, "Ingresa correctamente los datos. ¡Te sugerimos ver el tutorial de usuario anexado!", Toast.LENGTH_LONG).show();

                }



            }
        });

    }

    public void borrarm (View view){
        et_x1.setText("");
        et_x2.setText("");
        et_y1.setText("");
        et_y2.setText("");
        et_x1_formula.setText("");
        et_y1_formula.setText("");
        et_m_formula.setText("");
        et_pendiente.setText("");



        formula.setText("");

        et_x1_formula.setHint("x1");
        et_y1_formula.setHint("y1");
        et_m_formula.setHint("m");


    }


    public void iniciar () {
        final GraphView graph = (GraphView) findViewById(R.id.graph1);
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

    public void limpiar_pendiete (View view){
        final GraphView graph = (GraphView) findViewById(R.id.graph1);
        graph.removeAllSeries();
        iniciar();

    }
}

