import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class ConversorMonedas {

    public static void main(String[] args) {


        String apiKey = "b6f2694fcd3dae4dad1a6e8a";
        String monedaBase = "";
        String monedaConvertir = "";
        int opcion = 0;
        String menu = """
                
                Bienvenido al conversor de monedas
                
                1) USD -> COP
                2) CAD -> COP
                3) ARS -> COP
                4) AUD -> ARS
                5) USD -> BZD
                6) COP -> AUD
                
                7) Salir
                
                Seleccione una opcion valida.
                ____________________________________________________
                """;
        System.out.println(menu);

        while (opcion != 7) {

        Scanner scanner = new Scanner((System.in));
        opcion = scanner.nextInt();


        switch (opcion) {
            case 1 -> {
                monedaBase = "USD";
                monedaConvertir = "COP";
            }
            case 2 -> {
                monedaBase = "CAD";
                monedaConvertir = "COP";
            }
            case 3 -> {
                monedaBase = "ARS";
                monedaConvertir = "COP";
            }
            case 4 -> {
                monedaBase = "AUD";
                monedaConvertir = "ARS";
            }
            case 5 -> {
                monedaBase = "USD";
                monedaConvertir = "BZD";
            }
            case 6 -> {
                monedaBase = "COP";
                monedaConvertir = "AUD";
            }
            case 7 -> {
                System.out.println("Gracias por usar el conversor.");
                return;
            }
            default -> {
                System.out.println("Opción no válida.");
                return;
            }
        }


            System.out.print("Ingrese la cantidad a convertir: ");
            double valorAconvertir = scanner.nextDouble();


            try {
                String apiURL = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + monedaBase;

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(apiURL))
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


                JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
                JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");

                double tasa = rates.get(monedaConvertir).getAsDouble();
                double resultado = valorAconvertir * tasa;


                System.out.println("El monto de " + valorAconvertir + monedaBase + " equivale a " + resultado + monedaConvertir);



            } catch (Exception e) {
                throw new RuntimeException(e);
            }System.out.println(menu);
        }


    }

}
