<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../layout/header.jsp" %>
<div class="container">
    <div class="justify-content-center">
        <c:forEach var="board" items="${boards}">
            <div class="card m-3">
                <div class="card-body">
                    <h4 class="card-title">${board.title}</h4>
                    <a href="/board/${board.id}" class="btn btn-dark">상세보기</a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<%@ include file="../layout/footer.jsp" %>
