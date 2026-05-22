package model;


public class CalculoCombinacion {


 
    public static int calcularFactorial(int numero) {
        int factorial = 1;
        for (int i = 1; i <= numero; i++) {
            factorial *= i;
        }
        return factorial;
    }

  
    public static int calcularCombinacion(int n, int r) {
        if (r < 0 || r > n) {
            return 0; // Validación básica
        }
        int numerador = calcularFactorial(n);
        int denominador = calcularFactorial(r) * calcularFactorial(n - r);
        
        return numerador / denominador;
    }
}