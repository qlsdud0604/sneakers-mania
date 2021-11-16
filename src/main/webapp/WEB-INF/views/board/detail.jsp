<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../layout/header.jsp" %>

<div class="container">
    <div>
        글 번호 : <span id="id">${board.id}</span>
        <br/>
        작성자 : <span>${board.user.username}</span>
    </div>
    <hr/>
    <div class="form-group">
        <h3>${board.title}</h3>
    </div>
    <hr/>
    <div class="form-group">
        <div>${board.content}</div>
    </div>
    <hr/>
    <br/>
    <c:if test="${board.user.id==principal.user.id}">
        <a href="/board/${board.id}/updateForm" class="btn btn-secondary justify-content-center">수정</a>
        <button id="btn-delete" class="btn btn-danger justify-content-center">삭제</button>
    </c:if>
</div>

</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>





