package controllers.daily_kcal;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Daily_kcal;
import models.Personal_data;
import utils.DBUtil;

/**
 * Servlet implementation class Daily_kcalCreateServlet
 */
@WebServlet("/daily/create")
public class Daily_kcalCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Daily_kcalCreateServlet() {
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

            Daily_kcal d = new Daily_kcal();

            Date date = new Date(System.currentTimeMillis());
            String d_str = request.getParameter("date");
            if (d_str != null && !d_str.equals("")) {
                date = Date.valueOf(request.getParameter("date"));
            }
            d.setDate(date);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            Date yyyy = Date.valueOf(sdf.format(d.getDate()));
            d.setYear(yyyy);

            SimpleDateFormat sdf2 = new SimpleDateFormat("mm");
            Date mm = Date.valueOf(sdf2.format(d.getDate()));
            d.setMonth(mm);

            d.setKcal(Integer.parseInt(request.getParameter("kcal")));
            d.setTodays_weight(Double.parseDouble(request.getParameter("todays_weight")));
            double todays_w = Double.parseDouble(request.getParameter("todays_weight"));

            Personal_data p = new Personal_data();
            double tw = p.getTarget_weight();

            double bmr_difference = todays_w - tw;

            d.setTodays_weight(bmr_difference);

            em.getTransaction().begin();
            em.persist(d);
            em.getTransaction().commit();
            em.close();

            response.sendRedirect(request.getContextPath() + "/daily/index");
        }
    }
}
