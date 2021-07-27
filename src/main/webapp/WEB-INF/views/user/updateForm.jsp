<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div class="container">
	<!--username=머시기&password=머시기&email&address=머시기-->
	<form action="/user/${principal.id}" method="post">
		<div class="form-group">
			<label for="email">Username:</label> <input value="${principal.username}" type="text"
				class="form-control" placeholder="Enter email" readonly="readonly" />
		</div>
		<div class="form-group">
			<label for="email">Email address:</label> <input value="${principal.email}"
				type="email" class="form-control" placeholder="Enter email" readonly="readonly" />
		</div>
		<div class="form-group">
			<label for="pwd">Password:</label> <input value="${principal.password}" type="password"
				class="form-control" placeholder="Enter email" name="password" required="required" />
		</div>

		<input class="btn btn-info" type="button" onClick="goPopup();"
			value="주소찾기" />

		<div class="form-group">
			<label for="email">Address:</label> <input value="${principal.address}" type="text"
				class="form-control" placeholder="Enter password" name="address"
				id="address" />


		</div>
		<button type="submit" class="btn btn-primary">회원수정</button>
	</form>
</div>

<script>
	function goPopup() {
		var pop = window.open("/juso", "pop",
				"width=570,height=420, scrollbars=yes, resizable=yes");
	}

	function jusoCallBack(roadFullAddr) {
		let addressEL = document.querySelector("#address");
		addressEL.value = roadFullAddr;
		console.log(addressEL);
	}
</script>






<%@ include file="../layout/footer.jsp"%>
