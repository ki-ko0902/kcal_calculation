<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${hasError}">
            <div id="flush_error">ニックネームかパスワードが間違っています。</div>
        </c:if>
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

        <h2>ログイン</h2>
        <form method="POST" action="<c:url value='/login' />" id=login>
            <label for="name">ニックネーム</label><br /> <input type="text"
                name="name" value="${name}" /> <br />
            <br /> <label for="password">パスワード</label><br /> <input
                type="password" name="password" /> <br />
            <br /> <input type="hidden" name="_token" value="${_token}" />
            <button type="submit">ログイン</button>
        </form>

        <div id="Instructions">
            <p>身長、体重等と、目標期間と目標体重、生活スタイルを設定すれば、</p>
            <p>日々どれくらいのkcal摂取のみで設定した目標期間で</p>
            <p>目標体重を達成できるか自動で計算できるアプリです</P>
            <P>アカウントを作成しログインすると進捗管理もできます。</P>
        </div>

        <br />
        <br />
        <p>
            <a href="<c:url value='/personal/new' />">新規登録</a>
        </p>

    </c:param>




</c:import>