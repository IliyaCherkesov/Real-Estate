package http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import excel.model.Product;

public class Client {
    private HttpClient client = HttpClient.newHttpClient();
    private String baseUrl = "https://api.escuelajs.co/api/v1";

    public Client() {
    }

    public List<Product> getProductsList() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.baseUrl + "/products"))
                .build();
        try {
            HttpResponse<String> response = this.client.send(request, HttpResponse.BodyHandlers.ofString());
            return Product.parseProductList(response.body());
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Product getProductDetail(int id) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.baseUrl + "/products/" + id))
                .build();
        try {
            HttpResponse<String> response = this.client.send(request, HttpResponse.BodyHandlers.ofString());
            return Product.parseProduct(response.body());
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
