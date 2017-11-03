<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<c:set var="perfil" value="${perfil}" scope="request" />
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
                                        
  </c:if>>

	<c:if test="${vacio eq 'vacio'}">
		<div class="panel panel-default">
		  <div class="panel-body">
			<div class="clearfix"></div>
			
			<h4>No hay publicaciones que mostrar.</h4>
			<hr>
			
		  </div>
	   </div>
	</c:if>
	
	<c:forEach items="${publicaciones}" var="publicacion">
	   <div class="panel panel-default">
	   	<div class="panel-heading"><h7 class="pull-right">${publicacion.fecha}</h7> <h4><img src="${var.url}/${publicacion.ufoto}" class="img-circle"> ${publicacion.unombre}</h4></div>
		  <div class="panel-body">
			<div class="clearfix"></div>
			
			<p>${publicacion.texto}</p>
			
			<hr>
			<form>
			<div class="input-group">
			  <div class="input-group-btn">
			  <c:if test="${publicacion.nickname ne user.nickname}">
			  <form style="display: inline-block" action="#" method="post">
 			    <input type="text" id="id" name="id" hidden="hidden" value="${publicacion.id}">
 			    <button style="display: inline-block" type="submit" name="submit" value="submit" class="btn btn-default"">+1</button>
 		 	 </form>
 		 	 
 		 	 <form style="display: inline-block" action="#" method="post">
 			    <input type="text" id="id" name="id" hidden="hidden" value="${publicacion.id}">
 			    <button style="display: inline-block" type="submit" name="submit" value="submit" class="btn btn-default""><i class="glyphicon glyphicon-share"></i></button>
 		 	 </form>
	 			    
			  </c:if>
			  <c:if test="${publicacion.nickname eq user.nickname}">
			  	<form></form>
				  <form style="display: inline-block" class="form-inline" action="${var.url}/user/editarPublicacion" method="post">
	 			    <input type="text" id="id" name="id" hidden="hidden" value="${publicacion.id}"/>
	 			    <a href="${var.url}/user/editarPublicacion?id=${publicacion.id}"><button style="display: inline-block" type="submit" name="submit" value="submit" class="btn btn-default"><i class="glyphicon glyphicon-edit"></i></button></a>
	 		 	 </form>
	 		 	 
	 		 	 <form style="display: inline-block" class="form-inline" action="${var.url}/user/removePublicacion" method="post">
	 			    <input type="text" id="id" name="id" hidden="hidden" value="${publicacion.id}"/>
	 			    <button style="display: inline-block" type="submit" name="submit" value="submit" class="btn btn-default"><i class="glyphicon glyphicon-remove"></i></button>
	 		 	 </form>
 		 	 
			  </c:if>
			  </div>
			</div>
			</form>
			
		  </div>
	   </div>
   </c:forEach>


 </div>

	
<%@ include file="/WEB-INF/views/footer.jsp" %>
