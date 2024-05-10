<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header.jsp</title>
<link href="/resources/css/bootstrap.min.css" rel="stylesheet"
	crossorigin="anonymous">
<script src="/resources/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand" href="/">Spring</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/">홈으로</a></li>

					<li class="nav-item"><a class="nav-link" href="/board/list">게시판
							보기</a></li>
					<sec:authorize access="isAnonymous()">
						<li class="nav-item"><a class="nav-link"
							href="/user/register">회원가입</a></li>
						<li class="nav-item"><a class="nav-link" href="/user/login">로그인</a>
						</li>
					</sec:authorize>
					<!-- 액세스에 권한이 있는지 확인  -->
					<!-- 현재 사용자의 정보는 principal  -->

					<sec:authorize access="isAuthenticated()">
						<sec:authentication property="principal.uvo.email" var="authEmail" />
						<sec:authentication property="principal.uvo.nickName"
							var="authNick" />
						<sec:authentication property="principal.uvo.authList" var="auths" />
						<c:choose>
							<c:when
								test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get() }">
								<li class="nav-item"><a class="nav-link" href="/user/userList">회원리스트
										${authNick }(${authEmail }) / ADMIN)</a></li>
								<li class="nav-item"><a class="nav-link"
									href="/board/register">게시판 글쓰기</a></li>
							</c:when>
							<c:otherwise>
								<li class="nav-item"><a class="nav-link" href="/user/userModify">회원정보수정
										${authNick }(${authEmail })</a></li>
							</c:otherwise>
						</c:choose>
						<li class="nav-item"><a class="nav-link" href=""
							id="logoutLink">로그아웃</a></li>
						<form action="/user/logout" method="post" id="logoutForm">
							<!-- 인증된 계정의 이메일 -->
							<input type="hidden" name="email" value="${authEmail }">
						</form>
					</sec:authorize>


					<%--  <c:if test="${ses.id ne null }">
        <li class="nav-item">
          <a class="nav-link" href="/user/logout">로그아웃</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/user/modify">${ses.id }(${ses.email }) welcome!!!</a>
        </li>
        </c:if> --%>
				</ul>
			</div>
		</div>
	</nav>
	<script type="text/javascript">
	document.getElementById("logoutLink").addEventListener('click',(e)=>{
		e.preventDefault();
	    document.getElementById("logoutForm").submit();
	})
	</script>