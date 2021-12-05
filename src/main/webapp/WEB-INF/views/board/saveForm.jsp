<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../layout/header.jsp" %>

<div class="container">
    <form>
        <div class="form-group">
            <label for="title">Title</label>
            <input type="text" class="form-control" placeholder="제목을 입력해주세요." id="title">
        </div>
        <div class="form-group">
            <label for="content">Content</label>
            <textarea class="form-control summernote" rows="5" id="content"></textarea>
        </div>
    </form>
    <div class="d-flex justify-content-end">
        <button id="btn-save" class="btn btn-dark">글쓰기</button>
    </div>
</div>

<script>
    $('.summernote').summernote({
        placeholder: '내용을 입력해주세요.',
        tabsize: 2,
        height: 250,
        callbacks: {
            onImageUpload: function (files) {
                uploadSummernoteImageFile(files[0], this);
            }
        }
    });

    function uploadSummernoteImageFile(file, editor) {
        data = new FormData();
        data.append("file", file);

        $.ajax({
            data: data,
            type: "POST",
            url: "/uploadSummernoteImageFile",
            contentType: false,
            processData: false,
            success: function (data) {
                $(editor).summernote('insertImage', data.url);
            }
        });
    }
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>





