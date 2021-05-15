package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Daily_kcal;

public class Daily_kcalValidator {
    public static List<String> validate(Daily_kcal d,int target_kcal) {
        List<String> errors = new ArrayList<String>();

        String kcal_error = _validateTitle(d.getKcal());
        if(!kcal_error.equals("")) {
            errors.add(kcal_error);
        }

        String todays_weight_error = _validateContent(d.getTodays_weight());
        if(!todays_weight_error.equals("")) {
            errors.add(todays_weight_error);
        }

        String target_kcal_error = _validateContent(target_kcal);
        if(!target_kcal_error.equals("")) {
            errors.add(target_kcal_error);
        }



        return errors;
    }

    private static String _validateTitle(Integer kcal) {
        if(kcal == null || kcal.equals("")) {
            return "kcalを入力してください。";
            }

        return "";
    }

    private static String _validateContent(Double todays_weight) {
        if(todays_weight == null || todays_weight.equals("")) {
            return "今日の体重を入力してください。";
            }

        return "";
    }

    private static String _validateContent(Integer target_kcal) {
        if(target_kcal == null || target_kcal.equals("")) {
            return "１日の目標kcalが未設定です";
            }

        return "";
    }
}


