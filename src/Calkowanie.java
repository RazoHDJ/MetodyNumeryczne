import java.sql.SQLOutput;
import java.util.Scanner;

public class Calkowanie {
    public static final Scanner in = new Scanner(System.in);
    private static double a;
    private static double b;
    private static int n;
    private static double krok;
    private static double wynik;

    public static void main(String[] args) {
        boolean powtorzyc = true;
        int wybor;
        while (powtorzyc) {
            System.out.println("----------MENU----------");
            System.out.println("1. Metoda Prostokątów");
            System.out.println("2. Metoda Trapezów");
            System.out.println("3. Metoda Paraboli");
            System.out.println("0. EXIT");
            System.out.println("Wybierz numer:");
            wybor = in.nextInt();

            switch (wybor) {
                case 1:
                    metodaProstokatow();
                    break;
                case 2:
                    metodaTrapezow();
                    break;
                case 3:
                    metodaParaboli();
                    break;
                case 0:
                    System.exit(1);
                    break;
                default:
                    System.out.println("Wybrano zły numer");
                    break;
            }
        }
    }

    private static void metodaProstokatow() {
        zakresCalki();
        double temp;
        for (int i = 1; i <= n; i++) {
            temp = funkcjaPodCalkowa(a + i * krok);
            System.out.println("f(" + (a + i * krok) + ") = " + temp);
            wynik += temp;
        }
        System.out.println("Wynik całkowania metodą Prostokątów: " + wynik * krok);


    }

    public static void metodaTrapezow() {
        zakresCalki();
        double[] temp = new double[n + 1];
        for (int i = 0; i <= n; i++) {
            temp[i] = funkcjaPodCalkowa(a + i * krok);
            System.out.println("f(" + (a + i * krok) + ") = " + temp[i]);
        }
        for (int i = 1; i < n; i++) {
            wynik += temp[i];
        }
        wynik += (temp[0] + temp[n]) / 2;
        wynik *= krok;
        System.out.println("Wynik całkowania metodą Trapezów: " + wynik);
    }

    private static void metodaParaboli() {
        zakresCalki();
        n *= 2;
        krok = (b - a)/n;
        double[] temp = new double[n+1];
        for (int i = 0; i <= n; i++) {
            if (i == 0 || i == temp.length - 1) {
                //pierwszy i ostatni indeks
                temp[i] = funkcjaPodCalkowa(a + i * krok);
            } else if (i % 2 == 1) {
                //nieparzyste indeksy
                temp[i] = 4 * funkcjaPodCalkowa(a + i * krok);
            } else {
                //parzyste ideksy
                temp[i] = 2 * funkcjaPodCalkowa(a + i * krok);
            }
            System.out.println("temp[" + (a + i * krok) + "] = " + temp[i]);
        }
        for (double current : temp) {
            wynik += current;
        }
        wynik = wynik / 3 * krok;
        System.out.println("Wynik całkowania metodą Paraboli: " + wynik);
    }


    public static double funkcjaPodCalkowa(double x) {
        //Miejsce zmieniania fukcji podcałkowej
        return Math.pow(x, 2) + 3;
//        return Math.pow(x, 2) + 12;
//        return 2 * Math.pow(x, 2) + 1;


    }

    private static void zakresCalki() {
        System.out.println("Podaj przedział całkowania funkcji f(x)");
        System.out.print("a: ");
        a = in.nextDouble();
        System.out.print("b: ");
        b = in.nextDouble();
        System.out.print("Podaj dokładność(ilosć podprzedziałów): ");
        n = in.nextInt();
        krok = (b - a) / n;
        wynik = 0;
    }


}
