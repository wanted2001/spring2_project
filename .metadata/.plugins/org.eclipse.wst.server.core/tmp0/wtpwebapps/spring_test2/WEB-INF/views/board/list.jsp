<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../layout/header.jsp" />
<div class="container-md">
	<h1>Board List Page</h1>
	<br>
	<!-- search Line -->
	<form action="/board/list" method="get" class="row gy-2 gx-3 align-items-center">
   		<div class="col-auto">
    		<select class="form-select" name="type" id="autoSizingSelect">
      			<option ${ph.pgvo.type == null ? 'selected' : '' }>Choose...</option>
      			<option value="t" ${ph.pgvo.type eq 't' ? 'selected' : '' }>Title</option>
      			<option value="w" ${ph.pgvo.type eq 'w' ? 'selected' : '' }>Writer</option>
      			<option value="c" ${ph.pgvo.type eq 'c' ? 'selected' : '' }>Content</option>
      			<option value="tc" ${ph.pgvo.type eq 'tc' ? 'selected' : '' }>Title & Content</option>
      			<option value="wc" ${ph.pgvo.type eq 'wc' ? 'selected' : '' }>Writer & Content</option>
      			<option value="tw" ${ph.pgvo.type eq 'tw' ? 'selected' : '' }>Title & Writer</option>
      			<option value="twc" ${ph.pgvo.type eq 'twc' ? 'selected' : '' }>All</option>
   			</select>
  		</div>
		<div class="col-auto">
    		<input type="text" name="keyword" class="form-control" id="autoSizingInput" placeholder="search.." value="${ph.pgvo.keyword}">
    		<input type="hidden" name="pageNo" value="1">
    		<input type="hidden" name="qty" value="10">
  		</div>
  		<div class="col-auto">
    		<button type="submit" class="btn btn-primary">Search</button>
  		</div>
  		<div class="col-auto">
  			<p>totalCount : ${ph.totalCount }</p>
  		</div>
  	</form>
	<table class="table table-hover">
	  <thead>
	  	<tr>
	  		<th>#</th>
	  		<th>title</th>
	  		<th>writer</th>
	  		<th>regDate</th>
	  		<th>readCount</th>
	  		<th>hasFile</th>
	  		<th>cmtQTY</th>
	  	</tr>
	  </thead>
	  <tbody>
	  
	  <c:forEach items="${list }" var="bvo">
	  	<tr>
			<td>${bvo.bno}</td>
			<td><a href="/board/detail?bno=${bvo.bno }">${bvo.title}</a>
			<%-- <c:if test="${bvo.cmt_qty ne 0 }">
			[${bvo.cmt_qty }]
			</c:if> --%>
			</td>
			<td>${bvo.writer}</td>
			<td>${bvo.regDate}</td>
			<td>${bvo.readCount}</td>
			<td>${bvo.hasFile}</td>
			<td>${bvo.cmtQty }</td>
		</tr>
	  </c:forEach>
	  </tbody>
	</table>
	<!-- paging Line -->
	<nav aria-label="Page navigation example">
	  <ul class="pagination justify-content-center">
	  	<!-- prev -->
	    <li class="page-item ${ph.prev eq false ? 'disabled' : ''}">
	      <a class="page-link" href="/board/list?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span></a>
	    </li>
	    <c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
	    <li class="page-item ${i eq ph.pgvo.pageNo ? 'active' : ''}"><a class="page-link" href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i }</a></li>
	    </c:forEach>
	    <!-- next -->
	    <li class="page-item ${ph.next eq false ? 'disabled' : ''}">
	      <a class="page-link" href="/board/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Next">
	        <span aria-hidden="true">&raquo;</span></a>
	    </li>
	  </ul>
	</nav>
</div>
<jsp:include page="../layout/footer.jsp" />