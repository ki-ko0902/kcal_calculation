package controllers.personal_data;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        List<Personal_data> personal_data = em.createNamedQuery("getAllPersonal_data", Personal_data.class).getResultList();
        response.getWriter().append(Integer.valueOf(personal_data.size()).toString());

        em.close();
    }

}
