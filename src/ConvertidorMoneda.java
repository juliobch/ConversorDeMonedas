import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConvertidorMoneda {
    protected HttpClient clienteHttp;
    protected String urlApi;

    public ConvertidorMoneda(String urlApi) {
        this.clienteHttp = HttpClient.newHttpClient();
        this.urlApi = urlApi;
    }

    public double obtenerTasaCambio(String monedaOrigen, String monedaDestino) throws IOException, InterruptedException {
        // Construir la URL completa para la moneda base
        String urlPeticion = String.format("%s%s", urlApi, monedaOrigen);

        // Crear la solicitud HTTP
        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(URI.create(urlPeticion))
                .GET()
                .build();

        // Enviar la solicitud y obtener la respuesta
        HttpResponse<String> respuesta = clienteHttp.send(peticion, HttpResponse.BodyHandlers.ofString());

        // Parsear la respuesta JSON
        JsonObject respuestaJson = JsonParser.parseString(respuesta.body()).getAsJsonObject();

        if (!respuestaJson.get("result").getAsString().equals("success")) {
            throw new RuntimeException("Error al obtener la tasa de cambio.");
        }

        // Obtener la tasa de cambio desde conversion_rates
        return respuestaJson.getAsJsonObject("conversion_rates").get(monedaDestino).getAsDouble();
    }

}
