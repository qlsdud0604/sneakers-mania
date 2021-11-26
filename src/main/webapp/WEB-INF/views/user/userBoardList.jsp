<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../layout/header.jsp" %>
<div class="container">
    <div class="justify-content-center">
        <c:forEach var="board" items="${boards}">
            <div class="card m-3 ">
                <div class="card-body d-flex justify-content-between align-items-center">
                    <div>
                        <h5 style="margin-bottom: 10px">제목 : ${board.title}</h5>
                        <p class="font-italic" style="margin: 0px">작성일 : ${board.createDate}</p>
                    </div>
                    <div>
                        <a href="/board/${board.id}" class="btn btn-dark">상세보기</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<%@ include file="../layout/footer.jsp" %>
