<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="layout/header.jsp" %>

<div class="container">
    <c:forEach var="board" items="${boards}">
        <div class="card m-3">
            <div class="card-body">
                <h4 class="card-title">${board.title}</h4>
                <a href="#" class="btn btn-primary">상세보기</a>
            </div>
        </div>
    </c:forEach>
</div>

<%@ include file="layout/footer.jsp" %>





