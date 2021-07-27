<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div class="container">
	<!-- var은 메모리 중 pageScope에 들어감에 들어감 -->
	<c:forEach var="post" items="${postsEntity.content}">

		<div class="card">
			<div class="card-body">
				<h4 class="card-title">${post.title}</h4>
				<a href="/post/${post.id}" class="btn btn-primary">상세보기</a>
			</div>
		</div>
		<!-- End of Card -->

	</c:forEach>

	<%-- 페이징 할 때 어떤 데이터를 뿌려줄지는 머리 좀 써야 함  --%>
	<ul class="pagination">
 		 <li class="page-item disabled"><a class="page-link" href="?page=${postsEntity.number-1}">Previous</a></li>
 		 <li class="page-item"><a class="page-link" href="?page=0">1</a></li>
 		 <li class="page-item"><a class="page-link" href="?page=1">2</a></li>
 		 <li class="page-item"><a class="page-link" href="?page=2">3</a></li>
  		<li class="page-item"><a class="page-link" href="?page=${postsEntity.number+1}">Next</a></li>
	</ul>


</div>
<!-- End of Container -->



<%@ include file="../layout/footer.jsp"%>
