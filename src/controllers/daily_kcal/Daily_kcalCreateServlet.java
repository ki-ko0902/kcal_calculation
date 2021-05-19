package controllers.daily_kcal;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
import models.validators.Daily_kcalValidator;
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
            Integer yyyy = Integer.parseInt(sdf.format(d.getDate()));
            d.setYear(yyyy);

            SimpleDateFormat sdf2 = new SimpleDateFormat("mm");
            Integer mm = Integer.parseInt(sdf2.format(d.getDate()));
            d.setMonth(mm);

            d.setKcal(Integer.parseInt(request.getParameter("kcal")));
            int kcal = Integer.parseInt(request.getParameter("kcal"));

            d.setTodays_weight(Double.parseDouble(request.getParameter("todays_weight")));


            // Personal_dataのtarget_kcalを取得
            Personal_data p = (Personal_data) request.getSession().getAttribute("login_personal_data");
             int target_kcal = p.getTarget_kcal();

            int bmr_difference = kcal - target_kcal;
            d.setBmr_difference(bmr_difference);

            List<String> errors = Daily_kcalValidator.validate(d,target_kcal);
            if (errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("daily_kcal", d);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/daily/new.jsp");
                rd.forward(request, response);
            } else {

                em.getTransaction().begin();
                em.persist(d);
                em.getTransaction().commit();
                em.close();

                request.getSession().removeAttribute("target_kcal");

                response.sendRedirect(request.getContextPath() + "/daily/index");
            }
        }
    }
}
