package controllers.daily_kcal;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Daily_kcal;
import utils.DBUtil;

/**
 * Servlet implementation class Daily_kcalShowServlet
 */
@WebServlet("/daily/show")
public class Daily_kcalShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Daily_kcalShowServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         EntityManager em = DBUtil.createEntityManager();

         Daily_kcal d = em.find(Daily_kcal.class, Integer.parseInt(request.getParameter("id")));

            em.close();

            request.setAttribute("daily_kcal", d);
            request.setAttribute("_token", request.getSession().getId());

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/daily/show.jsp");
            rd.forward(request, response);
        }
    }
