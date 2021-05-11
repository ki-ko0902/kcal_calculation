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
 * Servlet implementation class Personal_dataUpdateServlet
 */
@WebServlet("/personal/update")
public class Personal_dataUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Personal_dataUpdateServlet() {
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

            Personal_data p = em.find(Personal_data.class,
                    (Integer) (request.getSession().getAttribute("personal_data_id")));

            p.setGender(request.getParameter("gender"));
            p.setHeight(Double.parseDouble(request.getParameter("height")));
            p.setWeight(Double.parseDouble(request.getParameter("weight")));
            p.setAge(Integer.parseInt(request.getParameter("age")));

            // p.setTarget_kcal(Integer.parseInt(request.getParameter("target_kcal")));
            int target_kcal = 1800;
            p.setTarget_kcal(target_kcal);

            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();

            request.getSession().removeAttribute("personal_data_id");
            response.sendRedirect(request.getContextPath() + "/daily/index");

        }
    }
}
