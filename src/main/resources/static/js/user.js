let index = {
    init: function () {
        $("#btn-save").on("click", () => {
            this.save();
        });

        $("#btn-update").on("click", () => {
            this.update();
        });
    },
    /** 회원가입 요청 */
    save: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
        }

        /* ajax는 비동기 방식으로 작동 */
        $.ajax({
            /* 통신 요청 */
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            /* 응답의 결과가 성공한 경우 */
            if (res.status === 500) {
                alert("회원가입에 실패하였습니다.");
            } else {
                alert("회원가입이 완료되었습니다.");
                location.href = "/loginForm";
            }
        }).fail(function (err) {
            /* 응답의 결과가 실패한 경우 */
            alert(JSON.stringify(err));
        });
    },
    /** 회원수정 요청 */
    update: function () {
        let data = {
            id: $("#id").val(),
            username: $("#username").val(),
            password: $("#password").val(),
        }

        /* ajax는 비동기 방식으로 작동 */
        $.ajax({
            /* 통신 요청 */
            type: "PUT",
            url: "/user",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            /* 응답의 결과가 성공한 경우 */
            console.log(res);
            alert("패스워드 변경이 완료되었습니다.");
            location.href = "/user/profileForm";
        }).fail(function (err) {
            /* 응답의 결과가 실패한 경우 */
            alert(JSON.stringify(err));
        });
    },
}

index.init();