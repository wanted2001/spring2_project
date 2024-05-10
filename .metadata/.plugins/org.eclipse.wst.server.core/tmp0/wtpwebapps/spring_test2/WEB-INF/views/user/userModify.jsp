<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<jsp:include page="../layout/header.jsp" />
<div class="container-md">
	<h1>User Modify Page</h1>
	<form action="/user/userModify" method="post">
	<sec:authentication property="principal.uvo.email" var="authEmail"/>
	<sec:authentication property="principal.uvo.pwd" var="authPwd"/>
	<sec:authentication property="principal.uvo.nickName" var="authNick"/>
	
		<div class="mb-3">
			<label for="e" class="form-label">E-MAIL</label> <input type="text"
				class="form-control" name="email" id="e" value="${authEmail }" readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="p" class="form-label">PASSWORD</label> <input
				type="password" class="form-control" name="pwd" id="p"
				placeholder="pw...">
		</div>
		<div class="mb-3">
			<label for="n" class="form-label">NAME</label> <input type="text"
				class="form-control" name="nickName" id="n" value="${authNick }">
		</div>
		<button type="submit" class="btn btn-success">MODIFY</button>
		<a href="/user/delete?email=${authEmail }"><button type="button"
				class="btn btn-success">DELETE</button></a>
	</form>
</div>
<jsp:include page="../layout/footer.jsp" />