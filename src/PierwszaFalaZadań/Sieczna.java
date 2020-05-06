package PierwszaFalaZadań;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Sieczna {
    public Sieczna() {
    }

    static double potega(int n, double x) {
        double step = x;
        if (n > 1) {
            for(int i = 1; i < n; ++i) {
                x *= step;
            }
        } else if (n == 0) {
            x = 1.0D;
        }

        return x;
    }

    static double pochodna(int n, double x, ArrayList<Integer> liczby, ArrayList<Integer> potegi) {
        ArrayList<Integer> liczbaTemp = (ArrayList)liczby.clone();
        ArrayList<Integer> potegiTemp = (ArrayList)potegi.clone();
        int liczba = 0;
        int potega = 0;

        for(int i = 0; i < liczbaTemp.size() - 1; ++i) {
            liczba = (Integer)liczbaTemp.get(i);
            potega = (Integer)potegiTemp.get(i);
            liczba *= potega;
            --potega;
            liczbaTemp.set(i, liczba);
            potegiTemp.set(i, potega);
        }

        liczbaTemp.remove(liczbaTemp.size() - 1);
        return n > 1 ? pochodna(n - 1, x, (ArrayList)liczbaTemp.clone(), (ArrayList)potegiTemp.clone()) : wielomian(x, liczbaTemp, potegiTemp);
    }

    static double wielomian(double x, ArrayList<Integer> liczba, ArrayList<Integer> potega) {
        List<Double> wynik = new ArrayList();
        double tempWynik = 0.0D;

        for(int i = 0; i < liczba.size(); ++i) {
            tempWynik = potega((Integer)potega.get(i), x);
            tempWynik *= (double)(Integer)liczba.get(i);
            wynik.add(tempWynik);
        }

        tempWynik = 0.0D;

        double i;
        for(Iterator var10 = wynik.iterator(); var10.hasNext(); tempWynik += i) {
            i = (Double)var10.next();
        }

        return tempWynik;
    }

    public static void main(String[] args) {
        Scanner scaner = new Scanner(System.in);
        System.out.println("Przykłady jak wprowadzać wielomian:");
        System.out.println("1 x^ 4 -7 x^ 3 + 8 x^ 1 + 5 x^ 0");
        System.out.println("Podaj swój wielomian:");
        String rownanie = scaner.nextLine();
        ArrayList<Integer> liczby = new ArrayList();
        ArrayList<Integer> potega = new ArrayList();
        int tempNumber = 0;
        int ostatniaPotega = -1;
        String[] tabRow = rownanie.split(" ");
        String[] var9 = tabRow;
        int var10 = tabRow.length;

        int xp;
        for(xp = 0; xp < var10; ++xp) {
            String temp = var9[xp];

            try {
                if (tempNumber == 0) {
                    tempNumber = Integer.parseInt(temp);
                } else {
                    int tempPotega = Integer.parseInt(temp);
                    if (ostatniaPotega != -1) {
                        if (tempPotega != ostatniaPotega - 1) {
                            int temp2 = ostatniaPotega;

                            while(temp2 - 1 > tempPotega) {
                                --temp2;
                                liczby.add(0);
                                potega.add(temp2);
                            }
                        }
                    }

                    liczby.add(tempNumber);
                    potega.add(tempPotega);
                    ostatniaPotega = tempPotega;
                    tempNumber = 0;
                }
            } catch (Exception var33) {
            }
        }

        System.out.print("podaj przybliżenie ktore chcesz osiągnąć = ");

        double alfa;
        try {
            alfa = scaner.nextDouble();
        } catch (Exception var32) {
            System.out.print("podaj przyblizenie ktore chcesz osiągnąć = ");
            alfa = scaner.nextDouble();
        }

        System.out.print("poczatek przedzialu = ");

        try {
            xp = scaner.nextInt();
        } catch (Exception var31) {
            System.out.print("poczatek przedzialu = ");
            xp = scaner.nextInt();
        }

        System.out.print("koniec przedzialu: ");

        int xk;
        try {
            xk = scaner.nextInt();
        } catch (Exception var30) {
            System.out.print("koniec przedzialu: ");
            xk = scaner.nextInt();
        }

        double m = 0.0D;
        double a = 0.0D;
        double b = 0.0D;
        double blad = 1.0D;
        double fx = wielomian((double)xp, (ArrayList)liczby.clone(), potega);
        double fxx = pochodna(2, (double)xp, (ArrayList)liczby.clone(), potega);
        boolean test;
        if (fx * fxx > 0.0D) {
            a = (double)xp;
            b = (double)xk;
            m = pochodna(1, (double)xk, (ArrayList)liczby.clone(), potega);
            test = true;
        } else {
            fx = wielomian((double)xk, (ArrayList)liczby.clone(), potega);
            fxx = pochodna(2, (double)xk, (ArrayList)liczby.clone(), potega);
            if (fx * fxx > 0.0D) {
                a = (double)xk;
                b = (double)xp;
                m = pochodna(1, (double)xp, (ArrayList)liczby.clone(), potega);
                test = true;
            } else {
                System.out.println("Nie ma miejsc zerowych na przedziale.");
                test = false;
            }
        }

        double tempX = 0.0D;
        if (test) {
            while(blad > alfa) {
                tempX = a - wielomian(a, liczby, potega) * (b - a) / (wielomian(b, liczby, potega) - wielomian(a, liczby, potega));
                wielomian(tempX, liczby, potega);
                blad = Math.abs(wielomian(tempX, liczby, potega)) / m;
                a = b;
                b = tempX;
            }
        }

        System.out.println("Miejsce zerowe to x = " + tempX);
    }
}
