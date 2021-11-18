<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../layout/header.jsp" %>

<div class="container">
    <form>
        <input type="hidden" id="id" value="${principal.user.id}">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" value="${principal.user.username}" class="form-control" placeholder="아이디를 입력해 주세요."
                   id="username" readonly>
        </div>
        <c:if test="${empty principal.user.oauth}">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" value="${principal.user.email}" class="form-control" placeholder="이메일을 입력해 주세요."
                       id="email">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" placeholder="패스워드를 입력해 주세요."
                       id="password">
            </div>
        </c:if>
    </form>
    <button id="btn-update" class="btn btn-primary">회원수정</button>
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>





