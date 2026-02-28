package ua.edu.nung.fit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users-jdbc")
public class JdbcServlet extends HttpServlet {
    // Налаштування підключення згідно зі Слайдом 4
    private static final String URL = "jdbc:mysql://localhost:3306/myapp?serverTimezone=UTC";
    private static final String USER = "myuser";
    private static final String PASSWORD = "mypassword";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><body><h2>Список користувачів (JDBC)</h2>");

        try {
            // Завантаження драйвера MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 1. Встановлення підключення (DriverManager.getConnection)
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

                // 2. Створення Statement для виконання SQL-запиту
                Statement statement = connection.createStatement();

                // 3. Виконання SELECT запиту (Слайд 3)
                String sql = "SELECT * FROM users";
                ResultSet resultSet = statement.executeQuery(sql);

                out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Email</th></tr>");

                while (resultSet.next()) {
                    out.println("<tr>");
                    out.println("<td>" + resultSet.getInt("id") + "</td>");
                    out.println("<td>" + resultSet.getString("name") + "</td>");
                    out.println("<td>" + resultSet.getString("email") + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            }
        } catch (Exception e) {
            out.println("<p style='color:red'>Помилка: " + e.getMessage() + "</p>");
            e.printStackTrace(out);
        }
        out.println("</body></html>");
    }
}