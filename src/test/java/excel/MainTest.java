
package excel;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excel.model.Product;
import http.Client;

public class MainTest {
    private Client client;

    @BeforeEach
    public void setUp() {
        client = new Client();
    }

    @Test
    public void testGetProductsList() {
        List<Product> products = client.getProductsList();
        assert(products.size() > 0);
    }
}
