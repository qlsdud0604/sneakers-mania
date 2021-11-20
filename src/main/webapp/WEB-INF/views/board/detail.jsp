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
        <h5>제목 : ${board.title}</h5>
    </div>
    <hr/>
    <div class="form-group">
        <div>${board.content}</div>
    </div>
    <hr/>
    <div class="d-flex justify-content-end">
        <c:if test="${board.user.id==principal.user.id}">
            <a href="/board/${board.id}/updateForm" class="btn btn-primary">수정</a>
            &nbsp;
            <button id="btn-delete" class="btn btn-danger">삭제</button>
        </c:if>
    </div>
    <br/>
    <br/>
    <div class="card">
        <div class="card-body d-flex">
            <textarea id="reply-content" class="form-control" rows="1"></textarea>
            &nbsp;<button type="buton" id="btn-reply-save" class="btn btn-dark" style="width: 70px">등록
        </button>
        </div>
    </div>
    <br/>
    <div class="card">
        <form>
            <input type="hidden" id="userId" value="${principal.user.id}"/>
            <input type="hidden" id="boardId" value="${board.id}"/>
            <div class="card-header d-flex justify-content-center">💬</div>
            <ul id="reply-box" class="list-group">
                <c:forEach var="reply" items="${board.replies}">
                    <li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
                        <div>${reply.content}</div>
                        <div class="d-flex">
                            <div class="font-italic">작성자 : ${reply.user.username} &nbsp;&nbsp;</div>
                            <c:if test="${reply.user.id==principal.user.id}">
                                <button onclick="index.replyDelete(${board.id}, ${reply.id})" class="badge">삭제</button>
                            </c:if>
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





