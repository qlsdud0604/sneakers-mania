<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../layout/header.jsp" %>

<div style="width: 450px; margin: 0 auto">
    <form>
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" placeholder="아이디를 입력해 주세요." id="username">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" placeholder="패스워드를 입력해 주세요." id="password">
        </div>
    </form>
    &nbsp;
    <button id="btn-save" class="btn btn-dark btn-block">회원가입</button>
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>





