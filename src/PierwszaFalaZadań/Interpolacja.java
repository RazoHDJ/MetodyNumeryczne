package PierwszaFalaZadań;

/**
 *
 * Źródło przypomnienia : https://www.youtube.com/watch?v=vAgKE5wvR4Y
 */
public class Interpolacja {

    public static void main(String[] args) {
        double[] x = {1, 2, 3, 4, 5};
        double[] y = {8, 4, 7, 6, 3};
        double argx = 3.8; //int Argument x

        System.out.println("Wynik: x=" + argx + " => y=" + InterpolacjaLaGrange(argx, x, y));
    }

    public static double InterpolacjaLaGrange(double argx, double[] x, double[] y){
        double fx = 1.0;
        double wynik = 0;

        for (int i = 0; i < x.length; i++) {
            fx = 1.00;
            for (int j = 0; j < x.length; j++) {
                if (i != j) {
                    fx *= ((argx-x[j])/(x[i]-x[j]));
                }
            }
            wynik += fx * y[i];
        }


        return wynik;
    }
}