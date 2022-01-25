package com.example.galigeometriaanalitica;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Graficar_paravola extends AppCompatActivity {

    private double h,k,p,x,y, subh,subk;
    private String x_y;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficar_paravola);

        h = getIntent().getDoubleExtra("h",h);
        k = getIntent().getDoubleExtra("k",k);
        p = getIntent().getDoubleExtra("p",p);
        x_y = getIntent().getStringExtra("x_y");

        subh = h;
        subk = k;
        iniciar();




        if (x_y.equals("x")){
            graficar_para_x();
        }else{
            graficar_para_y();
        }

    }



    private void graficar_para_x (){


        if (p > 0) {//si la grafica va para arriba
            //sacamos el origen de la funcion, el cual estara en y=100 y evaluaremos x con √[p(y-k)]-h
            double restamso = 99 - k;
            double mlt_por_p = restamso * p;
            double raiz = Math.sqrt(mlt_por_p);
            double subxinicio = raiz - h;
            double xinicio = subxinicio * -1;


            final GraphView graph = (GraphView) findViewById(R.id.graph_paravola);
            //la formula para graficar la paravola es: y={[(x-h)²]/p}+k o viseversa, dependindo si la directriz es perpendicular al eje x o y
            for (x = xinicio; y <= 100; x = x + .3) {

                double xmenosh = x - subh;//subh es la constante de la vertice
                double binomiocuadrado = xmenosh * xmenosh;
                double entre_p = binomiocuadrado / p;
                y = entre_p + subk;//subk es la constante de la vertice
                if (x == xinicio) {
                    h = x;
                    k = y;
                }


                LineGraphSeries<DataPoint> paravoal = new LineGraphSeries<>(new DataPoint[]{


                        new DataPoint(h, k),
                        new DataPoint(x, y)


                });
                graph.addSeries(paravoal);
                paravoal.setColor(Color.GREEN);//color
                paravoal.setThickness(4);//espesr
                h = x;
                k = y;


            }
        }else {//si la grafica va para abajo
            double restamso = -99 - k;
            double mlt_por_p = restamso * p;
            double raiz = Math.sqrt(mlt_por_p);
            double subxinicio = raiz - h;
            double xinicio = subxinicio * -1;


            final GraphView graph = (GraphView) findViewById(R.id.graph_paravola);
            //la formula para graficar la paravola es: y={[(x-h)²]/p}+k o viseversa, dependindo si la directriz es perpendicular al eje x o y
            for (x = xinicio; y >= -100; x = x + .3) {

                double xmenosh = x - subh;//subh es la constante de la vertice
                double binomiocuadrado = xmenosh * xmenosh;
                double entre_p = binomiocuadrado / p;
                y = entre_p + subk;//subk es la constante de la vertice
                if (x == xinicio) {
                    h = x;
                    k = y;
                }


                LineGraphSeries<DataPoint> paravoal = new LineGraphSeries<>(new DataPoint[]{


                        new DataPoint(h, k),
                        new DataPoint(x, y)


                });
                graph.addSeries(paravoal);
                paravoal.setColor(Color.GREEN);//color
                paravoal.setThickness(4);//espesr
                h = x;
                k = y;

            }
        }



    }

    public void graficar_para_y (){

        if (p > 0) {//si la grafica va para la derecha
            //sacamos el origen de la funcion, el cual estara en x=100 y evaluaremos y con √[p(x-h)]-k
            double restamso = 99 - h;
            double mlt_por_p = restamso * p;
            double raiz = Math.sqrt(mlt_por_p);
            double subyinicio = raiz - k;
            double yinicio = subyinicio * -1;


            final GraphView graph = (GraphView) findViewById(R.id.graph_paravola);
            //la formula para graficar la paravola es: y={[(x-h)²]/p}+k o viseversa, dependindo si la directriz es perpendicular al eje x o y
            for (y = yinicio; x <= 100; y = y + .3) {

                double ymenosh = y - subk;//subh es la constante de la vertice
                double binomiocuadrado = ymenosh * ymenosh;
                double entre_p = binomiocuadrado / p;
                x = entre_p + subh;//subk es la constante de la vertice
                if (y == yinicio) {
                    h = x;
                    k = y;
                }

                if (x < h || x == h) {
                    LineGraphSeries<DataPoint> paravoal = new LineGraphSeries<>(new DataPoint[]{


                            new DataPoint(x, y),
                            new DataPoint(h, k)


                    });
                    graph.addSeries(paravoal);
                    paravoal.setColor(Color.GREEN);//color
                    paravoal.setThickness(4);//espesr
                }else if (x>h){
                    LineGraphSeries<DataPoint> paravoal = new LineGraphSeries<>(new DataPoint[]{


                            new DataPoint(h, k),
                            new DataPoint(x, y)


                    });
                    graph.addSeries(paravoal);
                    paravoal.setColor(Color.GREEN);//color
                    paravoal.setThickness(4);//espesr

                    }
                h = x;
                k = y;


            }
        }


        else {//si la grafica va para la izquierda
            //sacamos el origen de la funcion, el cual estara en x=100 y evaluaremos y con √[p(x-h)]-k
            double restamso = -99 - h;
            double mlt_por_p = restamso * p;
            double raiz = Math.sqrt(mlt_por_p);
            double subyinicio = raiz - k;
            double yinicio = subyinicio * -1;


            final GraphView graph = (GraphView) findViewById(R.id.graph_paravola);
            //la formula para graficar la paravola es: y={[(x-h)²]/p}+k o viseversa, dependindo si la directriz es perpendicular al eje x o y
            for (y = yinicio; x >= -100; y = y + .3) {

                double ymenosh = y - subk;//subh es la constante de la vertice
                double binomiocuadrado = ymenosh * ymenosh;
                double entre_p = binomiocuadrado / p;
                x = entre_p + subh;//subk es la constante de la vertice
                if (y == yinicio) {
                    h = x;
                    k = y;
                }


                if (x < h || x == h) {
                    LineGraphSeries<DataPoint> paravoal = new LineGraphSeries<>(new DataPoint[]{


                            new DataPoint(x, y),
                            new DataPoint(h, k)


                    });
                    graph.addSeries(paravoal);
                    paravoal.setColor(Color.GREEN);//color
                    paravoal.setThickness(4);//espesr
                }else if (x>h){
                    LineGraphSeries<DataPoint> paravoal = new LineGraphSeries<>(new DataPoint[]{


                            new DataPoint(h, k),
                            new DataPoint(x, y)


                    });
                    graph.addSeries(paravoal);
                    paravoal.setColor(Color.GREEN);//color
                    paravoal.setThickness(4);//espesr

                }                h = x;
                k = y;


            }
        }


    }



    private void iniciar () {

        final GraphView graph = (GraphView) findViewById(R.id.graph_paravola);
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


}

