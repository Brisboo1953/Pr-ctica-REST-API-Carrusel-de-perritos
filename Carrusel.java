import com.google.gson.Gson;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Carrusel {
    public static final String BASE_URL = "https://dog.ceo/api/breeds/image/random/";

    public static void main(String[] args) {
        int n = (int) (Math.random() * 11) + 10; // Genera un número aleatorio entre 10 y 20
        String breed = JOptionPane.showInputDialog("Ingrese la raza de perro (o presione Cancelar para imágenes aleatorias):");

        if (breed != null && !breed.isEmpty()) {
            String customUrl = BASE_URL + n + "?breed=" + breed;
            DogsApiResponse response = query(customUrl);
            ArrayList<String> urls = response.getMessage();
            for (String u : urls) {
                System.out.println(u);
                EventQueue.invokeLater(() -> {
                    JFrame ex = new Verimg(u);
                    ex.setVisible(true);
                });
            }
        } else {
            DogsApiResponse response = query(BASE_URL + n);
            ArrayList<String> urls = response.getMessage();
            for (String u : urls) {
                System.out.println(u);
                EventQueue.invokeLater(() -> {
                    JFrame ex = new Verimg(u);
                    ex.setVisible(true);
                });
            }
        }
    }

    public static DogsApiResponse query(String urlString) {
        DogsApiResponse response = null;
        try {
            URL url = new URL(urlString);

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String buffer;
            StringBuilder jsonText = new StringBuilder();
            while ((buffer = in.readLine()) != null) {
                jsonText.append(buffer);
            }
            in.close();
            Gson gson = new Gson();
            response = gson.fromJson(jsonText.toString(), DogsApiResponse.class);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
