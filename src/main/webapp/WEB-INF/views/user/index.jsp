<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<c:set var="user" value="${user}" scope="request" /> <!-- Esto envia variables a las vistas de los includes -->
<c:set var="vista" value="usuario" scope="request" />
<c:set var="var" value="${var}" scope="request" />
<%@ include file="/WEB-INF/views/header.jsp" %>

 <!-- main col right -->
 <div class="col-sm-9">
	<% int cont=0;%>
	<c:forEach items="${publicaciones}" var="publicacion">
	   <div class="panel panel-default">
	   	<div class="panel-heading"><h7 class="pull-right">${publicacion.fecha}</h7> <h4>${publicacion.unombre}</h4></div>
		  <div class="panel-body">
			<div class="clearfix"></div>
			
			<p>${publicacion.texto}</p>
			
			<hr>
			<form>
			<div class="input-group">
			  <div class="input-group-btn">
			  <c:if test="${publicacion.nickname ne user.nickname}">
			  	<button class="btn btn-default">+1</button><button class="btn btn-default"><i class="glyphicon glyphicon-share"></i></button>
			  </c:if>
			  <c:if test="${publicacion.nickname eq user.nickname}">
			  	<button class="btn btn-default"><i class="glyphicon glyphicon-edit"></i></button><button class="btn btn-default"><i class="glyphicon glyphicon-remove"></i></button>
			  </c:if>
			  </div>
			</div>
			</form>
			
		  </div>
	   </div>
   </c:forEach>


 </div>

	
<%@ include file="/WEB-INF/views/footer.jsp" %>
