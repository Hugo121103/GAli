package com.example.galigeometriaanalitica;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Distancia_entre_dos_puntos extends AppCompatActivity {
    EditText et_x1, et_y1, et_x2, et_y2;
    TextView tv1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distancia_entre_dos_puntos);

        et_x1 = (EditText) findViewById(R.id.et_x1);
        et_y1 = (EditText) findViewById(R.id.et_y1);
        et_x2 = (EditText) findViewById(R.id.et_x2);
        et_y2 = (EditText) findViewById(R.id.et_y2);
        tv1 = (TextView) findViewById(R.id.resultado);
        Button button = findViewById(R.id.button);

        final GraphView graph = (GraphView) findViewById(R.id.graph);
        iniciar();


            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    String x1s, y1s;
                    String x2s, y2s;

                    //  1 and 5
                    x1s = et_x1.getText().toString();
                    y1s = et_y1.getText().toString();
                    x2s = et_x2.getText().toString();
                    y2s = et_y2.getText().toString();


                    if (et_x1.length() != 0 && et_x2.length() != 0 && et_y1.length() != 0 && et_y2.length() != 0) {
                        double x1 = Double.parseDouble(x1s);
                        double y1 = Double.parseDouble(y1s);
                        double x2 = Double.parseDouble(x2s);
                        double y2 = Double.parseDouble(y2s);


                        if (x1 > x2) {


                                LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[]{

                                        new DataPoint(x2, y2),
                                        new DataPoint(x1, y1)


                                });
                                graph.addSeries(series2);
                                series2.setColor(Color.GREEN);//color
                                series2.setThickness(4);//espesr


                        } else {

                                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{


                                        new DataPoint(x1, y1),

                                        new DataPoint(x2, y2)
                                });
                                graph.addSeries(series);
                                series.setColor(Color.GREEN);//color
                                series.setThickness(4);//espesr


                        }


                        // calculo de la distancaia
                        double subResta1 = ((x2 * x2) - (2 * x2 * x1) + (x1 * x1));
                        double subResta2 = ((y2 * y2) - (2 * y2 * y1) + (y1 * y1));
                        double suma = subResta1 + subResta2;
                        double result = Math.sqrt(suma);
                        String resultado = String.valueOf(result);
                        tv1.setText(resultado);


                    } else {
                        Toast.makeText(Distancia_entre_dos_puntos.this, "Verific los campos.", Toast.LENGTH_LONG).show();
                    }

                }

            });


        }
        public void borrar (View view){
            et_x1.setText("");
            et_y1.setText("");
            et_x2.setText("");
            et_y2.setText("");
            tv1.setText("");

            et_x1.setHint("x1");
            et_y1.setHint("y1");
            et_x2.setHint("x2");
            et_y2.setHint("y2");





        }





    public void iniciar () {
        final GraphView graph = (GraphView) findViewById(R.id.graph);
        //activar el zoom horizontal y el desplazamiento
        graph.getViewport().setScalable(true);


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

    public void limpiar_dp (View view){
        final GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.removeAllSeries();
        iniciar();


    }











    }



