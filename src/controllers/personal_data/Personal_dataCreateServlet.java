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
            p.setHeight(Double.parseDouble(request.getParameter("height")));
            p.setWeight(Double.parseDouble(request.getParameter("weight")));
            p.setAge(Integer.parseInt(request.getParameter("age")));
            p.setTarget_weight(Double.parseDouble(request.getParameter("target_weight")));
            Integer.parseInt(request.getParameter("momentum"));
            Double.parseDouble(request.getParameter("month"));

            if (request.getParameter("gender").equals("男性")) {

                 // 基礎代謝量 （BMR）
                // bmr(男性)： 13.397×体重kg＋4.799×身長cm−5.677×年齢+88.362
                double bmr = (13.397 * Double.parseDouble(request.getParameter("weight")))
                        + (4.799 * Double.parseDouble(request.getParameter("height")))
                        - (5.677 * Integer.parseInt(request.getParameter("age"))) + 88.362;

                // BMR*運動量(momentum)＝一日の消費量(tdee)
                double tdee = bmr * Integer.parseInt(request.getParameter("momentum"));

                // 脂肪1kgを消費するのに必要なカロリーは 7200kcal
                // 1日の目標摂取kcal(target_kcal) = tdee - (現在の体重 - 目標体重)*7200 / (月*30)
                double kcal = tdee - (Double.parseDouble(request.getParameter("weight")))
                        - (Double.parseDouble(request.getParameter("target_weight")))
                                * (7200 / (Integer.parseInt(request.getParameter("month")) * 30));
                int target_kcal = (int) kcal;

                // ※ 1日の摂取カロリー (target_kcal) > BMR

                if (target_kcal < bmr) {

                    em.close();

                    request.setAttribute("_token", request.getSession().getId());
                    request.setAttribute("personal_data", p);

                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/personal/new.jsp");
                    rd.forward(request, response);

                } else {

                    p.setTarget_kcal(target_kcal);

                    em.getTransaction().begin();
                    em.persist(p);
                    em.getTransaction().commit();
                    em.close();

                    response.sendRedirect(request.getContextPath() + "/daily/index");

                }

            } else {
                // bmr(女性)： (9.247 * 体重kg) + 3.098 * 身長cm − 4.33 * 年齢 + 447.593
                double bmr = 9.247 * Double.parseDouble((request.getParameter("weight")))
                        + (3.098 * Double.parseDouble(request.getParameter("height")))
                        - (4.33 * Integer.parseInt(request.getParameter("age"))) + 447.593;

                double tdee = bmr * Integer.parseInt(request.getParameter("momentum"));

                double kcal = tdee - (Double.parseDouble(request.getParameter("weight")))
                        - (Double.parseDouble(request.getParameter("target_weight")))
                                * (7200 / (Integer.parseInt(request.getParameter("month")) * 30));

                int target_kcal = (int) kcal;

                if (target_kcal < bmr) {

                    em.close();

                    request.setAttribute("_token", request.getSession().getId());
                    request.setAttribute("personal_data", p);

                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/personal/new.jsp");
                    rd.forward(request, response);

                } else {

                    p.setTarget_kcal(target_kcal);

                    em.getTransaction().begin();
                    em.persist(p);
                    em.getTransaction().commit();
                    em.close();

                    response.sendRedirect(request.getContextPath() + "/daily/index");

                }

            }

        }
    }

}
