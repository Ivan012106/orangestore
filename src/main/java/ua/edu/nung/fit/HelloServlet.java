package ua.edu.nung.fit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Передаємо дані для відображення в FreeMarker
        request.setAttribute("message", "Привіт! FreeMarker працює на Tomcat 7!");

        // Перенаправляємо на шаблон
        request.getRequestDispatcher("/WEB-INF/templates/hello.ftl")
                .forward(request, response);
    }
}