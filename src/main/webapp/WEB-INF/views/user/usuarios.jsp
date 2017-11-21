<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<c:set var="user" value="${user}" scope="request" /> <!-- Esto envia variables a las vistas de los includes -->
<c:set var="vista" value="usuario" scope="request" />
<c:set var="var" value="${var}" scope="request" />
<%@ include file="/WEB-INF/views/header.jsp" %>

 <!-- main col right -->
 <div class="col-sm-9">
  <c:if test="${var.cont == 1 && not empty var.mensaje}">
 	<div class="aviso-${var.tipo} alert alert-success alert-dismissable fade in" role="alert">
 		<button type="button" class="cerrar" data-dismiss="alert" aria-label="Close">
    		<span aria-hidden="true">&times;</span>
	  	</button>
   	  <h4>${var.mensaje}</h4>
   </div>
                                        
  </c:if>
	<c:if test="${vacio eq 'vacio'}">
		<div class="panel panel-default">
		  <div class="panel-body">
			<div class="clearfix"></div>
			
			<h4>No hay coincidencias.</h4>
			<hr>
			
		  </div>
	   </div>
	</c:if>
	<c:forEach items="${usuarios}" var="resultado">
	   <div class="panel panel-default">
	   	<div class="panel-heading"><h7 class="pull-right" style="padding-bottom: 3px; padding-left:2px;"><a href="${var.url}/user/ver/${resultado.nickname}">Ver perfil</a></h7><h4> </h4></div>
		  <div class="panel-body">
			<div class="clearfix"></div>
			
			<p><a href="${var.url}/user/ver/${resultado.nickname}"><h1><img src="${var.url}/${resultado.foto}" class="img-circle-big">${resultado.nombre} ${resultado.apellido}</h1></a></p>
			
			<hr>
			<form>
			<div class="input-group">
			  <div class="input-group-btn">
			  <c:if test="${user.nickname ne resultado.nickname}">
			  <form></form>
			  <c:if test="${resultado.pendiente} == false">
				  <form style="display: inline-block" action="${var.url}/user/crearSolicitud" method="post">
	 			    <input type="text" id="id" name="id" hidden="hidden" value="${resultado.nickname}">
	 			    <button style="display: inline-block" type="submit" name="submit" value="submit" class="btn btn-default""><i class="glyphicon glyphicon-plus"></i> Agregar amigo</button>
	 		 	 </form>
 		 	 </c:if>
 		 	 
 		 	 <c:if test="${resultado.pendiente} == true">
				  <form style="display: inline-block" action="${var.url}/user/revocarAmistad" method="post">
	 			    <input type="text" id="id" name="id" hidden="hidden" value="${resultado.nickname}">
	 			    <button style="display: inline-block" type="submit" name="submit" value="submit" class="btn btn-default""><i class="glyphicon glyphicon-minus"></i> Cancelar amistad</button>
	 		 	 </form>
 		 	 </c:if>
	 			    
			  </c:if>
			  
			  </div>
			</div>
			</form>
			
		  </div>
	   </div>
   </c:forEach>


 </div>

	
<%@ include file="/WEB-INF/views/footer.jsp" %>
