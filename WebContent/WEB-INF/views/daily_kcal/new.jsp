<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>入力ページ</h2>

        <form method="POST" action="<c:url value='/daily/create' />">
            <c:import url="_form.jsp" />
        </form>

        <p><a href="<c:url value='/daily/index' />">一覧</a></p>

         <c:import url="../layout/kcal_example.jsp">
         </c:import>

    </c:param>
     </c:import>