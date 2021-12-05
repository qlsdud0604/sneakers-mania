let index = {
    init: function () {
        $("#btn-save").on("click", () => {
            this.save();
        });

        $("#btn-delete").on("click", () => {
            this.deleteById();
        });

        $("#btn-update").on("click", () => {
            this.update();
        });

        $("#btn-reply-save").on("click", () => {
            this.replySave();
        });
    },
    /** 글쓰기 요청 */
    save: function () {

        /* 썸네일 URL 추출 */
        const imgRex = /<img.*?src="(.*?)"[^>]+>/g;

        const images = [];

        while ((img = imgRex.exec($("#content").val()))) {
            images.push(img[1]);

            if (images.length === 1)
                break;
        }

        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
            thumbnail: images[0]
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
    },
    /** 글삭제 요청 */
    deleteById: function () {
        let id = $("#id").text();

        $.ajax({
            /* 통신 요청 */
            type: "DELETE",
            url: "/api/board/" + id,
            dataType: "json"
        }).done(function (res) {
            /* 응답의 결과가 성공한 경우 */
            console.log(res);
            alert("삭제가 완료되었습니다.");
            location.href = "/";
        }).fail(function (err) {
            /* 응답의 결과가 실패한 경우 */
            alert(JSON.stringify(err));
        });
    },
    /** 글수정 요청 */
    update: function () {
        let id = $("#id").val();

        /* 썸네일 URL 추출 */
        const imgRex = /<img.*?src="(.*?)"[^>]+>/g;

        const images = [];

        while ((img = imgRex.exec($("#content").val()))) {
            images.push(img[1]);

            if (images.length === 1)
                break;
        }

        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
            thumbnail: images[0]
        }

        $.ajax({
            /* 통신 요청 */
            type: "PUT",
            url: "/api/board/" + id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            /* 응답의 결과가 성공한 경우 */
            console.log(res);
            alert("글수정이 완료되었습니다.");
            location.href = "/board/" + id;
        }).fail(function (err) {
            /* 응답의 결과가 실패한 경우 */
            alert(JSON.stringify(err));
        });
    },
    /** 댓글 작성 요청 */
    replySave: function () {
        let data = {
            userId: $("#userId").val(),
            boardId: $("#boardId").val(),
            content: $("#reply-content").val(),
        }

        let boardId = $("#boardId").val();

        $.ajax({
            /* 통신 요청 */
            type: "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            /* 응답의 결과가 성공한 경우 */
            console.log(res);
            alert("댓글 작성이 완료되었습니다.");
            location.href = `/board/${data.boardId}`;
        }).fail(function (err) {
            /* 응답의 결과가 실패한 경우 */
            alert(JSON.stringify(err));
        });
    },
    /** 댓글 삭제 요청 */
    replyDelete: function (boardId, replyId) {
        $.ajax({
            /* 통신 요청 */
            type: "DELETE",
            url: `/api/board/${boardId}/reply/${replyId}`,
            dataType: "json"
        }).done(function (res) {
            /* 응답의 결과가 성공한 경우 */
            alert("댓글이 삭제되었습니다.");
            location.href = `/board/${boardId}`;
        }).fail(function (err) {
            /* 응답의 결과가 실패한 경우 */
            alert(JSON.stringify(err));
        })
    }
}

index.init();