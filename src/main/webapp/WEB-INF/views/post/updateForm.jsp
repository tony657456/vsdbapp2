<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layout/header.jsp" %>



<div class="container">
<!-- action, method, name 필요없음  -->
<form action="#" onsubmit="updatePost()">
    <div class="form-group">
    <label for="title">Title</label>
    <input id="title" value="${postEntity.title}" type="text" class="form-control" placeholder="Enter title" name="title"  required="required"/>
  </div>
    <div class="form-group">
    <textarea id="content" rows="10" class="form-control" name="content">
        ${postEntity.content}
    </textarea>

  </div>
  <button type="submit" class="btn btn-primary">글쓰기 완료</button>
</form>
</div>

    <script>
    	async function updatePost(){
    		console.log(event);
    		event.preventDefault(); // submit 동작을 막는다.
    		
    		let title = document.querySelector("#title").value;
    		let content = document.querySelector("#content").value;
    		
    		console.log(title);
    		console.log(content);
    		
    		let updateDto = {
    				title: title,
    				content: content
    		};
    		
    		// 백픽을 써서 $를 넣으면 자바스크립트는 변수를 바인딩할 때 쓰는 방법인데 여기서는 그렇게 쓰면 안된다.
    		let response = await fetch("/post/${postEntity.id}", {
    			method: "put",
    			body: JSON.stringify(updateDto),
    			headers: {
    				"Content-Type": "application/json; charset=utf-8"
    			}
    		});
    		
    		let parseResponse = await response.text(); // json(), text()
    		
    		console.log(parseResponse);
    		
    		// ===을 사용하면 값이 같아도 타입을 확인 해준다.
    		if(parseResponse === "ok") {
    			// location을 사용하면 화면을 이동 시켜줌, history는 이전 페이지로
    			location.href="/"
    		}
    	}
    		
    
      $('#content').summernote({
        placeholder: 'Hello Bootstrap 4',
        tabsize: 2,
        height: 500
      });
    </script>



<%@ include file="../layout/footer.jsp" %>