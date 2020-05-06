package PierwszaFalaZadań;

import static java.lang.Math.abs;

/**
 *
 * Źródło 1: https://pl.wikipedia.org/wiki/Metoda_r%C3%B3wnego_podzia%C5%82u
 * Źródło 2: http://www.mathcs.emory.edu/~cheung/Courses/170/Syllabus/07/bisection.html
 */
public class Bisekcja {
    public static void main(String[] args) {
        double a, b, epsilon;
        a = 0;
        b = 4;
        epsilon = 0.00001;
        if (funkcja(a)==0) {
            System.out.println("Szukaną współrzędną było a: " + a);
        }
        else if(funkcja(b)==0){
            System.out.println("Szukaną współrzędną było b: " + b);
        }
        else{
            System.out.println("Wynik algorytmu Bisekcji: " + algorytmBisekcji(a, b, epsilon));
        }

    }

    public static double algorytmBisekcji(double a, double b, double epsilon){
        double buf = funkcja((a+b)/2);
        if (buf ==0) {

            return ((a+b)/2);
        }
        else{
            if (abs(b-a)>epsilon) {
                double x = ((a+b)/2);
                if ((funkcja(a)*funkcja(x))<0) {
                    System.out.println("Nowe rozwiązanie: "+ (a+b)/2);
                    System.out.println("Dla przedziału: [" + a + " ; " + b + "]");
                    return algorytmBisekcji( a, x, epsilon);
                }
                else if ((funkcja(b)*funkcja(x))<0) {
                    System.out.println("Nowe rozwiązanie: "+ (a+b)/2);
                    System.out.println("Dla przedziału: [" + a + " ; " + b + "]");
                    return algorytmBisekcji( x, b, epsilon);
                }
                else{
                    System.out.println("ERROR");
                    return 0;
                }

            }
            else{
                return ((a+b)/2);
            }
        }

    }



//    public static double funkcja(double x){
//        double wynik = ((x*x)*x -x +1) ; //dla wartości 2, -2
//        return wynik;
//    }

//      public static double funkcja(double x){
//          double wynik = ((x*x)*x -3*(x*x) -2*x +5); //dla wartości 1,2
//          return wynik;
//      }

//    public static double funkcja (double x){ //-3 3
//        double wynik = ((x*x)*x -(5*x) + 1);
//        return wynik;
//    }

//    public static double funkcja (double x){ //1,2
//        double wynik = (x*x -2);
//        return wynik;
//    }
//    public static double funkcja (double x) { // -200 300
//        double wynik = (((x*x)*x) - (x*x) +2);
//        return wynik;
//    }
    public static double funkcja (double x) { //0 ,4 epsi 0.00001
        double wynik = (x*x - 3);
        return wynik;
    }
}