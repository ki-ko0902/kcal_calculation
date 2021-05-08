package controllers.personal_data;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Personal_data;
import utils.DBUtil;

/**
 * Servlet implementation class Personal_dataEditServlet
 */
@WebServlet("/personal/edit")
public class Personal_dataEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Personal_dataEditServlet() {
        super();

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Personal_data p = em.find(Personal_data.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("personal_data", p);
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("personal_data_id", p.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/personal_data/edit.jsp");
        rd.forward(request, response);
    }
}
