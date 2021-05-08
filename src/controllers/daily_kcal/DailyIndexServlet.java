package controllers.daily_kcal;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class DailyIndexServlet
 */
@WebServlet("/daily/index")
public class DailyIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DailyIndexServlet() {
        super();

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        List<Personal_data> personal_data = em.createNamedQuery("getAllPersonal_data", Personal_data.class).getResultList();
        response.getWriter().append(Integer.valueOf( personal_data.size()).toString());

       List<Daily_kcal> daily_kcal = em.createNamedQuery("getAllDaily_kcal", Daily_kcal.class).getResultList();
        response.getWriter().append(Integer.valueOf(daily_kcal.size()).toString());

        em.close();

        request.setAttribute("personal_data", personal_data);
        request.setAttribute("daily_kcal", daily_kcal);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/daily_kcal/index.jsp");
        rd.forward(request, response);
    }
}
