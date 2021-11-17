<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../layout/header.jsp" %>

<div class="container">
    <form action="/auth/loginProc" method="post">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" name="username" class="form-control" placeholder="아이디를 입력해 주세요." id="username">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" class="form-control" placeholder="패스워드를 입력해 주세요." id="password">
        </div>
        <button id="btn-login" class="btn btn-primary">로그인</button>
        <a href="https://kauth.kakao.com/oauth/authorize?client_id=9cbe170d83aa45724a840169b89614f6&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code">
            <img height="38px" src="/image/kakao_login_button.png"/></a>
    </form>
</div>

<%@ include file="../layout/footer.jsp" %>





