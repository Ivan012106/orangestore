package ua.edu.nung.fit;

import java.io.IOException;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users-dao")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        try {
            // Отримання DataSource з JNDI (вимога ЛР №3)
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myapp");
            userDAO = new UserDAO(ds);
        } catch (NamingException e) {
            throw new ServletException("Помилка ініціалізації DataSource", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 1. Отримуємо дані з бази через DAO
            List<User> users = userDAO.getAllUsers();

            // 2. Встановлюємо атрибути ПЕРЕД викликом forward
            request.setAttribute("message", "Дані з DAO успішно отримано!");
            request.setAttribute("users", users);

            // 3. Відправляємо дані в шаблон (правильний шлях з вашого дерева файлів)
            request.getRequestDispatcher("/WEB-INF/templates/hello.ftl").forward(request, response);

        } catch (Exception e) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h2>Помилка в UserServlet: " + e.getMessage() + "</h2>");
            e.printStackTrace(response.getWriter());
        }
    }
}