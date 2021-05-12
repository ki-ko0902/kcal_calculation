<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${daily_kcal != null}">
                <h2>詳細ページ</h2>

                <table>
                    <tbody>
                                                <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${daily_kcal.date}"
                                    pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>今日の摂取kcal</th>
                            <td><c:out value="${daily_kcal.kcal}" /></td>
                        </tr>
                        <tr>
                            <th>目標摂取kcalとの差</th>
                            <td><c:out value="${daily_kcal.bmr_difference}" /></td>
                        </tr>
                        <tr>
                            <th>今日の体重</th>
                            <td><c:out value="${daily_kcal.todays_weight}" /></td>
                        </tr>
                    </tbody>
                </table>
                <p>
                    <a href="<c:url value="/daily/edit?id=${report.id}" />">編集する</a>
                </p>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p>
            <a href="<c:url value="/daily/index" />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>