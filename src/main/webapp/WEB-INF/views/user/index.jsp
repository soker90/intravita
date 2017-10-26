<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<c:set var="user" value="${user}" scope="request" /> <!-- Esto envia variables a las vistas de los includes -->
<c:set var="vista" value="usuario" scope="request" />
<%@ include file="/WEB-INF/views/header.jsp" %>

 <!-- main col right -->
 <div class="col-sm-9">

   <div class="panel panel-default">
	  <div class="panel-body">
		<div class="clearfix"></div>
		<hr>
		
		<p>Texto de mi publicación.</p>
		
		<hr>
		<form>
		<div class="input-group">
		  <div class="input-group-btn">
		  <button class="btn btn-default">+1</button><button class="btn btn-default"><i class="glyphicon glyphicon-share"></i></button>
		  <button class="btn btn-default"><i class="glyphicon glyphicon-edit"></i></button><button class="btn btn-default"><i class="glyphicon glyphicon-remove"></i></button>
		  </div>
		</div>
		</form>
		
	  </div>
   </div>


 </div>

	
<%@ include file="/WEB-INF/views/footer.jsp" %>
