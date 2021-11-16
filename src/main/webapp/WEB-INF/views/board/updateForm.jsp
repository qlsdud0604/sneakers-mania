<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../layout/header.jsp" %>

<div class="container">
    <form>
        <input type="hidden" id="id" value="${board.id}"/>
        <div class="form-group">
            <label for="title">Title</label>
            <input value="${board.title}" type="text" class="form-control" placeholder="제목을 입력해주세요." id="title">
        </div>
        <div class="form-group">
            <label for="content">Content</label>
            <textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
        </div>
    </form>
    <button id="btn-update" class="btn btn-primary">글수정</button>
</div>

<script>
    $('.summernote').summernote({
        placeholder: '내용을 입력해주세요.',
        tabsize: 2,
        height: 250
    });
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>





