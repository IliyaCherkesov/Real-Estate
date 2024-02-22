package excel.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;

import excel.model.Product;


public class Excel {
    private static Workbook wb;

    public static void exportToExcelProductsList(String fileName, List<Product> products) {
        try {
            OutputStream os = new FileOutputStream(new File(fileName));
            wb = new Workbook(os, "Products List", "1.0");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Worksheet ws = wb.newWorksheet("Products");
            ws.range(0, 0, 0, 6).style()
                .horizontalAlignment("center").fontName("Arial").fontSize(12)
                .bold().fontColor("ffffff").fillColor("3366FF").set();
            ws.value(0, 0, "Id");
            ws.value(0, 1, "Name");
            ws.value(0, 2, "Price");
            ws.value(0, 3, "Description");
            ws.value(0, 4, "Category");
            ws.value(0, 5, "Images");
            for (int i = 0; i < products.size(); i++) {
                ws.value(i + 1, 0, products.get(i).getId());
                ws.value(i + 1, 1, products.get(i).getTitle());
                ws.value(i + 1, 2, products.get(i).getPrice());
                ws.value(i + 1, 3, products.get(i).getDescription());
                ws.value(i + 1, 4, products.get(i).getCategory().getName());
                ws.value(i + 1, 5, products.get(i)
                    .getImages().stream()
                        .reduce("", (acc, item) -> acc += item + "\r\n"));
            }
            ws.range(1, 5, products.size(), 5).style().wrapText(true).set();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void exportToExcelProductDetail(Product product) {
        try {
            Worksheet ws = wb.newWorksheet("Product Detail");
            ws.range(0, 0, 5, 0).style()
                .horizontalAlignment("center").fontName("Arial").fontSize(12)
                .bold().fontColor("ffffff").fillColor("3366FF").set();

            ws.range(3, 1, 3, 1).style().wrapText(true).set();
            ws.range(5, 1, 5, 1).style().wrapText(true).set();

            ws.value(0, 0, "Id");
            ws.value(0, 1, product.getId());
            ws.value(1, 0, "Name");
            ws.value(1, 1, product.getTitle());
            ws.value(2, 0, "Price");
            ws.value(2, 1, product.getPrice());
            ws.value(3, 0, "Description");
            ws.value(3, 1, product.getDescription());
            ws.value(4, 0, "Category");
            ws.value(4, 1, product.getCategory().getName());
            ws.value(5, 0, "Images");
            ws.value(5, 1, product.getImages().stream()
                .reduce("", (acc, item) -> acc += item + "\r\n"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveToFile() {
        try {
            wb.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
