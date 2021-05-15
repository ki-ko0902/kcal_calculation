<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" />
            <br />
        </c:forEach>

    </div>
</c:if>

<br />
<label for="name">名前</label>
<br />
<input type="text" name="name" value="${personal_data.name}" />
<br />
<br />


<label for="password">パスワード</label>
<br />
<input type="password" name="password" />
<br />
<br />


<label> <input type="radio" name="gender" value="男性" required>
    男性
</label>
<label> <input type="radio" name="gender" value="女性"> 女性
</label>
<br />
<br />

<label>年齢 <input type="number" name="age" step="1" min="0"
    max="120"> 歳
</label>
<br />
<br />

<label>身長 <input type="number" name="height" step="0.1" min="0">
    cm
</label>
<br />
<br />

<label>体重 <input type="number" name="weight" step="0.1" min="0">
    kg
</label>
<br />
<br />
<label><strong>目標</strong> <br /> <input type="number"
    name="month" step="1" min="1">ヶ月で </label>
<br />
<label>目標体重 <input type="number" name="target_weight" step="0.1"
    min="30" max="200"> kg
</label>
<br />
<br />
<br />
<br />

<div>
    <label> <input type="radio" name="momentum" value="1.2"
        required> <strong>活動量が低い人</strong> <small>デスクワーク中心で座っていることが多く、
            １日の運動は通勤・通学や近所のお買い物程度ほぼ運動しない</small>
    </label>
</div>
<br />
<div>
    <label> <input type="radio" name="momentum" value="1.375">
        <strong>活動量がやや低い人</strong> <small>上記の活動量が低い人＋１週間に１，２回程度軽い運動や筋トレをする</small>
    </label>
</div>
<br />
<div>
    <label> <input type="radio" name="momentum" value="1.55">
        <strong>活動量が標準の人</strong> <small>営業の外回りや肉体労働で１日中よく動いている
            または１週間に２，３回程度強度の高い運動や筋トレをする</small>
    </label>
</div>
<br />
<div>
    <label> <input type="radio" name="momentum" value="1.725">
        <strong>活動量が高い人</strong> <small>上記の標準の人＋１週間に４，５回程度強度の高い運動や筋トレをする</small>
    </label>
</div>
<br />
<div>
    <label> <input type="radio" name="momentum" value="1.9">
        <strong>活動量がかなり高い人</strong> <small>スポーツ選手・アスリート</small>
    </label>
</div>
<br />
<br />
<br />
<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>