package com.example.java_jakarta_homework2;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "task2Servlet", value = "/task2-servlet")
public class Task2Servlet extends HttpServlet {

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
            out.println("<h3>Відображення всього вмісту таблиці з автомобілями</h3>");
            response.setContentType("text/html; charset=UTF-8");
            String command = "SELECT * FROM Car";
            Statement statement = conn.createStatement();
            ResultSet set = statement.executeQuery(command);
            out.println("<html><body>");

            while(set.next())
            {
                int id = set.getInt("id");
                String manufacturer_name = set.getString("manufacturer_name");
                String car_name = set.getString("car_name");
                int year = set.getInt("yearprod");
                String color = set.getString("color");
                String manufacturer_country = set.getString("manufacturer_country");
                String type = set.getString("typec");

                out.println("<p>ID: " + id + "</p>");
                out.println("<p>Назва виробника автомобіля: " + manufacturer_country + "</p>");
                out.println("<p>Назва автомобіля: " +car_name + "</p>");
                out.println("<p>Рік випуску: " + year + "</p>");
                out.println("<p>Колір автомобіля: " + color + "</p>");
                out.println("<p>Тип автомобіля:: " + type+ "</p>");

            }

            out.println("</html></body>");

            out.println("<h3>Показати всіх виробників автомобілів</h3>");
            String command2 = "SELECT manufacturer_name FROM Car";
            Statement statement2 = conn.createStatement();
            ResultSet set2 = statement2.executeQuery(command2);
            out.println("<html><body>");

            while(set2.next())
            {
                String manufacturer_name = set2.getString("manufacturer_name");
                out.println("<p>Назва виробника автомобіля: " + manufacturer_name + "</p>");
            }

            out.println("</html></body>");

            out.println("<h3>Показати назву виробника і кількість автомобілів кожного виробника</h3>");
            String command3 = "SELECT manufacturer_name, COUNT(*) AS countCar FROM Car GROUP BY manufacturer_name";
            Statement statement3 = conn.createStatement();
            ResultSet set3 = statement3.executeQuery(command3);
            out.println("<html><body>");
            while(set3.next())
            {
                String name = set3.getString("manufacturer_name");
                int count = set3.getInt("countCar");


                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th>Manufacturer Name</th>");
                out.println("<th>Count Cars</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + count + "</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<br>");
            }


            out.println("</html></body>");
//            conn.close();
//            set3.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
