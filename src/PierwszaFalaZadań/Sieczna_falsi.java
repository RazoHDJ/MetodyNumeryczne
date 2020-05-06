package PierwszaFalaZadań;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Sieczna_falsi {
    public Sieczna_falsi() {
    }

    public static void main(String[] args) {
        Scanner scaner = new Scanner(System.in);
        System.out.println("Przykłady jak wprowadzać wielomian:");
        System.out.println("8 x^ 4 -5 x^ 3 + 6 x^ 1 + 2 x^ 0");
        System.out.println("Podaj swój wielomian:");
        String rownanie = scaner.nextLine();
        ArrayList<Integer> liczba = new ArrayList();
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
                                liczba.add(0);
                                potega.add(temp2);
                            }
                        }
                    }

                    liczba.add(tempNumber);
                    potega.add(tempPotega);
                    ostatniaPotega = tempPotega;
                    tempNumber = 0;
                }
            } catch (Exception var32) {
            }
        }

        System.out.print("podaj przybliżenie jakie chcesz osiągnąć = ");

        double alfa;
        try {
            alfa = scaner.nextDouble();
        } catch (Exception var31) {
            System.out.print("podaj przybliżenie jakie chcesz osiągnąć = ");
            alfa = scaner.nextDouble();
        }

        System.out.print("początek przedziału = ");

        try {
            xp = scaner.nextInt();
        } catch (Exception var30) {
            System.out.print("początek przedziału = ");
            xp = scaner.nextInt();
        }

        System.out.print("koniec przedziału: ");

        int xk;
        try {
            xk = scaner.nextInt();
        } catch (Exception var29) {
            System.out.print("koniec przedziału: ");
            xk = scaner.nextInt();
        }

        double fx = wielomian((double)xp, (ArrayList)liczba.clone(), potega);
        double fxx = pochodna(2, (double)xp, (ArrayList)liczba.clone(), potega);
        double a = 0.0D;
        double m = 0.0D;
        double blad = 1.0D;
        double tempWynik = 0.0D;
        double tempX = 0.0D;
        ArrayList<Double> wyniki = new ArrayList();
        boolean test;
        if (fx * fxx > 0.0D) {
            m = pochodna(1, (double)xk, (ArrayList)liczba.clone(), potega);
            a = (double)xp;
            wyniki.add((double)xp);
            test = true;
            tempX = (double)xk;
        } else {
            fx = wielomian((double)xk, (ArrayList)liczba.clone(), potega);
            fxx = pochodna(2, (double)xk, (ArrayList)liczba.clone(), (ArrayList)potega.clone());
            if (fx * fxx > 0.0D) {
                m = pochodna(1, (double)xp, (ArrayList)liczba.clone(), potega);
                a = (double)xk;
                wyniki.add((double)xk);
                test = true;
                tempX = (double)xp;
            } else {
                System.out.println("Nie ma miejsc zerowych na przedziale.");
                test = false;
            }
        }

        if (test) {
            while(blad > alfa) {
                tempWynik = tempX - wielomian(tempX, liczba, potega) * (a - tempX) / (wielomian(a, liczba, potega) - wielomian(tempX, (ArrayList)liczba.clone(), (ArrayList)potega.clone()));
                tempX = tempWynik;
                tempWynik = wielomian(tempWynik, liczba, potega);
                wyniki.add(tempX);
                blad = Math.abs(tempWynik) / m;
            }

            System.out.print("Miejsce zerowe to: x" + (wyniki.size() - 1) + "= " + wyniki.get(wyniki.size() - 1));
        }

    }

    static double pochodna(int n, double x, ArrayList<Integer> liczby, ArrayList<Integer> potegi) {
        ArrayList<Integer> liczbyTemp = (ArrayList)liczby.clone();
        ArrayList<Integer> potegiTemp = (ArrayList)potegi.clone();
        int liczba = 0;
        int potega = 0;

        for(int i = 0; i < liczbyTemp.size() - 1; ++i) {
            liczba = (Integer)liczbyTemp.get(i);
            potega = (Integer)potegiTemp.get(i);
            liczba *= potega;
            --potega;
            liczbyTemp.set(i, liczba);
            potegiTemp.set(i, potega);
        }

        liczbyTemp.remove(liczbyTemp.size() - 1);
        return n > 1 ? pochodna(n - 1, x, (ArrayList)liczbyTemp.clone(), (ArrayList)potegiTemp.clone()) : wielomian(x, liczbyTemp, potegiTemp);
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
}
