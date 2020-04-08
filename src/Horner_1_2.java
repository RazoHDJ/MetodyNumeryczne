import java.util.Scanner;

public class Horner_1_2 {
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        boolean petla = true;
        while (petla) {
            System.out.println("----------Menu----------");
            System.out.println("1.Schemat Hornera - wyznaczanie wartości wielomianu");
            System.out.println("2.Schemat Hornera - dzielenie wielomianu przez dwumian");
            System.out.println("0. EXIT");
            System.out.print("Wybierz numer: ");
            int zadanie = in.nextInt();

            switch (zadanie) {
                case 1:
                    wyznaczanieWartosciWielomianu();
                    break;
                case 2:
                    dzielenieWielomianuPrzezDwumian();
                    break;
                case 0:
                    petla = false;
                    break;
                default:
                    System.out.println("Wybrano zły nr zadania");
                    break;
            }
        }


    }

    public static double[] wczytanieWielomianu() {
        System.out.println("Podaj stopień wielomianu");
        int stopien = in.nextInt();
        double[] wielomian = new double[stopien + 1];
        System.out.println("Podaj współczynniki wielomianu zaczynając od najwyższej potęgi");
        for (int i = 0; i < wielomian.length; i++) {
            wielomian[i] = in.nextDouble();
        }
        return wielomian;
    }

    public static void dzielenieWielomianuPrzezDwumian() {
        double[] wielomian = wczytanieWielomianu();
        double a, b;
        System.out.print("Podaj dwumian(ax + b)\na: ");
        a = in.nextDouble();
        System.out.print("b: ");
        b = in.nextDouble();
        double dzielnik = -b / a;
        double[] wynik = new double[wielomian.length];
        wynik[0] = wielomian[0];
        for (int i = 1; i < wielomian.length; i++) {
            wynik[i] = dzielnik * wynik[i-1] + wielomian[i];
        }

        for (int i = 0, numer = wynik.length-1; i < wynik.length - 1; i++) {
            numer--;
            System.out.println("x^"+ numer +": " + wynik[i]);
        }
        System.out.println("Reszta z dzielenia: " + wynik[wynik.length-1]);
    }


    public static void wyznaczanieWartosciWielomianu() {
        double[] wielomian = wczytanieWielomianu();
        System.out.print("Podaj punkt: ");
        double punkt = in.nextDouble();
        double wynik = wielomian[0];
        for (int i = 1; i < wielomian.length; i++) {
            wynik = wynik * punkt + wielomian[i];
        }
        System.out.println("Wartość funkcji w punkcie p(" + punkt + ") = " + wynik);
    }

}





















