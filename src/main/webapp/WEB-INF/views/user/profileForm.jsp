<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../layout/header.jsp" %>

<div class="container">
    <input type="hidden" id="id" value="${principal.user.id}">
    <div class="form-group">
        <label for="username">π Username</label>
        <h5 type="text" class="form-control"
            id="username">${principal.user.username} </h5>
    </div>
    <br/>
    <br/>
    <br/>
    <c:if test="${empty principal.user.oauth}">
        <a href="/user/updateForm" style="text-decoration: none">
            <div id="update-box" class="d-flex justify-content-between align-items-center"
                 style="border-top: 1px solid lightgray; height: 70px">
                <div style="color: black">
                    &nbsp;&nbsp;ν¨μ€μλ λ³κ²½
                </div>
                <div style="color: black">
                    >&nbsp;&nbsp;
                </div>
            </div>
        </a>
    </c:if>
    <a href="/user/userBoardList" style="text-decoration: none">
        <div id="board-box" class="d-flex justify-content-between align-items-center"
             style="border-top: 1px solid lightgray; border-bottom: 1px solid lightgray; height: 70px">
            <div style="color: black">
                &nbsp;&nbsp;μμ± κ²μκΈ λͺ©λ‘
            </div>
            <div style="color: black">
                >&nbsp;&nbsp;
            </div>
        </div>
    </a>
    <a href="/logout" style="text-decoration: none">
        <div id="logout-box" class="d-flex justify-content-between align-items-center"
             style="border-bottom: 1px solid lightgray; height: 70px">
            <div style="color: red">
                &nbsp;&nbsp;λ‘κ·Έμμ
            </div>
            <div style="color: red">
                >&nbsp;&nbsp;
            </div>
        </div>
    </a>
</div>

<style>
    #update-box:hover {
        background-color: #FAFAFA;
    }

    #board-box:hover {
        background-color: #FAFAFA;
    }

    #logout-box:hover {
        background-color: #FAFAFA;
    }
</style>
<%@ include file="../layout/footer.jsp" %>