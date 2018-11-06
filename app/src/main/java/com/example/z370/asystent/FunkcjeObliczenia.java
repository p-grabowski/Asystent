package com.example.z370.asystent;


import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.tan;
import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;

public class FunkcjeObliczenia {

    public static class Punkt{
        public String Nazwa;
        public double X=0;
        public double Y=0;
        public double H=0;
            }

            public static class PunktyLista{

                public List<Punkt> ListaPunktow;



            }

    public static String zaokraglij(double a, int x){ //zaokrąglanie liczby a do x miejsc po przecinku

        java.text.DecimalFormat df = new java.text.DecimalFormat();
        df.setMaximumFractionDigits(x);
        df.setMinimumFractionDigits(2);

        return df.format(a);
    }


   public static double odleglosc(Punkt A, Punkt B){

        double wynik=0;
        wynik = sqrt(pow((B.X-A.X),2)+pow((B.Y-A.Y),2));

        return wynik;
    }

    public static double spadek(Punkt A, Punkt B){
        double wynik=0;

        wynik = (B.H - A.H)/odleglosc(A, B) * 100;

        return wynik;
    }

    public static double azymut(Punkt A, Punkt B){

        double wynik=0, wynik2=0;
        wynik = toDegrees(atan(abs((B.Y-A.Y)/(B.X-A.X))));
        double dx,dy;
        dx=B.X-A.X;
        dy=B.Y-A.Y;

        if (dx>0 && dy>0)
            wynik2=wynik;
        else if(dx<0 && dy>0)
            wynik2=180-wynik;
        else if(dx<0 && dy<0)
            wynik2=180+wynik;
        else  if(dx>0 && dy<0)
            wynik2=360-wynik;

    return wynik2; //w stopniach
    }

    public static Punkt domiarprost(Punkt A, Punkt B, double l, double h){

        Punkt P = new Punkt();
        P.X = A.X + ( l * cos(toRadians(azymut(A, B)))- h * sin(toRadians(azymut(A, B))));
        P.Y = A.Y + ( l * sin(toRadians(azymut(A, B)))+ h * cos(toRadians(azymut(A, B))));

        return P;
    }

    public static Punkt domiarbiegunowy (Punkt A, Punkt B, double d, double a){

        Punkt P = new Punkt();
        P.X = A.X + (d * cos(toRadians(azymut(A, B) + a)));
        P.Y = A.Y + (d * sin(toRadians(azymut(A, B) + a)));

        return P;
    }

    public static Punkt punktnaprostej(Punkt A, Punkt B, double l){

        Punkt P = new Punkt();
        P.X = A.X + ( l * cos(toRadians(azymut(A, B))));
        P.Y = A.Y + ( l * sin(toRadians(azymut(A, B))));

        return P;
    }

    public static double katCLP(Punkt C, Punkt L, Punkt P){
        double wynik = 0;

        wynik = azymut(C, P) - azymut(C, L);

        if(wynik<0) wynik=wynik+360;

        return wynik;
    }

    public static Punkt przeciecieprostych(Punkt A, Punkt B, Punkt C, Punkt D){

        Punkt P = new Punkt();

        double lambda = tan(toRadians(azymut(A, B)));
        double mi = tan(toRadians(azymut(C, D)));

        P.X = (C.Y-A.Y+(lambda*A.X)-(mi*C.X))/(lambda-mi);
        P.Y = A.Y + lambda*(P.X-A.X);

        return P;
    }

    public static Punkt wcieciewprzod(Punkt A, Punkt B, double a, double b){
        Punkt P = new Punkt();

        a = 1 / tan(toRadians(a));         //ctan = 1 / tan // a = ctg a
        b = 1 / tan(toRadians(b));

        P.X = ((A.X*b)+A.Y+(B.X*a)-B.Y)/(a+b);
        P.Y = ((A.X*-1)+(A.Y*b)+B.X+(B.Y*a))/(a+b);

        return P;
    }


    public static Punkt wciecieliniowe(Punkt A, Punkt B, double a, double b){
        Punkt P = new Punkt();

        double aa, bb, cc, Ca, Cb, Cc, Pole;

        double c = odleglosc(A,B);

        aa = a*a;
        bb = b*b;
        cc = c*c;

        Ca = bb+cc-aa;
        Cb = aa-bb+cc;
        Cc = aa+bb-cc;

        Pole = sqrt((Ca*Cb)+(Cb*Cc)+(Cc*Ca));

        P.X = ((A.X*Cb)+(A.Y*Pole)+(B.X*Ca)-(B.Y*Pole))/(Ca+Cb);
        P.Y = ((A.X*Pole*-1)+(A.Y*Cb)+(B.X*Pole)+(B.Y*Ca))/(Ca+Cb);

        return P;
    }

    public static Punkt wcieciewstecz (Punkt A, Punkt B, Punkt C, double b, double a){
        Punkt P = new Punkt();

        double f1, f2, F1, F2, F0, dx, dy;


        a = 1 / tan(toRadians(a)); // ctg a
        b = 1 / tan(toRadians(b)); // ctg b

        f1 = (C.X-A.X)-((C.Y-A.Y)*a);
        f2 = ((C.X-A.X)*a)+(C.Y-A.Y);
        F1 = (C.X-A.X)-((C.Y-A.Y)*a)+((B.X-A.X)*-1)-(B.Y-A.Y)*b*-1;
        F2 = ((C.X-A.X)*a)+(C.Y-A.Y)+((B.X-A.X)*-1*b)+(B.Y-A.Y)*-1;
        F0 = F1/F2;

        dx = (f1-(f2*F0))/((F0*F0)+1);
        dy = F0 * dx * -1;

        P.X = A.X + dx;
        P.Y = A.Y + dy;

        return P;
    }
}
