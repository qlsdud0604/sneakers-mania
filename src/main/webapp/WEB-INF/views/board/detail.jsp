<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../layout/header.jsp" %>

<div class="container">
    <div class="form-group">
        <h3>${board.title}</h3>
    </div>
    <hr/>
    <div class="form-group">
        <div>${board.content}</div>
    </div>
    <hr/>
    <br/>
    <button id="btn-update" class="btn btn-secondary justify-content-center">수정</button>
    <button id="btn-delete" class="btn btn-danger justify-content-center">삭제</button>
</div>

</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>





