package PierwszaFalaZadań;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Styczne {
    public Styczne() {
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

    static double pochodna(int n, double x, ArrayList<Integer> Liczba, ArrayList<Integer> potegi) {
        ArrayList<Integer> liczbyTemp = Liczba;
        ArrayList<Integer> potegiTemp = potegi;
        int liczba = 0;
        int potega = 0;

        for(int i = 0; i < Liczba.size() - 1; ++i) {
            liczba = (Integer)liczbyTemp.get(i);
            potega = (Integer)potegiTemp.get(i);
            liczba *= potega;
            --potega;
            liczbyTemp.set(i, liczba);
            potegiTemp.set(i, potega);
        }

        Liczba.remove(Liczba.size() - 1);
        if (n > 1) {
            return pochodna(n - 1, x, liczbyTemp, potegiTemp);
        } else {
            List<Double> wynik = new ArrayList();
            double tempWynik = 0.0D;

            for(int i = 0; i < liczbyTemp.size(); ++i) {
                tempWynik = potega((Integer)potegiTemp.get(i), x);
                tempWynik *= (double)(Integer)liczbyTemp.get(i);
                wynik.add(tempWynik);
            }

            tempWynik = 0.0D;

            double i;
            for(Iterator var18 = wynik.iterator(); var18.hasNext(); tempWynik += i) {
                i = (Double)var18.next();
            }

            return tempWynik;
        }
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
            } catch (Exception var31) {
            }
        }

        System.out.print("podaj przybliżenie ktore chcesz osiągnąć = ");

        double alfa;
        try {
            alfa = scaner.nextDouble();
        } catch (Exception var30) {
            System.out.print("podaj przybliżenie ktore chcesz osiągnąć = ");
            alfa = scaner.nextDouble();
        }

        System.out.print("początek przedziału = ");

        try {
            xp = scaner.nextInt();
        } catch (Exception var29) {
            System.out.print("początek przedziału = ");
            xp = scaner.nextInt();
        }

        System.out.print("koniec przedziału: ");

        int xk;
        try {
            xk = scaner.nextInt();
        } catch (Exception var28) {
            System.out.print("koniec przedziału: ");
            xk = scaner.nextInt();
        }

        double fx = wielomian((double)xp, liczba, potega);
        double fxx = pochodna(2, (double)xp, liczba, potega);
        double blad = 1.0D;
        double tempWynik = 0.0D;
        ArrayList<Double> wyniki = new ArrayList();
        double m;
        double tempX;
        double temp;
        if (fx * fxx > 0.0D) {
            m = pochodna(1, (double)xk, liczba, potega);
            wyniki.add((double)xp);

            for(tempX = (double)xp; blad > alfa; blad = temp / m) {
                tempWynik = tempX - wielomian(tempX, liczba, potega) / pochodna(1, tempX, liczba, potega);
                tempX = tempWynik;
                wielomian(tempWynik, liczba, potega);
                wyniki.add(tempWynik);
                temp = wielomian(tempWynik, liczba, potega);
                if (temp < 0.0D) {
                    temp *= -1.0D;
                }
            }
        } else {
            fx = wielomian((double)xk, liczba, potega);
            fxx = pochodna(2, (double)xk, liczba, potega);
            if (fx * fxx > 0.0D) {
                m = pochodna(1, (double)xp, liczba, potega);
                wyniki.add((double)xk);

                for(tempX = (double)xk; blad > alfa; blad = temp / m) {
                    tempWynik = tempX - wielomian(tempX, liczba, potega) / pochodna(1, tempX, liczba, potega);
                    tempX = tempWynik;
                    wielomian(tempWynik, liczba, potega);
                    wyniki.add(tempWynik);
                    temp = wielomian(tempWynik, liczba, potega);
                    if (temp < 0.0D) {
                        temp *= -1.0D;
                    }
                }
            } else {
                System.out.println("Nie ma miejsc zerowych na przedziale.");
            }
        }

        System.out.print("Miejsce zerowe to: x" + wyniki.size() + "= " + wyniki.get(wyniki.size() - 1));
    }
}
