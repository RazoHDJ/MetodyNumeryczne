package DrugaFalaZadań;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLOutput;
import java.util.Scanner;

public class RozkladMetodaLU {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //Pobieranie wymierau macierzy
        System.out.print("Podaj wymiar macierzy: ");
        int n = in.nextInt();

        //Tworzenie oraz wypełnianie macierzy
        double[][] A = new  double[n][n];
        System.out.println("---Podaj macierz A---");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("A["+i+"]["+j+"] -> ");
                A[i][j] = in.nextDouble();
            }
            System.out.println();
        }
        System.out.println("Utworzona Macierz A:");
        printMatrix(A);
        System.out.println("Macierz L");
        double[][] L = matrixL(A);
        printMatrix(L);
        System.out.println("Macierz U");
        double[][] U = matrixU(A);
        printMatrix(U);

    }



    /**
     * Wyświetlanie macierzy
     * @param Matrix macierz do wyświeltnia
     */
    public static void printMatrix(double[] @NotNull [] Matrix){
        for (int i = 0; i < Matrix.length; i++) {
            for (int j = 0; j < Matrix.length; j++) {
                System.out.print(Matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Tworzenie macierzy L
     * @param Matrix macierz z której tworzymy macierz L
     * @return double[][] macierz L
     */
    public static double[][] matrixL(double[][] Matrix) {
        double[][] Lmatrix = new double[Matrix.length][Matrix.length];
        for (int i = 0; i < Matrix.length; i++) {
            for (int j = 0; j < Matrix.length; j++) {
                if (j < i) {
                    Lmatrix[i][j] = Matrix[i][j];
                } else if (j == i) {
                    Lmatrix[i][j] = 1;
                } else {
                    Lmatrix[i][j] = 0;
                }
            }
        }
        return Lmatrix;
    }

    /**
     * Tworzenie macierzy U
     * @param Matrix macierz z której zostanie utworzona macierz U
     * @return double[][] macierz U
     */
    private static double[][] matrixU(double[][] Matrix) {
        double[][] Umatrix = new double[Matrix.length][Matrix.length];
        for (int i = 0; i < Matrix.length; i++) {
            for (int j = 0; j < Matrix.length; j++) {
                if (j >= i) {
                    Umatrix[i][j] = Matrix[i][j];
                }else {
                    Umatrix[i][j] = 0;
                }
            }
        }

        return Umatrix;
    }
}
