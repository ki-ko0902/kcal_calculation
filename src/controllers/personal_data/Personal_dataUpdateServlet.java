package controllers.personal_data;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Personal_data;
import models.validators.Personal_dataValidator;
import utils.DBUtil;
import utils.EncryptUtil;

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


            Boolean nameDuplicateCheckFlag = true;
            if(p.getName().equals(request.getParameter("name"))) {
                nameDuplicateCheckFlag = false;
            } else {
                p.setName(request.getParameter("name"));
            }

            Boolean passwordCheckFlag = true;
            String password = request.getParameter("password");
            if(password == null || password.equals("")) {
                passwordCheckFlag = false;
            } else {
                p.setPassword(
                        EncryptUtil.getPasswordEncrypt(
                                password,
                                (String)this.getServletContext().getAttribute("pepper")
                                )
                        );
            }

            p.setName(request.getParameter("name"));
            p.setDelete_flag(0);

            p.setGender(request.getParameter("gender"));
            p.setHeight(Double.parseDouble(request.getParameter("height")));
            p.setWeight(Double.parseDouble(request.getParameter("weight")));
            p.setAge(Integer.parseInt(request.getParameter("age")));
            p.setTarget_weight(Double.parseDouble(request.getParameter("target_weight")));

            double h = Double.parseDouble(request.getParameter("height"));
            double w = Double.parseDouble(request.getParameter("weight"));
            int a = Integer.parseInt(request.getParameter("age"));
            double tw = Double.parseDouble(request.getParameter("target_weight"));
            double m = Double.parseDouble(request.getParameter("momentum"));
            int month = Integer.parseInt(request.getParameter("month"));

            if (request.getParameter("gender").equals("男性")) {

                // 基礎代謝量 （BMR）
                // bmr(男性)： 13.397×体重kg＋4.799×身長cm−5.677×年齢+88.362
                double bmr = (13.397 * w) + (4.799 * h) - (5.677 * a) + 88.362;

                // BMR*運動量(momentum)＝一日の消費量(tdee)
                double tdee = bmr * m;

                // 脂肪1kgを消費するのに必要なカロリーは 7200kcal
                // 1日の目標摂取kcal(target_kcal) = tdee - (現在の体重 - 目標体重)*7200 /
                // (月*1月を30日仮定)
                double kcal = tdee - (w - tw) * (7200 / (month * 30));
                int target_kcal = (int) kcal;

                // ※ 1日の摂取カロリー (target_kcal) > BMR
                List<String> errors = Personal_dataValidator.validate(nameDuplicateCheckFlag,passwordCheckFlag,p,bmr);
                if (errors.size() > 0) {

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
                double bmr = (9.247 * w) + (3.098 * h) - (4.33 * a) + 447.593;

                double tdee = bmr * m;

                double kcal = tdee - (w - tw) * ((7200 / month) * 30);

                int target_kcal = (int) kcal;

                List<String> errors = Personal_dataValidator.validate(true, true,p,bmr);
                if (errors.size() > 0) {

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