 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${personal_data != null}">
                <h2>登録内容</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>性別</th>
                            <td><c:out value="${personal_data.gender}" /></td>
                        </tr>
                        <tr>
                            <th>年齢</th>
                            <td><c:out value="${personal_data.age}" /></td>
                        </tr>
                        <tr>
                            <th>身長(cm)</th>
                            <td><c:out value="${personal_data.height}" /></td>
                        </tr>
                        <tr>
                            <th>体重(kg)</th>
                            <td><c:out value="${personal_data.weight}" /></td>
                        </tr>
                        <tr>
                            <th>目標摂取kcal</th>
                            <td><c:out value="${personal_data.target_kcal}" /></td>
                        </tr>
                    </tbody>
                </table>

                <p><a href="<c:url value='/personal/edit?id=${personal_data.id}' />">編集する</a></p>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/daily/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>