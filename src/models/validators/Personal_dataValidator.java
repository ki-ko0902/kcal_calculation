package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Personal_data;

public class Personal_dataValidator {
    // バリデーションを実行する
    public static List<String> validate(Personal_data p) {
        List<String> errors = new ArrayList<String>();

        String target_kcal_error = validateTarget_kcal(p.getTarget_kcal());
        if(!target_kcal_error.equals("")) {
            errors.add(target_kcal_error);
        }


        return errors;
    }


    private static String validateTarget_kcal(Integer target_kcal) {
        if(target_kcal.equals("")) {
            return "日々の目標摂取kcalが基礎代謝を下回り危険です。"
                    + "目標期間を長くするか、目標体重を増やしてください。" ;
        }

        return "";
    }



}