<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" />
            <br />
        </c:forEach>

    </div>
</c:if>

<label for="date">日付</label>
<br />
<input type="date" name="date"
    value="<fmt:formatDate value='${daily_kcal.date}' pattern='yyyy-MM-dd' />" />
<br />
<br />

<label for="kcal">摂取kcal</label>
<br />
<input type="number" name="kcal" step="1" min="0">

<br />
<br />

<label for="title">今日の体重</label>
<br />
<input type="number" name="todays_weight" />
<br />
<br />
<br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>