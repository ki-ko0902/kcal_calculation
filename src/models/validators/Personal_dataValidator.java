package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Personal_data;
import utils.DBUtil;

public class Personal_dataValidator {
    // バリデーションを実行する
    public static List<String> validate( Boolean nameDuplicateCheckFlag, Boolean passwordCheckFlag,Personal_data p, double bmr) {
        List<String> errors = new ArrayList<String>();


        String name_error = validateName(p.getName(), nameDuplicateCheckFlag);
        if(!name_error.equals("")) {
            errors.add(name_error);
        }


        String password_error = validatePassword(p.getPassword(), passwordCheckFlag);
        if(!password_error.equals("")) {
            errors.add(password_error);
        }


        String gender_error = validateGender(p.getGender());
        if (!gender_error.equals("")) {
            errors.add(gender_error);
        }

        String height_error = validateHeight(p.getHeight());
        if (!height_error.equals("")) {
            errors.add(height_error);
        }


        String weight_error = validateWeight(p.getWeight());
        if (!weight_error.equals("")) {
            errors.add(weight_error);
        }


        String age_error = validateAge(p.getAge());
        if (!age_error.equals("")) {
            errors.add(age_error);
        }


        String target_weight_error = validateTarget_weight(p.getTarget_weight());
        if (!target_weight_error.equals("")) {
            errors.add(target_weight_error);
        }

        String for_month_error = validateFor_month(p.getFor_month());
        if (!for_month_error.equals("")) {
            errors.add(for_month_error);
        }

        String target_kcal_error = validateTarget_kcal(p.getTarget_kcal(),bmr, p.getTarget_weight());
        if (!target_kcal_error.equals("")) {
            errors.add(target_kcal_error);
        }

        return errors;
    }

    private static String validateName(String name, Boolean nameDuplicateCheckFlag) {
        if(name == null || name.equals("")) {
            return "ニックネームを入力してください。";
        }

        if(nameDuplicateCheckFlag) {
            EntityManager em = DBUtil.createEntityManager();
            long personal_data_count = (long)em.createNamedQuery("checkRegisteredName", Long.class).setParameter("name", name).getSingleResult();
            em.close();
            if(personal_data_count > 0) {
                return "入力されたニックネームはすでに使用されています。";
            }
        }

        return "";
    }

    private static String validatePassword(String password, Boolean passwordCheckFlag) {
        if(passwordCheckFlag && (password == null || password.equals(""))) {
            return "パスワードを入力してください。";
        }
        return "";
    }



     private static String validateGender(String gender) {
            if(gender == null || gender.equals("")) {
                return "性別を選んでください";
            }

            return "";
        }

     private static String validateHeight(double height) {
         if(height == 0) {
             return "身長を入力してください";
         }

         return "";
     }

          private static String validateWeight(double weight) {
         if(weight == 0) {
             return "体重を入力してください";
         }

         return "";
     }

          private static String validateAge(Integer age) {
              if(age < 0 ) {
                  return "年齢を入力してください";
              }

              return "";
          }


          private static String validateTarget_weight(double target_weight) {
              if(target_weight == 0) {
                  return "目標体重を入力してください";
              }

              return "";
          }

          private static String validateFor_month(Integer for_month) {
              if(for_month < 0) {
                  return "目標期間を入力してください";
              }

              return "";
          }

     private static String validateTarget_kcal(Integer target_kcal, double bmr, double target_weight) {
        if (target_kcal < bmr  && target_weight > 0) {
            return "日々の目標摂取kcalが基礎代謝を下回り危険です。" + "目標期間を長くするか、目標体重を増やしてください。";
        }

        return "";
    }

}