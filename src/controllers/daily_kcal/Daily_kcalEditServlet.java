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
import models.Personal_data;
import utils.DBUtil;

/**
 * Servlet implementation class Daily_kcalEditServlet
 */
@WebServlet("/daily/edit")
public class Daily_kcalEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Daily_kcalEditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Daily_kcal d = em.find(Daily_kcal.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        Personal_data login_personal_data = (Personal_data) request.getSession().getAttribute("login_personal_data");
        if (d != null && login_personal_data.getId() == d.getPersonal_data().getId()) {
            request.setAttribute("daily_kcal", d);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("daily_kcal_id", d.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/daily_kcal/edit.jsp");
        rd.forward(request, response);
    }

}
