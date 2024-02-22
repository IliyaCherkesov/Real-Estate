package excel;

import java.util.List;

import excel.model.Product;
import excel.utils.Excel;
import http.Client;

public class Main
{
    public static void main(String[] args)
    {
        Client client = new Client();
        List<Product> products = client.getProductsList();
        Product firstProduct = products.get(0);
        Product productDetail = client.getProductDetail(firstProduct.getId());
        Excel.exportToExcelProductsList("test.xlsx", products);
        Excel.exportToExcelProductDetail(productDetail);
        Excel.saveToFile();
    }

}

