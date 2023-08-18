package com.example.java_jakarta_homework2;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

//    Назва виробника автомобіля
// Назва автомобіля
// Об’єм двигуна
// Рік випуску
// Колір автомобіля
// Тип автомобіля: седан, хетчбек, універсал

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        String url = "jdbc:postgresql://localhost:5432/Cars";
        String username = "postgres";
        String password = "postgres";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PrintWriter out = response.getWriter();
//            String postgreCommand = "CREATE TABLE Car (" +
//                    "id SERIAL PRIMARY KEY," +
//                    "manufacturer_name VARCHAR(255)," +
//                    "car_name VARCHAR(255)," +
//                    "yearProd INT," +
//                    "color VARCHAR(50)," +
//                    "manufacturer_country VARCHAR(100)," +
//                    "typeC VARCHAR(50))";
//            Statement statement = conn.createStatement();
//            statement.executeUpdate(postgreCommand);
//------------------------------------------------------------------------
            String insertDataCommand = "INSERT INTO Car (manufacturer_name, car_name, yearprod, color, manufacturer_country, typec) " +
                    "VALUES\n" +
                    "('Manufacturer1', 'Car1', 2019, 'Yellow', 'Germany', 'Sedan')," +
                    "('Manufacturer2', 'Car2', 2020, 'Blue', 'Germany', 'Hatchback')," +
                    "('Manufacturer3', 'Car3', 2022, 'White', 'Ukraine', 'Universal')," +
                    "('Manufacturer4', 'Car14', 2021, 'Navy', 'Germany', 'Sedan')," +
                    "('Manufacturer5', 'Car15', 2023, 'Black', 'Germany', 'Hatchback'),"+
                    "('Manufacturer5', 'Car15', 2023, 'Black', 'Italy', 'Hatchback'),"+
                    "('Manufacturer5', 'Car16', 2023, 'Black', 'China', 'Hatchback'),"+
                    "('Manufacturer5', 'Car17', 2023, 'Black', 'Japan', 'Hatchback')";

            Statement insertDataStatement = conn.createStatement();
            insertDataStatement.executeUpdate(insertDataCommand);


            out.println("<html><body>");
            out.println("<h1>" + "Data!" + "</h1>");
            out.println("</body></html>");
            // Hello

            out.println("<html><body>");
            out.println("<h1>" + message + "</h1>");
            out.println("</body></html>");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void destroy() {
    }
}