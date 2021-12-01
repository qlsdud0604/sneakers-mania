<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="layout/header.jsp" %>

<div>
    <div class="d-flex justify-content-center">
        <c:forEach var="board" items="${boards.content}">
            <div class="card m-3" style="width:330px">
                <img class="card-img-top"
                     src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbd5ujI%2FbtrmxHTCXur%2F9UIhvKYKIs2IJkeLl4dxl1%2Fimg.jpg"
                     alt="Card image">
                <hr/>
                <div class="card-body" style="border-top-width: 1px; border-color: black">
                    <h5 class="card-title">${board.title}</h5>
                    <br/>
                    <a href="/board/${board.id}" class="btn btn-dark">상세보기</a>
                </div>
            </div>
        </c:forEach>
    </div>
    &nbsp;
    <ul class="pagination justify-content-center">
        <c:choose>
            <c:when test="${boards.first}">
                <li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">이전</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number-1}">이전</a></li>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${boards.last}">
                <li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">다음</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number+1}">다음</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>

<%@ include file="layout/footer.jsp" %>





