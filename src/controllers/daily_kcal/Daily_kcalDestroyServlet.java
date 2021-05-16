package controllers.daily_kcal;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Daily_kcal;
import utils.DBUtil;

/**
 * Servlet implementation class Daily_kcalDestroyServlet
 */
@WebServlet("/daily/destroy")
public class Daily_kcalDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Daily_kcalDestroyServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String _token = request.getParameter("_token");
            if(_token != null && _token.equals(request.getSession().getId())) {
                EntityManager em = DBUtil.createEntityManager();

                Daily_kcal d = em.find(Daily_kcal.class, (Integer)(request.getSession().getAttribute("daily_kcal_id")));

                em.getTransaction().begin();
                em.remove(d);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "削除が完了しました。");

                request.getSession().removeAttribute("daily_kcal_id");

                response.sendRedirect(request.getContextPath() + "/employees/index");
            }
        }

    }