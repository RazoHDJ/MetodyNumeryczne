package DrugaFalaZadań;

import java.io.IOException;

public class ElimnacjaGausa {

    public static void main(String[] args) throws IOException {

//rozmiar tablicy
        int n=3;

//macierz do rozwiazania
        double macierz [][] = {{3, 1, 0},
                {1, 2, -1},
                {0, -1, 3}};

//macierz wynikowa
        double macWyn [][] = new double [macierz.length][macierz.length];

//kopiuj macierz do rozwiazania do macierzy wynikowej
        for (int i = 0; i<macierz.length; i++)
            for (int j = 0; j<macierz.length; j++)
                macWyn[i][j]=macierz[i][j];

//wektor w wynikami
        double wynik [] = {-3, 0, 0};

//kopiuj wektor
        double wCopy [] = new double [wynik.length];
        for (int i=0; i<wynik.length; i++){
            wCopy[i]=wynik[i];
        }

//wyswietl  macierz do rozwiazania
        for (int j = 0; j<n; j++){
            for (int k=0; k<n; k++){
                System.out.print(macierz[j][k]+"\t");
            }
            System.out.println("");
        }
        System.out.println("");

//Metoda Eliminacji Gaussa
        for (int i = 0; i<n-1; i++){
            for (int j = i+1; j<=n-1; j++){
                for (int k = 0; k<n; k++){
                    macWyn[j][k]=macierz[j][k]-(macierz[i][k]*(macierz[j][i]/macierz[i][i]));
                }

                wCopy[j]=wynik[j]-(wynik[i]*(macierz[j][i]/macierz[i][i]));

                for (int ii = 0; ii<macWyn.length; ii++){
                    for (int jj = 0; jj<macWyn.length; jj++){
                        macierz[ii][jj]=macWyn[ii][jj];
                    }
                    wynik[ii]=wCopy[ii];
                }
            }
        }

//Wypisanie wyniku
        System.out.println("");
        System.out.println("");
        for (int j = 0; j<n; j++){
            for (int k=0; k<n; k++){
                System.out.print(macierz[j][k]+"\t");
            }
            System.out.println("\t"+wynik[j]);
        }
    }
}