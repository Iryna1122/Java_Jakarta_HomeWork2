package com.example.java_jakarta_homework2;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name="task5_Servlet", value ="/task5")
public class Task5Servlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = "jdbc:postgresql://localhost:5432/Cars";
        String username = "postgres";
        String password = "postgres";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


            PrintWriter out = response.getWriter();
            response.setContentType("text/html; charset=UTF-8");

            out.println("<html><body>");
            out.println("<h3>Додавання нового автомобіля</h3>");
            out.println("<form action='task5' method='post'>");
            out.println("Назва фірми виробника: <input type='text' name='manufacturer'><br>");
            out.println("Назва авто: <input type='text' name='carname'><br>");
            out.println("Рік: <input type='number' name='year'><br>");
            out.println("Колір: <input type='text' name='color'><br>");
            out.println("Тип: <input type='text' name='type'><br>");

            out.println("<input type='submit' value='Додати'>");
            out.println("</form>");
            out.println("</html></body>");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = "jdbc:postgresql://localhost:5432/Cars";
        String username = "postgres";
        String password = "postgres";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String manuf = request.getParameter("manufacturer");
            String name = request.getParameter("carname");
            int year = Integer.parseInt(request.getParameter("year"));
            String color = request.getParameter("color");
            String type = request.getParameter("type");


            String insertCommand = "INSERT INTO Car (manufacturer_name, car_name, yearprod, color, typec) " +
                    "VALUES (?, ?, ?, ?, ?)";

            try {
                PreparedStatement preparedStatement = null;
                try {
                    preparedStatement = conn.prepareStatement(insertCommand);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                preparedStatement.setString(1, manuf);
                preparedStatement.setString(2, name);
                preparedStatement.setInt(3, year);
                preparedStatement.setString(4, color);
                preparedStatement.setString(5, type);


                preparedStatement.executeUpdate();
//DELETE
                String deleteCommand = "DELETE FROM Car WHERE yearprod = 2020";

                Statement statement = conn.createStatement();
                statement.executeUpdate(deleteCommand);
//UPDATE
                String updateCommand = "UPDATE Car SET color = 'Red' WHERE yearprod = 2022";

                Statement statement2 = conn.createStatement();
                statement2.executeUpdate(updateCommand);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}