let index = {
    init: function () {
        $("#btn-save").on("click", () => {
            this.save();
        });
    },
    /** 회원가입 요청 */
    save: function () {
        let data = {
            username: $("#username").val(),
            email: $("#email").val(),
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
            console.log(res);
            alert("회원가입이 완료되었습니다.");
            location.href = "/loginForm";
        }).fail(function (err) {
            /* 응답의 결과가 실패한 경우 */
            alert(JSON.stringify(err));
        });
    }
}

index.init();