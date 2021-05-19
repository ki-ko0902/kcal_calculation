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
            if (p.getName().equals(request.getParameter("name"))) {
                nameDuplicateCheckFlag = false;
            } else {
                p.setName(request.getParameter("name"));
            }

            Boolean passwordCheckFlag = true;
            String password = request.getParameter("password");
            if (password == null || password.equals("")) {
                passwordCheckFlag = false;
            } else {
                p.setPassword(EncryptUtil.getPasswordEncrypt(password,
                        (String) this.getServletContext().getAttribute("pepper")));
            }

            p.setName(request.getParameter("name"));

            p.setGender(request.getParameter("gender"));


            if(request.getParameter("height") == null || request.getParameter("height").equals("")) {
                p.setHeight(0.0);
            } else {
                p.setHeight(Double.parseDouble(request.getParameter("height")));
            }


            if(request.getParameter("weight") == null || request.getParameter("weight").equals("")) {
                p.setWeight(0.0);
            } else {
                p.setWeight(Double.parseDouble(request.getParameter("weight")));
            }


            if(request.getParameter("age") == null || request.getParameter("age").equals("")) {
                p.setAge(-1);
            } else {
                p.setAge(Integer.parseInt(request.getParameter("age")));
            }


            if(request.getParameter("target_weight") == null || request.getParameter("target_weight").equals("")) {
                p.setTarget_weight(0.0);
            } else {
                p.setTarget_weight(Double.parseDouble(request.getParameter("target_weight")));
            }


            if(request.getParameter("for_month") == null || request.getParameter("for_month").equals("")) {
                p.setFor_month(-1);
            } else {
                p.setFor_month(Integer.parseInt(request.getParameter("for_month")));
            }


            double h = p.getHeight();
            double w = p.getWeight();
            int a = p.getAge();
            double tw = p.getTarget_weight();
            double m = Double.parseDouble(request.getParameter("momentum"));
            int month = p.getFor_month();

            double bmr = getBmr(request.getParameter("gender"), w, h, a);

            // 1日の消費量 = BMR * 運動量

            double tdee = bmr * m;

            // 脂肪1kgを消費するのに必要なカロリーは 7200kcal
            // 1日の目標摂取kcal(target_kcal) = tdee - (現在の体重 - 目標体重)*7200 /
            // (月*1月を30日仮定)
            double kcal = tdee - (w - tw) * (7200 / (month * 30));
            int target_kcal = (int) kcal;

            p.setTarget_kcal(target_kcal);

            List<String> errors = Personal_dataValidator.validate(nameDuplicateCheckFlag, passwordCheckFlag, p, bmr);
            if (errors.size() > 0) {

                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("personal_data", p);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/personal_data/edit.jsp");
                rd.forward(request, response);

            } else {

                em.getTransaction().begin();
                em.persist(p);
                em.getTransaction().commit();
                em.close();


                request.getSession().setAttribute("flush", "更新が完了しました。");
                response.sendRedirect(request.getContextPath() + "/daily/index");
            }
        }
    }

    private double getBmr(String gender, double w, double h, int a) {
        double bmr = 0;
        if (gender.equals("男性")) {
            // 基礎代謝量 （BMR）
            // bmr(男性)： 13.397×体重kg＋4.799×身長cm−5.677×年齢+88.362
            bmr = (13.397 * w) + (4.799 * h) - (5.677 * a) + 88.362;
        } else {
            // bmr(女性)： (9.247 * 体重kg) + 3.098 * 身長cm − 4.33 * 年齢 + 447.593
            bmr = (9.247 * w) + (3.098 * h) - (4.33 * a) + 447.593;
        }
        return bmr;

    }
}