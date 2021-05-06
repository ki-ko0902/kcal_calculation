<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<label> <input type="radio" name="gender" value="male" required>
    男性
</label>
<label> <input type="radio" name="gender" value="female">
    女性
</label>
<br />
<br />

<label>年齢 <input type="number" name="age" step="1" min="0"max="120"> 歳

</label>
<br />
<br />

<label>身長 <input type="number" name="height" step="0.1"min="120" max="220"> cm
</label>
<br />
<br />

<label>体重 <input type="number" name="weight" step="0.1" min="30"
    max="200"> kg
</label>
<br />
<br />



<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>