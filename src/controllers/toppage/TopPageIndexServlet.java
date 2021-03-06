package controllers.toppage;

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
 * Servlet implementation class TopPageIndexServlet
 */
@WebServlet("/index.html")
public class TopPageIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopPageIndexServlet() {
        super();

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();

        Personal_data p = (Personal_data) request.getSession().getAttribute("login_personal_data");
        int id = p.getId();

        List<Personal_data> personal_data = em.createNamedQuery("getAllPersonal_data", Personal_data.class)
                .setParameter("id", id).getResultList();

        int page;

        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {
            page = 1;
        }

        long daily_kcal_count = (long) em.createNamedQuery("getDaily_kcalCount", Long.class)
                                   .setParameter("personal_data", p)
                                   .getSingleResult();

        int pageAllCount = 0;
        List<Daily_kcal> pageInfoList = null;
        List<Daily_kcal> daily_kcalList = null;

        if (daily_kcal_count > 0) {

            pageInfoList = em.createNamedQuery("getDaily_kcal_y_m", Daily_kcal.class)
                         .setParameter("personal_data", p)
                         .getResultList();

            pageAllCount = pageInfoList.size();

            request.getSession().setAttribute("page_info", pageInfoList);

            // page?????????????????????????????? ??? (page - 1)??????????????????????????????????????????0????????????????????????
            Integer year = pageInfoList.get(page - 1).getYear();
            Integer month = pageInfoList.get(page - 1).getMonth();
            daily_kcalList = em.createNamedQuery("getAllDaily_kcal", Daily_kcal.class)
                    .setParameter("personal_data", p)
                    .setParameter("year", year)
                    .setParameter("month", month)
                    .getResultList();
        }

        em.close();

        request.setAttribute("personal_data", personal_data);
        request.setAttribute("daily_kcalList", daily_kcalList);
        request.setAttribute("daily_kcal_count", daily_kcal_count);
        request.setAttribute("page_all_count", pageAllCount);
        request.setAttribute("page", page);

        if (request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
        rd.forward(request, response);
    }

}