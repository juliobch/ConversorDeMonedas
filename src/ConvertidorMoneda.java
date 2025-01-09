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
        String urlPeticion = String.format("%s%s", urlApi, monedaOrigen);
        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(URI.create(urlPeticion))
                .GET()
                .build();
        HttpResponse<String> respuesta = clienteHttp.send(peticion, HttpResponse.BodyHandlers.ofString());

        JsonObject respuestaJson = JsonParser.parseString(respuesta.body()).getAsJsonObject();

        if (!respuestaJson.get("result").getAsString().equals("success")) {
            throw new RuntimeException("Error al obtener la tasa de cambio, no se encontro el tipo de moneda solicitado.");
        }
        return respuestaJson.getAsJsonObject("conversion_rates").get(monedaDestino).getAsDouble();
    }

}
