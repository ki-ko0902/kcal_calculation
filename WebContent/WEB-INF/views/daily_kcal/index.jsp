<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">

        <h2>あなたの情報</h2>
        <table id="personal_data">
            <tbody>
                <tr>
                    <th class="personal_gender">性別</th>
                    <th class="personal_age">年齢</th>
                    <th class="personal_height">身長(cm)</th>
                    <th class="personal_weight">体重(kg)</th>
                    <th class="personal_target_kcal">目標摂取kcal</th>
                    <th class="personal_target_weight">目標体重</th>
                    <th class="personal_edit">編集</th>
                </tr>
                <c:forEach var="personal_data" items="${personal_data}"
                    varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="personal_gender"><c:out
                                value="${personal_data.gender}" /></td>
                        <td class="personal_age"><c:out value="${personal_data.age}" /></td>
                        <td class="personal_height"><c:out
                                value="${personal_data.height}" /></td>
                        <td class="personal_weight"><c:out
                                value="${personal_data.weight}" /></td>
                        <td class="personal_target_kcal"><c:out
                                value="${personal_data.target_kcal}" /></td>
                        <td class="personal_target_weight"><c:out
                                value="${personal_data.target_weight}" /></td>
                        <td class="personal_edit"><a
                            href="<c:url value='/personal/show?id=${personal_data.id}' />">編集</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p><a href="<c:url value='/personal/new' />">新規登録</a></p>
        <br />
        <br />
        </body>
        <h2>日々の記録</h2>
         <p><a href="<c:url value='/daily/new' />">今日の登録</a></p>
        <table id="daily_kcal">
            <tbody>

                <tr>
                    <th class="daily_date">日付</th>
                    <th class="daily_kcal">今日の摂取kcal</th>
                    <th class="daily_bmr">目標摂取kcalとの差</th>
                    <th class="daily_weight">今日の体重</th>
                    <th class="daily_edit">編集</th>
                </tr>
                <c:forEach var="daily_kcal" items="${daily_kcal}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="daily_date"><fmt:formatDate value='${daily_kcal.date}' pattern='yyyy-MM-dd' /></td>
                        <td class="daily_kcal"><c:out value="${daily_kcal.kcal}" /></td>
                        <td class="daily_bmr"><c:out value="${daily_kcal.bmr_difference}" /></td>
                        <td class="daily_weight"><c:out value="${daily_kcal.todays_weight}" /></td>
                        <td class="daily_edit"><a href="<c:url value='/daily/show?id=${daily_kcal.id}' />">編集</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>



    </c:param>
</c:import>