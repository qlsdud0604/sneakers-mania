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
    <c:if test="${board.user.id==principal.user.id}">
        <a href="/board/${board.id}/updateForm" class="btn btn-secondary justify-content-center">수정</a>
        <button id="btn-delete" class="btn btn-danger justify-content-center">삭제</button>
    </c:if>
    <br/>
    <br/>
    <div class="card">
        <div class="card-body d-flex"><textarea id="reply-content" class="form-control"></textarea>
            &nbsp;&nbsp;<button type="buton" id="btn-reply-save" class="btn btn-primary">등록</button>
        </div>
    </div>
    <br/>
    <div class="card">
        <form>
            <input type="hidden" id="userId" value="${principal.user.id}"/>
            <input type="hidden" id="boardId" value="${board.id}"/>
            <div class="card-header">댓글 리스트</div>
            <ul id="reply--box" class="list-group">
                <c:forEach var="reply" items="${board.replies}">
                    <li id="reply" class="list-group-item d-flex justify-content-between">
                        <div>${reply.content}</div>
                        <div class="d-flex">
                            <div class="font-italic">작성자 : ${reply.user.username} &nbsp;&nbsp;</div>
                            <button class="badge">삭제</button>
                        </div>
                    </li>
                </c:forEach>

            </ul>
        </form>
    </div>
</div>

</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>





