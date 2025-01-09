import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        String urlApi = "https://v6.exchangerate-api.com/v6/5de963d9893c01190381dcfb/latest/"; // URL de ejemplo para la API
        ConvertidorMonedaAvanzado convertidor = new ConvertidorMonedaAvanzado(urlApi);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú del Convertidor de Moneda:");
            System.out.println("1. Convertir Moneda");
            System.out.println("2. Ver Historial de Conversiones");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la moneda de origen (por ejemplo, USD): ");
                    String monedaOrigen = scanner.next().toUpperCase();
                    System.out.print("Ingrese la moneda de destino (por ejemplo, EUR): ");
                    String monedaDestino = scanner.next().toUpperCase();
                    System.out.print("Ingrese la cantidad a convertir: ");
                    double cantidad = scanner.nextDouble();

                    try {
                        double resultado = convertidor.convertirMoneda(monedaOrigen, monedaDestino, cantidad);
                        System.out.printf("Cantidad convertida: %.2f %s\n", resultado, monedaDestino);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    convertidor.mostrarHistorialConversiones();
                    break;

                case 3:
                    System.out.println("Saliendo del programa. ¡Adios!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción inválida. Por favor, intente de nuevo.");
            }
        }
    }
}
