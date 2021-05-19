package controllers.personal_data;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Personal_data;
import utils.DBUtil;

/**
 * Servlet implementation class Personal_dataDestroyServlet
 */
@WebServlet("/personal/destroy")
public class Personal_dataDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Personal_dataDestroyServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Personal_data p = em.find(Personal_data.class, (Integer)(request.getSession().getAttribute("personal_data_id")));


            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
            em.close();

            response.getWriter().append("Served at: ").append(request.getContextPath());
            request.getSession().removeAttribute("login_personal_data");
            response.sendRedirect(request.getContextPath() + "/login");
        }

    }
}
