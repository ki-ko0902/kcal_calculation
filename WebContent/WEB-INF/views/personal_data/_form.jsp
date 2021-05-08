<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<label> <input type="radio" name="gender" value="男性" required>
    男性
</label>
<label> <input type="radio" name="gender" value="女性">
    女性
</label>
<br />
<br />

<label>年齢 <input type="number" name="age" step="1" min="0"
    max="120"> 歳

</label>
<br />
<br />

<label>身長 <input type="number" name="height" step="0.1"
    min="120" max="220"> cm
</label>
<br />
<br />

<label>体重 <input type="number" name="weight" step="0.1" min="30"
    max="200"> kg
</label>
<br />
<br />


<!--
 基礎代謝量 （BMR）
     基礎代謝量(男性)： 13.397×体重kg＋4.799×身長cm−5.677×年齢+88.362
     基礎代謝量(女性)： 9.247×体重kg＋3.098×身長cm−4.33×年齢+447.593


      BMR*運動量＝一日の消費量
            ほぼ運動しない (基礎代謝 × 1.2)
            軽い運動 (基礎代謝 × 1.375)
            中程度の運動 (基礎代謝 × 1.55)
            激しい運動 (基礎代謝 × 1.725)
            非常に激しい (基礎代謝 × 1.9)

     target_kcal
        脂肪1kgを消費するのに必要なカロリー
        脂肪1gをカロリーに変換すると9kcal 脂肪1kgを消費するのに必要なカロリーは 7200kcal

        計算式（ 一日当たり)
        一日の消費量 - ( 減らしたいkg * 7200 ) / (月 * 30) =   1日の摂取カロリー (target_kcal)

     ※ 1日の摂取カロリー (target_kcal)  >  BMR
 -->


<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>