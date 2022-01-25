package com.example.galigeometriaanalitica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Constructor;

public class Paravola extends AppCompatActivity {
    EditText h, P, k, A, B, C, D, xfoco, yfoco, xvertice, yvertice, directriz;
    TextView tv_xyc_anonica, flecha;

    private boolean estado = true;//marcadores para verificar el estado de la ecuacion, si es f(x) o f(y)
    private boolean estado_anterior = false;//marcadores para verificar el estado de la ecuacion, si es f(x) o f(y)
    private String x_y = "x";//estado de la ecuacion


    //marcadores para validar campos

    int ecuacon_canonica = 0;
    int vertice_foco = 0;
    int foco_directriz = 0;
    int vertice_directriz = 0;
    int ecuacion_general = 0;
    int ecuacion_general_conA = 0;

    //macadores paa el signo de la ecuacion canonica

    private String signo_binomio_cuadrado = "-";
    private String signo_binomio_segundo_termino = "-";
    private String signo;
    private boolean estado_signo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paravola);
        h = (EditText) findViewById(R.id.et_h_par);
        P = (EditText) findViewById(R.id.et_p_canonica_par);
        k = (EditText) findViewById(R.id.et_k_canonica_par);
        A = (EditText) findViewById(R.id.et_a_general_par);
        B = (EditText) findViewById(R.id.et_b_general_par);
        C = (EditText) findViewById(R.id.et_c_general_par);
        D = (EditText) findViewById(R.id.et_d_general_par);
        xfoco = (EditText) findViewById(R.id.et_foco_x_par);
        yfoco = (EditText) findViewById(R.id.et_foco_y_par);
        xvertice = (EditText) findViewById(R.id.et_vertice_x_par);
        yvertice = (EditText) findViewById(R.id.et_vertice_y_par);
        directriz = (EditText) findViewById(R.id.et_directriz_par);

        tv_xyc_anonica = (TextView) findViewById(R.id.tv_y_canonica_par);
        flecha = (TextView) findViewById(R.id.tv_flecha_par);




    }

    public void cambiar_a_y() {
        Button caconica = findViewById(R.id.btn_xy_canonica_par);
        Button general = findViewById(R.id.btn_xy_general_par);
        Button btn_directriz = findViewById(R.id.btn_directriz_fdex_par);
        caconica.setText("y");
        h.setHint("k");
        tv_xyc_anonica.setText("x");
        k.setHint("h");

        general.setText("y");
        directriz.setHint("x");
        btn_directriz.setText("f(y)");
    }

    public void cambiar_a_x() {
        Button caconica = findViewById(R.id.btn_xy_canonica_par);
        Button general = findViewById(R.id.btn_xy_general_par);
        Button btn_directriz = findViewById(R.id.btn_directriz_fdex_par);
        caconica.setText("x");
        h.setHint("h");
        tv_xyc_anonica.setText("y");
        k.setHint("k");

        general.setText("x");
        directriz.setHint("y");
        btn_directriz.setText("f(x)");

    }

    public void cambiardx_y(View view) {


        if (estado == true && estado_anterior == false) {
            x_y = "y";

            estado_anterior = estado;
        } else {
            x_y = "x";

            estado = true;
            estado_anterior = false;
        }
        if (x_y.equals("y")) {

            cambiar_a_y();

        } else {

            cambiar_a_x();

        }


    }

    public void validar_calcular_par(View view) {
        Button graficamos = findViewById(R.id.button23);
        Button btn_y = findViewById(R.id.mas_menos_segundo_termino);
        Button btn_x = findViewById(R.id.mas_menos_binomio_caudrado);


        validar_par();// Clase para validar campos
        String hs = h.getText().toString();
        String Ps = P.getText().toString();
        String ks = k.getText().toString();
        String As = A.getText().toString();
        String Bs = B.getText().toString();
        String Cs = C.getText().toString();
        String Ds = D.getText().toString();
        String xfocos = xfoco.getText().toString();
        String yfocos = yfoco.getText().toString();
        String xvertices = xvertice.getText().toString();
        String yvertices = yvertice.getText().toString();
        String directrizs = directriz.getText().toString();


        if (ecuacon_canonica == 1) {

            if (x_y.equals("x")) {//si es f(x)
                final double p = Double.parseDouble(Ps);
                final double h;
                //asignar signo al binomio al cuadrado
                if (signo_binomio_cuadrado.equals("-")) {
                    h = Double.parseDouble(hs);
                } else {
                    double sub_h = Double.parseDouble(hs);
                    h = sub_h * -1;
                }

                //Asignar signo al segundo binomio de la ecuacion
               final double k;
                if (signo_binomio_segundo_termino.equals("-")) {
                    k = Double.parseDouble(ks);
                } else {
                    double sub_k = Double.parseDouble(ks);
                    k = sub_k * -1;
                }

                if (p < 0) {//parabola abajo
                    flecha.setText("↓");
                    asignar_signo_a_vertice_y_foco_cuando_arriba_o_abajo();

                    //valores de la ecuacion canonica
                    double p_despejado = p / 4;
                    double yfoco = k + p_despejado;
                    String yfocos_despejada = String.valueOf(yfoco);
                    this.yfoco.setHint(yfocos_despejada);

                    //valores de directriz
                    double directriz = k - p_despejado;
                    String directrizs_despejada = String.valueOf(directriz);
                    this.directriz.setHint(directrizs_despejada);

                    //obtenemocs ecuacion general

                    Paravola_Ecuacion_General mensajero = new Paravola_Ecuacion_General(h, k, p, x_y);
                    double bx;
                    double cy;
                    double d;
                    bx = mensajero.bx;
                    cy = mensajero.cy;
                    d = mensajero.d;

                    String bxs = String.valueOf(bx);
                    String cys = String.valueOf(cy);
                    String ds = String.valueOf(d);

                    B.setHint(bxs);
                    C.setHint(cys);
                    D.setHint(ds);

                    graficamos.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent i = new Intent(Paravola.this, Graficar_paravola.class);
                            i.putExtra("h",h);
                            i.putExtra("k",k);
                            i.putExtra("p",p);
                            i.putExtra("x_y",x_y);
                            startActivity(i);
                        }
                    });






                } else if (p > 0) {
                    flecha.setText("↑");
                    asignar_signo_a_vertice_y_foco_cuando_arriba_o_abajo();

                    //valores de la ecuación canonica
                    double p_despejado = p / 4;
                    double yfoco = k + p_despejado;
                    String yfocos_despejada = String.valueOf(yfoco);
                    this.yfoco.setHint(yfocos_despejada);

                    //valores de la directriz
                    double directriz = k - p_despejado;
                    String directrizs_despejada = String.valueOf(directriz);
                    this.directriz.setHint(directrizs_despejada);


                    //obtenemocs ecuacion general
                    Paravola_Ecuacion_General mensajero = new Paravola_Ecuacion_General(h, k, p, x_y);
                    double bx;
                    double cy;
                    double d;
                    bx = mensajero.bx;
                    cy = mensajero.cy;
                    d = mensajero.d;


                    String bxs = String.valueOf(bx);
                    String cys = String.valueOf(cy);
                    String ds = String.valueOf(d);

                    B.setHint(bxs);
                    C.setHint(cys);
                    D.setHint(ds);

                    graficamos.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent i = new Intent(Paravola.this, Graficar_paravola.class);
                            i.putExtra("h",h);
                            i.putExtra("k",k);
                            i.putExtra("p",p);
                            i.putExtra("x_y",x_y);
                            startActivity(i);
                        }
                    });


                } else {
                    Toast.makeText(this, "Verifica los valores", Toast.LENGTH_SHORT).show();
                }
            } else {//si es f(y)
                final double p = Double.parseDouble(Ps);

               final  double h;
                //asignar signo al binomio al cuadrado
                if (signo_binomio_segundo_termino.equals("-")) {
                    h = Double.parseDouble(ks);
                } else {

                    double sub_h = Double.parseDouble(ks);
                    h = sub_h * -1;
                }

                //Asignar signo al segundo binomio de la ecuacion
                final double k;
                if (signo_binomio_cuadrado.equals("-")) {
                    k = Double.parseDouble(hs);
                } else {

                    double sub_k = Double.parseDouble(hs);
                    k = sub_k * -1;
                }


                if (p < 0) {//parabola abajof
                    flecha.setText("←");
                    asignar_signo_a_vertice_y_foco_cuando_derecha_o_izqierda();

                    //valores de la ecuación canonica
                    double p_despejado = p / 4;
                    double xfoco = h + p_despejado;
                    String xfocos_despejada = String.valueOf(xfoco);
                    this.xfoco.setHint(xfocos_despejada);

                    //valores de la directriz
                    double directriz = h - p_despejado;
                    String directrizs_despejada = String.valueOf(directriz);
                    this.directriz.setHint(directrizs_despejada);


                    //obtenemocs ecuacion general
                    Paravola_Ecuacion_General mensajero = new Paravola_Ecuacion_General(h, k, p, x_y);
                    double bx;
                    double cy;
                    double d;
                    bx = mensajero.bx;
                    cy = mensajero.cy;
                    d = mensajero.d;

                    String bxs = String.valueOf(bx);
                    String cys = String.valueOf(cy);
                    String ds = String.valueOf(d);

                    B.setHint(bxs);
                    C.setHint(cys);
                    D.setHint(ds);

                    graficamos.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent i = new Intent(Paravola.this, Graficar_paravola.class);
                            i.putExtra("h",h);
                            i.putExtra("k",k);
                            i.putExtra("p",p);
                            i.putExtra("x_y",x_y);
                            startActivity(i);
                        }
                    });

                } else if (p > 0) {
                    flecha.setText("→");
                    asignar_signo_a_vertice_y_foco_cuando_derecha_o_izqierda();

                    //valores de la ecuación canonica
                    double p_despejado = p / 4;
                    double xfoco = h + p_despejado;
                    String xfocos_despejada = String.valueOf(xfoco);
                    this.xfoco.setHint(xfocos_despejada);

                    //valores de la directriz
                    double directriz = h - p_despejado;
                    String directrizs_despejada = String.valueOf(directriz);
                    this.directriz.setHint(directrizs_despejada);


                    //obtenemocs ecuacion general
                    Paravola_Ecuacion_General mensajero = new Paravola_Ecuacion_General(h, k, p, x_y);
                    double bx;
                    double cy;
                    double d;
                    bx = mensajero.bx;
                    cy = mensajero.cy;
                    d = mensajero.d;


                    String bxs = String.valueOf(bx);
                    String cys = String.valueOf(cy);
                    String ds = String.valueOf(d);

                    B.setHint(bxs);
                    C.setHint(cys);
                    D.setHint(ds);

                    graficamos.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent i = new Intent(Paravola.this, Graficar_paravola.class);
                            i.putExtra("h",h);
                            i.putExtra("k",k);
                            i.putExtra("p",p);
                            i.putExtra("x_y",x_y);
                            startActivity(i);
                        }
                    });
                } else {
                    Toast.makeText(this, "Verifica los valores", Toast.LENGTH_SHORT).show();
                }

            }


        }

        else if (vertice_foco == 1) {
            double xvertice = Integer.parseInt(xvertices);
            double yvertice = Integer.parseInt(yvertices);
            double xfoco = Integer.parseInt(xfocos);
            double yfoco = Integer.parseInt(yfocos);
            if (yvertice < yfoco && xfoco == xvertice) {
                x_y = "x";
            } else if (yvertice > yfoco && xfoco == xvertice) {//abre abajo
                x_y = "x";
            } else if (xvertice < xfoco && yvertice == yfoco) {//abre derecha
                x_y = "y";
            } else if (xvertice > xfoco && yvertice == yfoco) {//abre izquierda
                x_y = "y";
            }


            String bxs, cys, ds, flecha, btnx, btny, et_ps, directriz;
            Resolver_foco_directriz_vertice_parabola mensajero = new Resolver_foco_directriz_vertice_parabola(xvertice, yvertice, xfoco, yfoco, x_y);
            mensajero.Resolver();
            et_ps = mensajero.et_ps;
            bxs = mensajero.bxs;
            cys = mensajero.cys;
            ds = mensajero.ds;
            flecha = mensajero.flecha;
            btnx = mensajero.btn_x;
            btny = mensajero.btn_y;
            xvertices = mensajero.xvertices;
            yvertices = mensajero.yvertices;

            //graficamos
            double subh = Double.parseDouble(xvertices);
            final double h = Math.abs(subh);
            double subk = Double.parseDouble(yvertices);
            final double k = Math.abs(subk);
            final double p = Double.parseDouble(et_ps);
            graficamos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(Paravola.this, Graficar_paravola.class);
                    i.putExtra("h",h);
                    i.putExtra("k",k);
                    i.putExtra("p",p);
                    i.putExtra("x_y",x_y);
                    startActivity(i);
                }
            });
            //////////////////////////////////////


            P.setHint(et_ps);
            B.setHint(bxs);
            C.setHint(cys);
            D.setHint(ds);
            this.flecha.setText(flecha);

            //
            btn_x.setText(btnx);
            btn_y.setText(btny);
            if (x_y.equals("x")) {
                cambiar_a_x();
                this.h.setHint(xvertices);
                this.k.setHint(yvertices);
            } else {
                cambiar_a_y();
                this.k.setHint(xvertices);
                this.h.setHint(yvertices);
            }
            directriz = mensajero.directrizs;
            this.directriz.setHint(directriz);


        }

        else if (foco_directriz == 1) {
            double xfoco = Double.parseDouble(xfocos);
            double yfoco = Double.parseDouble(yfocos);
            double directriz = Double.parseDouble(directrizs);
            double xvertice;
            double yvertice;

            if (x_y.equals("x")) {
                yvertice = (yfoco + directriz) / 2;
                xvertice = xfoco;

            } else {
                xvertice = (xfoco + directriz) / 2;
                yvertice = yfoco;
            }

            xvertices = String.valueOf(xvertice);
            yvertices = String.valueOf(yvertice);

            this.xvertice.setHint(xvertices);
            this.yvertice.setHint(yvertices);


            String bxs, cys, ds, flecha, btnx, btny, et_ps;
            Resolver_foco_directriz_vertice_parabola mensajero = new Resolver_foco_directriz_vertice_parabola(xvertice, yvertice, xfoco, yfoco, x_y);
            mensajero.Resolver();
            et_ps = mensajero.et_ps;
            bxs = mensajero.bxs;
            cys = mensajero.cys;
            ds = mensajero.ds;
            flecha = mensajero.flecha;
            btnx = mensajero.btn_x;
            btny = mensajero.btn_y;

            P.setHint(et_ps);
            B.setHint(bxs);
            C.setHint(cys);
            D.setHint(ds);
            this.flecha.setText(flecha);
            x_y = mensajero.x_y;
            //
            btn_x.setText(btnx);
            btn_y.setText(btny);

            final double subxvertice = Math.abs(xvertice);
            final double subyvertice = Math.abs(yvertice);

            yvertice = subyvertice;
            xvertice = subxvertice;

            xvertices = String.valueOf(xvertice);
            yvertices = String.valueOf(yvertice);


            if (x_y.equals("x")) {

                h.setHint(xvertices);
                k.setHint(yvertices);

            } else {
                k.setHint(xvertices);
                h.setHint(yvertices);
            }

            //graficamos
            final double p = Double.parseDouble(et_ps);
            graficamos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(Paravola.this, Graficar_paravola.class);
                    i.putExtra("h",subxvertice);
                    i.putExtra("k",subyvertice);
                    i.putExtra("p",p);
                    i.putExtra("x_y",x_y);
                    startActivity(i);
                }
            });


        }

        else if (vertice_directriz == 1) {
            double xvertice = Double.parseDouble(xvertices);
            double yvertice = Double.parseDouble(yvertices);
            double directriz = Double.parseDouble(directrizs);

            double xfoco;
            double yfoco;

            if (x_y.equals("x")) {
                yfoco = (2 * yvertice) - directriz;
                xfoco = xvertice;
            } else {
                xfoco = (2 * xvertice) - directriz;
                yfoco = yvertice;
            }

            xfocos = String.valueOf(xfoco);
            yfocos = String.valueOf(yfoco);

            this.xfoco.setHint(xfocos);
            this.yfoco.setHint(yfocos);


            String et_ps, bxs, cys, ds, flecha, btnx, btny;
            Resolver_foco_directriz_vertice_parabola mensajero = new Resolver_foco_directriz_vertice_parabola(xvertice, yvertice, xfoco, yfoco, x_y);
            mensajero.Resolver();
            et_ps = mensajero.et_ps;
            xvertices = mensajero.xvertices;
            yvertices = mensajero.yvertices;
            bxs = mensajero.bxs;
            cys = mensajero.cys;
            ds = mensajero.ds;
            flecha = mensajero.flecha;
            btnx = mensajero.btn_x;
            btny = mensajero.btn_y;

            P.setHint(et_ps);
            B.setHint(bxs);
            C.setHint(cys);
            D.setHint(ds);
            this.flecha.setText(flecha);
            x_y = mensajero.x_y;
            //
            btn_x.setText(btnx);
            btn_y.setText(btny);
            if (x_y.equals("x")) {
                h.setHint(xvertices);
                k.setHint(yvertices);
            } else {
                k.setHint(xvertices);
                h.setHint(yvertices);
            }

            //graficamos

            double subh = Double.parseDouble(xvertices);
            final double h = Math.abs(subh);
            double subk = Double.parseDouble(yvertices);
            final double k = Math.abs(subk);
            final double p = Double.parseDouble(et_ps);
            graficamos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(Paravola.this, Graficar_paravola.class);
                    i.putExtra("h",h);
                    i.putExtra("k",k);
                    i.putExtra("p",p);
                    i.putExtra("x_y",x_y);
                    startActivity(i);
                }
            });

        }

        else if (ecuacion_general == 1) {

             double h,k;
            final double p;
            String signocuadrado, signosegundo;
            if (x_y.equals("x")) {//si el termino cuadratico es x
                Ecuacion_general_paravola mensajero = new Ecuacion_general_paravola(Bs, Cs, Ds);
                mensajero.valida_calcular_ecg();
                this.h.setHint(mensajero.h_k_cuadrado);
                this.k.setHint(mensajero.k_h_segundo);
                P.setHint(mensajero.p);
                btn_x.setText(mensajero.btn_cuadrado);
                btn_y.setText(mensajero.btn_segundo);

                h = Double.parseDouble(mensajero.h_k_cuadrado);
                p = Double.parseDouble(mensajero.p);
                k = Double.parseDouble(mensajero.k_h_segundo);
                signocuadrado = mensajero.btn_cuadrado;
                signosegundo = mensajero.btn_segundo;

            } else {//si el termino cuadratico es y
                Ecuacion_general_paravola mensajero = new Ecuacion_general_paravola(Cs, Bs, Ds);
                mensajero.valida_calcular_ecg();
                this.h.setHint(mensajero.h_k_cuadrado);
                this.k.setHint(mensajero.k_h_segundo);
                P.setHint(mensajero.p);
                btn_x.setText(mensajero.btn_cuadrado);
                btn_y.setText(mensajero.btn_segundo);


                h = Double.parseDouble(mensajero.h_k_cuadrado);
                p = Double.parseDouble(mensajero.p);
                k = Double.parseDouble(mensajero.k_h_segundo);
                signocuadrado = mensajero.btn_cuadrado;
                signosegundo = mensajero.btn_segundo;
            }





            Ecuacion_canonica mensajero_ec = new Ecuacion_canonica(x_y,h,p,k,signocuadrado,signosegundo);
            mensajero_ec.calcular();
            flecha.setText(mensajero_ec.flecha);
            xfoco.setHint(mensajero_ec.xfoco);
            yfoco.setHint(mensajero_ec.yfoco);
            directriz.setHint(mensajero_ec.directriz);



            if (signocuadrado.equals("+") && x_y.equals("x")) {
                h = h * -1;
            }else if (signocuadrado.equals("+") && x_y.equals("y")){
                h = h * -1;
            }
            if (signosegundo.equals("+") && x_y.equals("x")) {
                k=k * -1;
            }else if (signosegundo.equals("+") && x_y.equals("y")){
                k = k * -1;
            }
            if (x_y.equals("x")) {
                final String et_x_v = String.valueOf(h);
                final String et_y_v = String.valueOf(k);
                xvertice.setHint(et_x_v);
                yvertice.setHint(et_y_v);


                graficamos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final double hx = Double.parseDouble(et_x_v);
                        final double ky = Double.parseDouble(et_y_v);

                        Intent i = new Intent(Paravola.this, Graficar_paravola.class);
                        i.putExtra("h",hx);
                        i.putExtra("k",ky);
                        i.putExtra("p",p);
                        i.putExtra("x_y",x_y);
                        startActivity(i);
                    }
                });
            }else{
                String et_x_v = String.valueOf(k);
                String et_y_v = String.valueOf(h);
                xvertice.setHint(et_x_v);
                yvertice.setHint(et_y_v);

                final double hx = Double.parseDouble(et_x_v);
                final double ky = Double.parseDouble(et_y_v);

                graficamos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent i = new Intent(Paravola.this, Graficar_paravola.class);
                        i.putExtra("h",hx);
                        i.putExtra("k",ky);
                        i.putExtra("p",p);
                        i.putExtra("x_y",x_y);
                        startActivity(i);
                    }
                });

            }


        }

        else if (ecuacion_general_conA == 1) {
            double a = Double.parseDouble(As);
            double subb = Double.parseDouble(Bs);
            double subc = Double.parseDouble(Cs);
            double subd = Double.parseDouble(Ds);

            double b = subb/a;
            double c = subc/a;
            double d = subd/a;

            String bs = String.valueOf(b);
            String cs = String.valueOf(c);
            String ds = String.valueOf(d);






            double h,k;
            final double p;
            String signocuadrado, signosegundo;
            if (x_y.equals("x")) {//si el termino cuadratico es x
                Ecuacion_general_paravola mensajero = new Ecuacion_general_paravola(bs, cs, ds);
                mensajero.valida_calcular_ecg();
                this.h.setHint(mensajero.h_k_cuadrado);
                this.k.setHint(mensajero.k_h_segundo);
                P.setHint(mensajero.p);
                btn_x.setText(mensajero.btn_cuadrado);
                btn_y.setText(mensajero.btn_segundo);

                h = Double.parseDouble(mensajero.h_k_cuadrado);
                p = Double.parseDouble(mensajero.p);
                k = Double.parseDouble(mensajero.k_h_segundo);
                signocuadrado = mensajero.btn_cuadrado;
                signosegundo = mensajero.btn_segundo;

            } else {//si el termino cuadratico es y
                Ecuacion_general_paravola mensajero = new Ecuacion_general_paravola(cs, bs, ds);
                mensajero.valida_calcular_ecg();
                this.h.setHint(mensajero.h_k_cuadrado);
                this.k.setHint(mensajero.k_h_segundo);
                P.setHint(mensajero.p);
                btn_x.setText(mensajero.btn_cuadrado);
                btn_y.setText(mensajero.btn_segundo);


                h = Double.parseDouble(mensajero.h_k_cuadrado);
                p = Double.parseDouble(mensajero.p);
                k = Double.parseDouble(mensajero.k_h_segundo);
                signocuadrado = mensajero.btn_cuadrado;
                signosegundo = mensajero.btn_segundo;
            }





            Ecuacion_canonica mensajero_ec = new Ecuacion_canonica(x_y,h,p,k,signocuadrado,signosegundo);
            mensajero_ec.calcular();
            flecha.setText(mensajero_ec.flecha);
            xfoco.setHint(mensajero_ec.xfoco);
            yfoco.setHint(mensajero_ec.yfoco);
            directriz.setHint(mensajero_ec.directriz);



            if (signocuadrado.equals("+") && x_y.equals("x")) {
                h = h * -1;
            }else if (signocuadrado.equals("+") && x_y.equals("y")){
                h = h * -1;
            }
            if (signosegundo.equals("+") && x_y.equals("x")) {
                k=k * -1;
            }else if (signosegundo.equals("+") && x_y.equals("y")){
                k = k * -1;
            }
            if (x_y.equals("x")) {
                final String et_x_v = String.valueOf(h);
                final String et_y_v = String.valueOf(k);
                xvertice.setHint(et_x_v);
                yvertice.setHint(et_y_v);


                graficamos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final double hx = Double.parseDouble(et_x_v);
                        final double ky = Double.parseDouble(et_y_v);

                        Intent i = new Intent(Paravola.this, Graficar_paravola.class);
                        i.putExtra("h",hx);
                        i.putExtra("k",ky);
                        i.putExtra("p",p);
                        i.putExtra("x_y",x_y);
                        startActivity(i);
                    }
                });
            }else{
                String et_x_v = String.valueOf(k);
                String et_y_v = String.valueOf(h);
                xvertice.setHint(et_x_v);
                yvertice.setHint(et_y_v);

                final double hx = Double.parseDouble(et_x_v);
                final double ky = Double.parseDouble(et_y_v);

                graficamos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent i = new Intent(Paravola.this, Graficar_paravola.class);
                        i.putExtra("h",hx);
                        i.putExtra("k",ky);
                        i.putExtra("p",p);
                        i.putExtra("x_y",x_y);
                        startActivity(i);
                    }
                });

            }




        }


    }


    private void validar_par() {
        String hs = h.getText().toString();
        String Ps = P.getText().toString();
        String ks = k.getText().toString();
        String As = A.getText().toString();
        String Bs = B.getText().toString();
        String Cs = C.getText().toString();
        String Ds = D.getText().toString();
        String xfocos = xfoco.getText().toString();
        String yfocos = yfoco.getText().toString();
        String xvertices = xvertice.getText().toString();
        String yvertices = yvertice.getText().toString();
        String directrizs = directriz.getText().toString();

        if (h.length() != 0 && P.length() != 0 && k.length() != 0 && As.equals("") && Bs.equals("") && Cs.equals("") && Ds.equals("") && xfocos.equals("") && yfocos.equals("") && xvertices.equals("") && yvertices.equals("") && directrizs.equals("")) {//validamos la ecuacion canonica

            ecuacon_canonica = 1;
            vertice_foco = 0;
            foco_directriz = 0;
            vertice_directriz = 0;
            ecuacion_general = 0;
            ecuacion_general_conA = 0;

        } else if (hs.equals("") && Ps.equals("") && ks.equals("") && As.equals("") && Bs.equals("") && Cs.equals("") && Ds.equals("") && xfoco.length() != 0 && yfoco.length() != 0 && xvertice.length() != 0 && yvertice.length() != 0 && directrizs.equals("")) {//Cuanco vertice foco
            double xvertice = Double.parseDouble(xvertices);
            double yvertice = Double.parseDouble(yvertices);
            double xfoco = Double.parseDouble(xfocos);
            double yfoco = Double.parseDouble(yfocos);
            if (xvertice == xfoco || yvertice == yfoco) {
                ecuacon_canonica = 0;
                vertice_foco = 1;
                foco_directriz = 0;
                vertice_directriz = 0;
                ecuacion_general = 0;
                ecuacion_general_conA = 0;
            } else {
                Toast.makeText(this, "Verifica los valores", Toast.LENGTH_SHORT).show();
            }
        } else if (hs.equals("") && Ps.equals("") && ks.equals("") && As.equals("") && Bs.equals("") && Cs.equals("") && Ds.equals("") && xfoco.length() != 0 && yfoco.length() != 0 && xvertices.equals("") && yvertices.equals("") && directriz.length() != 0) {//cuando foco y directriz
            double xfoco = Double.parseDouble(xfocos);
            double yfoco = Double.parseDouble(yfocos);
            double directriz = Double.parseDouble(directrizs);

            ecuacon_canonica = 0;
            vertice_foco = 0;
            foco_directriz = 1;
            vertice_directriz = 0;
            ecuacion_general = 0;
            ecuacion_general_conA = 0;


        } else if (hs.equals("") && Ps.equals("") && ks.equals("") && As.equals("") && Bs.equals("") && Cs.equals("") && Ds.equals("") && xfocos.equals("") && yfocos.equals("") && xvertice.length() != 0 && yvertice.length() != 0 && directriz.length() != 0) {//cuando vertice y directriz
            double xvertice = Double.parseDouble(xvertices);
            double yvertice = Double.parseDouble(yvertices);
            double directriz = Double.parseDouble(directrizs);

            ecuacon_canonica = 0;
            vertice_foco = 0;
            foco_directriz = 0;
            vertice_directriz = 1;
            ecuacion_general = 0;
            ecuacion_general_conA = 0;


        } else if (hs.equals("") && Ps.equals("") && ks.equals("") && A.length() != 0 && B.length() != 0 && C.length() != 0 && D.length() != 0 && xfocos.equals("") && yfocos.equals("") && xvertices.equals("") && yvertices.equals("") && directrizs.equals("")) {//cuando ecuacion general
            ecuacon_canonica = 0;
            vertice_foco = 0;
            foco_directriz = 0;
            vertice_directriz = 0;
            ecuacion_general = 0;
            ecuacion_general_conA = 1;
        } else if (hs.equals("") && Ps.equals("") && ks.equals("") && As.equals("") && B.length() != 0 && C.length() != 0 && D.length() != 0 && xfocos.equals("") && yfocos.equals("") && xvertices.equals("") && yvertices.equals("") && directrizs.equals("")) {//ecuacuon sin a
            ecuacon_canonica = 0;
            vertice_foco = 0;
            foco_directriz = 0;
            vertice_directriz = 0;
            ecuacion_general = 1;
            ecuacion_general_conA = 0;
        } else {
            Toast.makeText(this, "Verifica los valores", Toast.LENGTH_SHORT).show();
        }

    }

    public void borrar_par(View view) {
        if (x_y.equals("x")) {
            h.setHint("h");
            P.setHint("P");
            k.setHint("K");
            A.setHint("");
            B.setHint("B");
            C.setHint("C");
            D.setHint("D");
            xfoco.setHint("x");
            yfoco.setHint("y");
            xvertice.setHint("x");
            yvertice.setHint("y");
            directriz.setHint("y");

            borrar();

            tv_xyc_anonica.setText("y");
            flecha.setText("");
            x_y = "x";
            cambiar_a_x();

        } else {
            h.setHint("k");
            P.setHint("P");
            k.setHint("h");
            A.setHint("");
            B.setHint("B");
            C.setHint("C");
            D.setHint("D");
            xfoco.setHint("x");
            yfoco.setHint("y");
            xvertice.setHint("x");
            yvertice.setHint("y");
            directriz.setHint("x");

            tv_xyc_anonica.setText("x");
            flecha.setText("");
            borrar();
            x_y = "y";
            cambiar_a_y();


        }
    }

    private void borrar() {
        h.setText("");
        P.setText("");
        k.setText("");
        A.setText("");
        B.setText("");
        C.setText("");
        D.setText("");
        xfoco.setText("");
        yfoco.setText("");
        xvertice.setText("");
        yvertice.setText("");
        directriz.setText("");
        Button btn_x = findViewById(R.id.mas_menos_binomio_caudrado);
        Button btn_y = findViewById(R.id.mas_menos_segundo_termino);
        signo_binomio_cuadrado = "-";
        signo_binomio_segundo_termino = "-";
        btn_x.setText(signo_binomio_cuadrado);
        btn_y.setText(signo_binomio_segundo_termino);
    }


    //metodos para cambiar el signo

    public void cambiamos_signo() {

        if (estado_signo == false) {

            signo = "+";
            estado_signo = true;
        } else {

            signo = "-";
            estado_signo = false;

        }
    }

    public void cambiar_signo_binomio_cuadrado(View view) {
        Button btn_x = findViewById(R.id.mas_menos_binomio_caudrado);
        cambiamos_signo();

        signo_binomio_cuadrado = signo;
        btn_x.setText(signo_binomio_cuadrado);


    }

    public void cambiar_signo_segundo_termino(View view) {
        Button btn_y = findViewById(R.id.mas_menos_segundo_termino);
        cambiamos_signo();
        signo_binomio_segundo_termino = signo;
        btn_y.setText(signo_binomio_segundo_termino);


    }

    private void asignar_signo_a_vertice_y_foco_cuando_arriba_o_abajo() {
        String hs = h.getText().toString();
        String ks = k.getText().toString();
        if (signo_binomio_cuadrado.equals("-")) {
            xvertice.setHint(hs);
        } else {

            xvertice.setHint("-" + hs);
        }

        //valores segun el signo para el segundo termino
        if (signo_binomio_segundo_termino.equals("-")) {
            yvertice.setHint(ks);
        } else {
            yvertice.setHint("-" + ks);
        }

        if (signo_binomio_cuadrado.equals("-")) {
            xfoco.setHint(hs);
        } else {
            xfoco.setHint("-" + hs);
        }
    }

    private void asignar_signo_a_vertice_y_foco_cuando_derecha_o_izqierda() {
        String hs = h.getText().toString();
        String ks = k.getText().toString();
        if (signo_binomio_segundo_termino.equals("-")) {

            xvertice.setHint(ks);//ponemos en x vertice, el actual valor de la casilla ks, que tomo el valor de hs

        } else {

            xvertice.setHint("-" + ks);
        }

        //valores segun el signo para el segundo termino
        if (signo_binomio_cuadrado.equals("-")) {
            yvertice.setHint(hs);
        } else {
            yvertice.setHint("-" + hs);
        }

        if (signo_binomio_cuadrado.equals("-")) {
            yfoco.setHint(hs);
        } else {
            yfoco.setHint("-" + hs);
        }

    }




    public static class Paravola_Ecuacion_General {
        private double xverticeH, yverticek, P;
        double bx, cy, d;
        private String x_y;

        private Paravola_Ecuacion_General(double h,
                                          double k,
                                          double p,
                                          String x_y) {//constructor
            this.xverticeH = h;
            this.yverticek = k;
            this.P = p;
            this.x_y = x_y;

            if (x_y.equals("x")) {
                cuando_x();
            } else if (x_y.equals("y")) {
                cuando_y();
            }


        }

        private void cuando_x() {
            bx = 2 * (xverticeH * -1);
            double sub1_d = (P * (yverticek * -1)) * -1;//multiplicamos p por k y lo cambiamso de lado de la ecuacion
            double sub2_d = xverticeH * xverticeH;//elevamos h al cuadrado
            cy = P * -1;// cambiamso a cy de lado de la ecuacion
            d = sub1_d + sub2_d;

        }

        private void cuando_y() {
            cy = 2 * (yverticek * -1);
            double sub1_d = (P * (xverticeH * -1)) * -1;//multiplicamos p por k y lo cambiamso de lado de la ecuacion
            double sub2_d = yverticek * yverticek;//elevamos h al cuadrado
            bx = P * -1;// cambiamso a cy de lado de la ecuacion
            d = sub1_d + sub2_d;

        }
    }//clase para rsolver la ecuacion general, cuadno conoces los valors h, k, p

    public static class Resolver_foco_directriz_vertice_parabola {

        private double xvertice, yvertice, xfoco, yfoco;

        private String et_ps, xvertices, yvertices, bxs, cys, ds, flecha, btn_x, btn_y, x_y, directrizs;
        Paravola mensajero_par = new Paravola();

        private Resolver_foco_directriz_vertice_parabola(double xvertice,
                                                         double yvertice,
                                                         double xfoco,
                                                         double yfoco,
                                                         String x_y) {
            this.xvertice = xvertice;
            this.yvertice = yvertice;
            this.xfoco = xfoco;
            this.yfoco = yfoco;
            this.x_y = x_y;
        }

        private void Resolver() {

            if (yvertice < yfoco && xfoco == xvertice) {//si arriba
                flecha = "↑";
                //mensajero_par.cambiar_a_x();
                x_y = "x";

                //valores de la ecuacion canonica
                double p = yfoco - yvertice;//
                double et_p = 4 * p;
                et_ps = String.valueOf(et_p);

                if (xvertice < 0) {
                    double sub_xvertice = Math.abs(xvertice);
                    xvertices = String.valueOf(sub_xvertice);
                    mensajero_par.cambiamos_signo();
                    btn_x = "+";
                } else {
                    btn_x = "-";
                    xvertices = String.valueOf(xvertice);
                }

                if (yvertice < 0) {
                    double sub_yvertice = Math.abs(yvertice);
                    yvertices = String.valueOf(sub_yvertice);
                    mensajero_par.cambiamos_signo();
                    btn_y = "+";

                } else {
                    btn_y = "-";
                    yvertices = String.valueOf(yvertice);
                }


                //valores de ecuacioon general
                Paravola_Ecuacion_General mensajero = new Paravola_Ecuacion_General(xfoco, yvertice, et_p, x_y);
                double bx;
                double cy;
                double d;
                bx = mensajero.bx;
                cy = mensajero.cy;
                d = mensajero.d;


                bxs = String.valueOf(bx);
                cys = String.valueOf(cy);
                ds = String.valueOf(d);

                double directriz = yvertice - p;//valor de p
                directrizs = String.valueOf(directriz);


            } else if (yvertice > yfoco && xfoco == xvertice) {//abre abajo
                flecha = "↓";
                x_y = "x";
                //mensajero_par.cambiar_a_x();


                //valores de la ecuacion canonica
                double p = yfoco - yvertice;//calculamos el vaor de p
                double et_p = 4 * p;
                et_ps = String.valueOf(et_p);

                if (xvertice < 0) {
                    double sub_xvertice = Math.abs(xvertice);
                    xvertices = String.valueOf(sub_xvertice);
                    mensajero_par.cambiamos_signo();
                    btn_x = "+";
                } else {
                    btn_x = "-";
                    xvertices = String.valueOf(xvertice);
                }

                if (yvertice < 0) {
                    double sub_yvertice = Math.abs(yvertice);
                    yvertices = String.valueOf(sub_yvertice);
                    mensajero_par.cambiamos_signo();
                    btn_y = "+";

                } else {
                    btn_y = "-";
                    yvertices = String.valueOf(yvertice);
                }


                //valores de ecuacioon general
                Paravola_Ecuacion_General mensajero = new Paravola_Ecuacion_General(xvertice, yvertice, et_p, x_y);
                double bx;
                double cy;
                double d;
                bx = mensajero.bx;
                cy = mensajero.cy;
                d = mensajero.d;

                bxs = String.valueOf(bx);
                cys = String.valueOf(cy);
                ds = String.valueOf(d);

                double directriz = yvertice - p;
                directrizs = String.valueOf(directriz);


            } else if (xvertice > xfoco && yvertice == yfoco) {//abre izquierda
                flecha = "←";
                // mensajero_par.cambiar_a_y();
                x_y = "y";


                //valores de ecuacion canonica
                double p = xfoco - xvertice;//calculamos el vaor de p
                double et_p = 4 * p;
                et_ps = String.valueOf(et_p);


                if (xvertice < 0) {
                    double sub_xvertice = Math.abs(xvertice);
                    xvertices = String.valueOf(sub_xvertice);
                    mensajero_par.cambiamos_signo();
                    btn_y = "+";
                } else {
                    btn_y = "-";
                    xvertices = String.valueOf(xvertice);
                }

                if (yvertice < 0) {
                    double sub_yvertice = Math.abs(yvertice);
                    yvertices = String.valueOf(sub_yvertice);
                    mensajero_par.cambiamos_signo();
                    btn_x = "+";
                } else {
                    btn_x = "-";
                    yvertices = String.valueOf(yvertice);
                }


                //valores de ecuacioon general
                Paravola_Ecuacion_General mensajero = new Paravola_Ecuacion_General(xvertice, yvertice, et_p, x_y);
                double bx;
                double cy;
                double d;
                bx = mensajero.bx;
                cy = mensajero.cy;
                d = mensajero.d;


                bxs = String.valueOf(bx);
                cys = String.valueOf(cy);
                ds = String.valueOf(d);

                double directriz = xvertice - p;
                directrizs = String.valueOf(directriz);


            } else if (xvertice < xfoco && yvertice == yfoco) {//abre derecha
                flecha = "→";
                //mensajero_par.cambiar_a_y();
                x_y = "y";

                //valores de la ecuacin canonica
                double p = xfoco - xvertice;//calculamos el vaor de p
                double et_p = 4 * p;
                et_ps = String.valueOf(et_p);

                if (xvertice < 0) {
                    double sub_xvertice = Math.abs(xvertice);
                    xvertices = String.valueOf(sub_xvertice);
                    mensajero_par.cambiamos_signo();
                    btn_y = "+";
                } else {
                    btn_y = "-";
                    xvertices = String.valueOf(xvertice);
                }

                if (yvertice < 0) {
                    double sub_yvertice = Math.abs(yvertice);
                    yvertices = String.valueOf(sub_yvertice);
                    mensajero_par.cambiamos_signo();
                    btn_x = ("+");
                } else {
                    btn_x = "-";
                    yvertices = String.valueOf(yvertice);
                }


                //valores de ecuacioon general
                Paravola_Ecuacion_General mensajero = new Paravola_Ecuacion_General(xvertice, yvertice, et_p, x_y);
                double bx;
                double cy;
                double d;
                bx = mensajero.bx;
                cy = mensajero.cy;
                d = mensajero.d;


                bxs = String.valueOf(bx);
                cys = String.valueOf(cy);
                ds = String.valueOf(d);

                double directriz = xvertice - p;
                directrizs = String.valueOf(directriz);


            }
        }
    }// resolver datos conciedo otros datos, pero sin saber la ecuaacion

    private static class Ecuacion_general_paravola {
        private String btn_cuadrado, btn_segundo, h_k_cuadrado, k_h_segundo, p, flecha;
        private String bs, cs, ds;
        // private double a,b,c,d;

        private Ecuacion_general_paravola(String B,
                                          String C,
                                          String D) {

            this.bs = B;
            this.cs = C;
            this.ds = D;


        }

        private void valida_calcular_ecg() {


            Double b = Double.parseDouble(bs);
            Double c = Double.parseDouble(cs);
            Double d = Double.parseDouble(ds);
            //separar terminos a su lado de la ecuacion, las x se quedan igual y las y pasan al lado derecho
            double c_cambiado = c * -1;
            double d_cambiado = d * -1;
            //Completar el TCP
            double h = b / 2;
            double sumamos_lado_opuesto_de_ecuacion = (h * h);
            //sumeamos terminos independientes
            double sub_k = d_cambiado + sumamos_lado_opuesto_de_ecuacion;
            double k = sub_k / c_cambiado;

            //comprovacion de los signos
            double comprovacion_signos = c * d;

            if (b < 0 && comprovacion_signos < 0) {//ambos signos son menso

                btn_cuadrado = "-";
                btn_segundo = "-";
                double et_h = Math.abs(h);
                double et_k = Math.abs(k);
                h_k_cuadrado = String.valueOf(et_h);
                k_h_segundo = String.valueOf(et_k);
                p = String.valueOf(c_cambiado);

            } else if (b > 0 && comprovacion_signos < 0) {//mas menos

                btn_cuadrado = "+";
                btn_segundo = "-";
                double et_h = Math.abs(h);
                double et_k = Math.abs(k);
                h_k_cuadrado = String.valueOf(et_h);
                k_h_segundo = String.valueOf(et_k);
                p = String.valueOf(c_cambiado);

            } else if (b < 0 && comprovacion_signos > 0) {//menos mas

                btn_cuadrado = "-";
                btn_segundo = "+";
                double et_h = Math.abs(h);
                double et_k = Math.abs(k);
                h_k_cuadrado = String.valueOf(et_h);
                k_h_segundo = String.valueOf(et_k);
                p = String.valueOf(c_cambiado);

            } else if (b > 0 && comprovacion_signos > 0) {//mas mas

                btn_cuadrado = "+";
                btn_segundo = "+";
                double et_h = Math.abs(h);
                double et_k = Math.abs(k);
                h_k_cuadrado = String.valueOf(et_h);
                k_h_segundo = String.valueOf(et_k);
                p = String.valueOf(c_cambiado);

            }
        }
    }//clase para resolver la ecucion canonica, conociendo la ecuacion generla

    private static class Ecuacion_canonica {//resolver dats de la ecuacion conociendo la ecuacion canonica
        private String flecha, xfoco, yfoco, directriz, b, c, d;//valores de salida
        private String x_y, signo_cuadrado, signo_segundo;//valores de entrada
        private double h, p, k;//valores de entrada

        private Ecuacion_canonica(String x_y,
                                  double h,
                                  double p,
                                  double k,
                                  String signo_cuadrado,
                                  String signo_segundo) {

            this.x_y = x_y;
            this.h = h;
            this.p = p;
            this.k = k;
            this.signo_cuadrado = signo_cuadrado;
            this.signo_segundo = signo_segundo;

        }



        private void calcular() {
            if (x_y.equals("x")) {//si es f(x)

                //asignar signo al binomio al cuadrado
                if (signo_cuadrado.equals("+")) {
                    h = h * -1;
                }
                if (signo_segundo.equals("+")) {
                    k = k * -1;
                }


                if (p < 0) {//parabola abajo
                    flecha = ("↓");


                    //valores de la ecuacion canonica
                    double p_despejado = p / 4;
                    double yfoco = k + p_despejado;
                    this.yfoco = String.valueOf(yfoco);
                    this.xfoco = String.valueOf(h);

                    //valores de directriz
                    double directriz = k - p_despejado;
                    this.directriz = String.valueOf(directriz);




                } else if (p > 0) {
                    flecha = ("↑");


                    //valores de la ecuación canonica
                    double p_despejado = p / 4;
                    double yfoco = k + p_despejado;
                    this.yfoco = String.valueOf(yfoco);
                    this.xfoco = String.valueOf(h);

                    //valores de la directriz
                    double directriz = k - p_despejado;
                    this.directriz = String.valueOf(directriz);
                }
            } else {//si es f(y)
                //asignar signo al binomio al cuadrado
                double subh, subk;
                if (signo_segundo.equals("-")) {
                    subh = k;
                } else {
                    subh = k * -1;
                }
                if (signo_cuadrado.equals("-")) {
                    subk = h;
                } else {
                    subk = h * -1;
                }


                if (p < 0) {//parabola abajof
                    flecha = ("←");


                    //valores de la ecuación canonica
                    double p_despejado = p / 4;
                    double xfoco = subh + p_despejado;
                    this.xfoco = String.valueOf(xfoco);
                    this.yfoco = String.valueOf(subk);

                    //valores de la directriz
                    double directriz = subh - p_despejado;
                    this.directriz = String.valueOf(directriz);


                } else if (p > 0) {
                    flecha = ("→");


                    //valores de la ecuación canonica
                    double p_despejado = p / 4;
                    double xfoco = subh + p_despejado;
                    this.xfoco = String.valueOf(xfoco);
                    this.yfoco = String.valueOf(subk);

                    //valores de la directriz
                    double directriz = subh - p_despejado;
                    this.directriz = String.valueOf(directriz);


                }
            }

        }
    }


}

