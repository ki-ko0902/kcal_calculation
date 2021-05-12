package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Personal_data;

public class Personal_dataValidator {
    // バリデーションを実行する
    public static List<String> validate(Personal_data p) {
        List<String> errors = new ArrayList<String>();

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


        /*String target_kcal_error = validateTarget_kcal(p.getTarget_kcal(), null);
        if (!target_kcal_error.equals("")) {
            errors.add(target_kcal_error);
        }*/

        return errors;
    }

     private static String validateGender(String gender) {
            if(gender == null || gender.equals("")) {
                return "性別を選んでください";
            }

            return "";
        }

     private static String validateHeight(Double height) {
         if(height == null || height.equals("")) {
             return "身長を入力してください";
         }

         return "";
     }

          private static String validateWeight(Double weight) {
         if(weight == null || weight.equals("")) {
             return "体重を入力してください";
         }

         return "";
     }

          private static String validateAge(Integer age) {
              if(age == null || age.equals("")) {
                  return "体重を入力してください";
              }

              return "";
          }


          private static String validateTarget_weight(Double target_weight) {
              if(target_weight == null || target_weight.equals("")) {
                  return "目標体重を入力してください";
              }

              return "";
          }
    /*private static String validateTarget_kcal(Integer target_kcal, Double bmr) {
        if (target_kcal < bmr) {
            return "日々の目標摂取kcalが基礎代謝を下回り危険です。" + "目標期間を長くするか、目標体重を増やしてください。";
        }

        return "";
    }*/

}