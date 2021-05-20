package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Daily_kcal;

public class Daily_kcalValidator {
    public static List<String> validate(Daily_kcal d,int target_kcal) {
        List<String> errors = new ArrayList<String>();

        String kcal_error = _validateKcal(d.getKcal());
        if(!kcal_error.equals("")) {
            errors.add(kcal_error);
        }

        String todays_weight_error = _validateTodays_weight(d.getTodays_weight());
        if(!todays_weight_error.equals("")) {
            errors.add(todays_weight_error);
        }

        String target_kcal_error = _validatetarget_kcal(target_kcal);
        if(!target_kcal_error.equals("")) {
            errors.add(target_kcal_error);
        }



        return errors;
    }

    private static String _validateKcal(Integer kcal) {
        if(kcal < 0) {
            return "摂取kcalを入力してください。";
            }

        return "";
    }

    private static String _validateTodays_weight(double todays_weight) {
        if(todays_weight == 0.0) {
            return "今日の体重を入力してください。";
            }

        return "";
    }

    private static String _validatetarget_kcal(Integer target_kcal) {
        if(target_kcal == null || target_kcal.equals("")) {
            return "先に目標kcalの設定が必要です";
            }

        return "";
    }
}


