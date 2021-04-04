package org.example.servlets;
import org.example.classes.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AddProduct") //для всех запросов. Или же, если хотим, отдельно задать "/HttpServlet"

public class AddProductServlet extends javax.servlet.http.HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(AddProductServlet.class);
    private List<Product> productList;

    @Override
    public void init() throws ServletException {
        productList = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().printf("<h3>Форма для добавления продукта</h3>");
        response.getWriter().printf("<form method='post'>");
        //Немного костылей с пикселями в margin-left
        response.getWriter().printf("<p>Наименование продукта: <input style=\"margin-left: 10px;\" type='text' name='productName'>");
        response.getWriter().printf("<p>Стоимость продукта: <input style=\"margin-left: 36px;\" type='text' name='productCost'>");
        response.getWriter().printf("<p><input style=\"margin-left: 279px;\" type='submit'>");
        response.getWriter().printf("</form>");

        String productName = request.getParameter("productName");
        String productCost = request.getParameter("productCost");

        //Если наименование не пустое и стоимость содержит только цифры, то добавляем продукт в ArrayList
        if (productName != null && !productName.equals("") && productCost.matches("[0-9]+")) {
            productList.add(new Product(productName, Integer.parseInt(productCost)));
            logger.info("Product added to list: {}", productList.get(productList.size()-1).toString());
        }

        for (Product product : productList) {
            if (product != null) {
                response.getWriter().printf("<p>" + product.toString());
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("POST request");
        doGet(request, response);
    }
}
