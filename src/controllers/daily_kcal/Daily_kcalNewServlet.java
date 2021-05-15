package controllers.daily_kcal;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Daily_kcal;

/**
 * Servlet implementation class Daily_kcalNewServlet
 */
@WebServlet("/daily/new")
public class Daily_kcalNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Daily_kcalNewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());

        Daily_kcal d = new Daily_kcal();
        d.setDate(new Date(System.currentTimeMillis()));
        request.setAttribute("daily_kcal", d);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/daily_kcal/new.jsp");
        rd.forward(request, response);
    }


}
