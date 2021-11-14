let index = {
    init: function () {
        $("#btn-save").on("click", () => {
            this.save();
        });
    },
    /** 글쓰기 요청 */
    save: function () {
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        }

        $.ajax({
            /* 통신 요청 */
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            /* 응답의 결과가 성공한 경우 */
            console.log(res);
            alert("글쓰기가 완료되었습니다.");
            location.href = "/";
        }).fail(function (err) {
            /* 응답의 결과가 실패한 경우 */
            alert(JSON.stringify(err));
        });
    }
}

index.init();