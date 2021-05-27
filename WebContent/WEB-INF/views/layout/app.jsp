<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>kcal計算とkcal管理</title>
<link rel="stylesheet" href="<c:url value='/css/reset.css' />">
<link rel="stylesheet" href="<c:url value='/css/style.css' />">
<meta name="description"
    content="体重等と、目標期間と目標体重、生活スタイルを設定すれば、
               日々どれくらいのkcal摂取のみで目標体重を達成できるか自動で計算します。kcal計算以外に進捗の登録もできます。">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <div id="header_menu">
                <h1>
                    <a href="<c:url value='/' />">kcal計算</a>
                </h1>
                &nbsp;&nbsp;&nbsp;
                <c:if test="${sessionScope.login_personal_data != null}">
                    <a href="<c:url value='/daily/index' />">一覧</a>&nbsp;
                    </c:if>
            </div>
            <c:if test="${sessionScope.login_personal_data != null}">
                <div id="personal_data_name">
                    <c:out value="${sessionScope.login_personal_data.name}" />
                    &nbsp;さん&nbsp;&nbsp;&nbsp; <a href="<c:url value='/logout' />">ログアウト</a>
                </div>
            </c:if>
        </div>
        <div id="content">${param.content}</div>
        <div id="footer">by ki.</div>
    </div>
</body>
</html>