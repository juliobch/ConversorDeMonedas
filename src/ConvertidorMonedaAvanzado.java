import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ConvertidorMonedaAvanzado extends ConvertidorMoneda {
    private List<String> historialConversiones;

    public ConvertidorMonedaAvanzado(String urlApi) {
        super(urlApi);
        this.historialConversiones = new ArrayList<>();
    }

    public double convertirMoneda(String monedaOrigen, String monedaDestino, double cantidad) throws IOException, InterruptedException {
        double tasa = obtenerTasaCambio(monedaOrigen, monedaDestino);
        double resultado = tasa * cantidad;
        registrarConversion(monedaOrigen, monedaDestino, cantidad, resultado);
        return resultado;
    }

    private void registrarConversion(String monedaOrigen, String monedaDestino, double cantidad, double resultado) {
        String marcaTiempo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String registro = String.format("%s: Convertidos %.2f %s a %.2f %s", marcaTiempo, cantidad, monedaOrigen, resultado, monedaDestino);
        historialConversiones.add(registro);
    }

    public void mostrarHistorialConversiones() {
        if (historialConversiones.isEmpty()) {
            System.out.println("No hay historial de conversiones disponible.");
        } else {
            System.out.println("Historial de Conversiones:");
            for (String entrada : historialConversiones) {
                System.out.println(entrada);
            }
        }
    }
}
