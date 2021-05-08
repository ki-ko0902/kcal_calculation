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
 * Servlet implementation class Personal_dataCreateServlet
 */
@WebServlet("/personal/create")
public class Personal_dataCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Personal_dataCreateServlet() {
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
            Personal_data p = new Personal_data();

            p.setGender(request.getParameter("gender"));
            p.setHeight(Integer.parseInt(request.getParameter("height")));
            p.setWeight(Integer.parseInt(request.getParameter("weight")));
            p.setAge(Integer.parseInt(request.getParameter("age")));

          //p.setTarget_kcal(Integer.parseInt(request.getParameter("target_kcal")));
           int target_kcal = 1500;
            p.setTarget_kcal(target_kcal);



            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            em.close();

            response.sendRedirect(request.getContextPath() + "/daily/index");
        }
    }

}
