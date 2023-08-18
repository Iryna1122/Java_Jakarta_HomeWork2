package com.example.java_jakarta_homework2;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "task3Servlet", value = "/task3-servlet")
public class Task3Servlet extends HttpServlet {

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
            out.println("<html><body>");
            out.println("<h3>Показати виробника з найбільшою кількістю автомобілів</h3>");
            response.setContentType("text/html; charset=UTF-8");
            String command = "SELECT manufacturer_name, COUNT(*) AS countCar FROM Car GROUP BY manufacturer_name" +
                    " ORDER BY countCar DESC LIMIT 1";
            Statement statement = conn.createStatement();
            ResultSet set = statement.executeQuery(command);

            while (set.next()) {

                String name = set.getString("manufacturer_name");
                int count = set.getInt("countCar");

                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th>Виробник з найбільшою кількістю автомобілів:</th>");
                out.println("<th>Count Cars</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + count + "</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<br>");
            }


            out.println("<h3>Показати виробника з найменшою кількістю автомобілів</h3>");
            response.setContentType("text/html; charset=UTF-8");
            String command2 = "SELECT manufacturer_name, COUNT(*) AS countCar FROM Car GROUP BY manufacturer_name" +
                    " ORDER BY countCar ASC LIMIT 1";
            Statement statement2 = conn.createStatement();
            ResultSet set2 = statement2.executeQuery(command2);


            while (set2.next()) {

                String name = set2.getString("manufacturer_name");
                int count = set2.getInt("countCar");

                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th>Виробник з найменшою кількістю автомобілів:</th>");
                out.println("<th>Count Cars</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + count + "</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<br>");
            }


            out.println("<h3>Показати всі автомобілі 2023 року випуску</h3>");
            String command3 = "SELECT car_name,yearprod FROM Car WHERE yearprod = 2023";
            Statement statement3 = conn.createStatement();
            ResultSet set3 = statement3.executeQuery(command3);

            while (set3.next()) {
                String name = set3.getString("car_name");
                int year = set3.getInt("yearprod");

                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th>всі автомобілі 2023 року випуску:</th>");
                out.println("<th>Year</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + year + "</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<br>");

            }

            out.println("<h3>Показати всі автомобілі 2022 - 2023 роком випуску</h3>");
            String command4 = "SELECT car_name,yearprod FROM Car WHERE yearprod between 2022 AND 2023";

            Statement statement4 = conn.createStatement();
            ResultSet set4 = statement4.executeQuery(command4);

            while (set4.next()) {
                String name = set4.getString("car_name");
                int year = set4.getInt("yearprod");

                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th>всі автомобілі 2022 - 2023 роком випуску:</th>");
                out.println("<th>Year</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + year + "</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<br>");

            }

            out.println("<h3>5. Показати всі автомобілі Manufacturer1</h3>");
            String command5 = "SELECT car_name FROM Car WHERE manufacturer_name = 'Manufacturer1' ";
            Statement stat = conn.createStatement();
            ResultSet set5 = stat.executeQuery(command5);

            while (set5.next()) {
                String name = set5.getString("car_name");
                out.println("<p>всі автомобілі Manufacturer1:" + name + "</p>");
            }

            out.println("<h3>6. Показати всі автомобілі жовтого кольору</h3>");

            String com6 = "SELECT car_name FROM Car WHERE color = 'Yellow'";
            Statement stat6 = conn.createStatement();
            ResultSet set6 = stat6.executeQuery(com6);
            while(set6.next())
            {
                String name = set6.getString("car_name");
                out.println("<p>"+ name+"</p>");
            }


            out.println("<h3>7. Створити фільтр по типу автомобіля</h3>");
            String comm7 ="SELECT car_name, typec FROM Car";
            Statement stat7= conn.createStatement();
            ResultSet set7 = stat7.executeQuery(comm7);

            while(set7.next())
            {
                String name = set7.getString("car_name");
                String type = set7.getString("typec");

                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th>Title:</th>");
                out.println("<th>Type</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + type + "</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<br>");
            }

            out.println("</html></body>");
            out.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}