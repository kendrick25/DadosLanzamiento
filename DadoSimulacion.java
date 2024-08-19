import java.util.Random;
import java.util.Scanner;

public class DadoSimulacion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar la cantidad de réplicas, números del dado y lanzamientos
        System.out.println("\n-----------------------------------------------");
        System.out.print("Ingrese la cantidad de numeros que tiene el dado: ");
        int carasDado = scanner.nextInt();
        System.out.println("\n-----------------------------------------------");
        System.out.print("Ingrese la cantidad de lanzamientos: ");
        int lanzamientos = scanner.nextInt();
        System.out.println("\n-----------------------------------------------");
        System.out.print("Ingrese el numero de replicas: ");
        int numReplicas = scanner.nextInt();
        System.out.println("\n-----------------------------------------------");
        Random random = new Random();
        
        // Contadores para cada número del dado en cada réplica
        int[][] conteos = new int[numReplicas][carasDado];
        
        // Simulación de lanzamientos
        for (int r = 0; r < numReplicas; r++) {
            for (int i = 0; i < lanzamientos; i++) {
                int resultado = random.nextInt(carasDado) + 1;
                conteos[r][resultado - 1]++;
            }
        }
        
        // Imprimir tabla de frecuencias
        System.out.println("\n---------- Tabla de Frecuencias ----------");
        System.out.printf("%-10s", "Valor");
        for (int r = 1; r <= numReplicas; r++) {
            System.out.printf("%-10s", "Frec " + r);
        }
        System.out.println();
        
        for (int v = 0; v < carasDado; v++) {
            System.out.printf("%-10d", v + 1);
            for (int r = 0; r < numReplicas; r++) {
                System.out.printf("%-10d", conteos[r][v]);
            }
            System.out.println();
        }
        
        // Imprimir total de lanzamientos
        System.out.printf("%-10s", "Total");
        for (int r = 0; r < numReplicas; r++) {
            System.out.printf("%-10d", lanzamientos);
        }
        System.out.println();
        
        // Calcular y almacenar frecuencias relativas
        double[][] frecRel = new double[numReplicas][carasDado];
        double[] totalFrecRel = new double[carasDado];
        double[] sumaFrecRel = new double[numReplicas];  // Para almacenar la suma de las frecuencias relativas por réplica
        
        System.out.println("\n---------- Tabla de Frecuencias Relativas ----------");
        System.out.printf("%-10s", "Valor");
        for (int r = 1; r <= numReplicas; r++) {
            System.out.printf("%-15s", "Frec Rel. " + r);
        }
        System.out.printf("  %-20s\n", "Promedio de Frec. Rel.");
        
        for (int v = 0; v < carasDado; v++) {
            System.out.printf("%-10d", v + 1);
            double sumaRel = 0.0;
            
            // Imprimir frecuencias relativas
            for (int r = 0; r < numReplicas; r++) {
                double frecRelActual = (double) conteos[r][v] / lanzamientos;
                frecRel[r][v] = frecRelActual;
                totalFrecRel[v] += frecRelActual;
                sumaRel += frecRelActual;
                sumaFrecRel[r] += frecRelActual;
                System.out.printf("%-15.3f", frecRelActual);
            }
            
            double promedioFrecRel = sumaRel / numReplicas;
            System.out.printf("  %-20.3f\n", promedioFrecRel);
        }
        
        // Imprimir total de frecuencias relativas y promedio
        System.out.printf("%-10s", "Total");
        for (int r = 0; r < numReplicas; r++) {
            System.out.printf("%-15.3f", sumaFrecRel[r]);
        }
        // Imprimir total de la columna de promedios
        double totalPromedio = 0.0;
        for (int i = 0; i < carasDado; i++) {
            totalPromedio += totalFrecRel[i];
        }
        System.out.printf("  %-20.3f\n", totalPromedio / numReplicas);
        System.out.println("\n-----------------------------------------------");
        scanner.close();
    }
}