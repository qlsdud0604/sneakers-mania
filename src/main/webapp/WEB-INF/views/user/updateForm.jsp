<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../layout/header.jsp" %>

<div style="width: 450px; margin: 0 auto">
    <form>
        <input type="hidden" id="id" value="${principal.user.id}">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" value="${principal.user.username}" class="form-control" placeholder="아이디를 입력해 주세요."
                   id="username" readonly>
        </div>
        <c:if test="${empty principal.user.oauth}">
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" placeholder="변경할 패스워드를 입력해 주세요."
                       id="password">
            </div>
        </c:if>
    </form>
    &nbsp;
    <button id="btn-update" class="btn btn-dark btn-block">패스워드 변경</button>
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>





