package excel.model;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Product {
    private int id;

    private String title;
    private double price;
    private String description;
    private Category category;
    private List<String> images;

    public Product(int id, String title, double price, String description, Category category, List<String> images) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() { return price; }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public List<String> getImages() {
        return images;
    }

    public static List<Product> parseProductList(String text) {
        Gson gson = new Gson();
        JsonElement json = JsonParser.parseString(text);
        JsonArray productsList = json.getAsJsonArray();
        return productsList
                .asList()
                .stream()
                .map(item -> gson.fromJson(item, Product.class))
                .toList();
    }

    public static Product parseProduct(String text) {
        Gson gson = new Gson();
        JsonElement json = JsonParser.parseString(text);
        return gson.fromJson(json, Product.class);
    }
}
