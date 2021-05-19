package controllers.personal_data;

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
 * Servlet implementation class Personal_dataIndexServlet
 */
@WebServlet("/personal/index")
public class Personal_dataIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Personal_dataIndexServlet() {
        super();

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Personal_data p = (Personal_data)request.getSession().getAttribute("login_personal_data");
        int id = p.getId();


       List<Personal_data> personal_data = em.createNamedQuery("getAllPersonal_data", Personal_data.class)
               .setParameter("id",id) .getResultList();



      List<Daily_kcal> daily_kcal = em.createNamedQuery("getAllDaily_kcal", Daily_kcal.class)
               .setParameter("personal_data",p).getResultList();



        em.close();

        request.setAttribute("personal_data", personal_data);
        request.setAttribute("daily_kcal", daily_kcal);

        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/daily_kcal/index.jsp");
        rd.forward(request, response);
    }

}
