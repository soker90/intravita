<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<c:set var="user" value="${user}" scope="request" /> <!-- Esto envia variables a las vistas de los includes -->
<c:set var="vista" value="admin" scope="request" />
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

   <div class="panel panel-default">
   	  <div class="panel-heading"><h4>Editar publicaci&oacute;n</h4></div>
	  <div class="panel-body">
		<div class="clearfix"></div>
		
		<form action="${var.url}/admin/updatePublicacion" method="post">
			<div class="form-group">
				<input type="text" id="id" name="id" hidden="hidden" value="<c:out value="${publicacion.id}"/>">
		        <label for="nick_id" class="control-label">Texto</label>
		        <textarea type="text" class="form-control" id="texto" name="texto">${publicacion.texto}</textarea>
		    </div>        
		    <div class="form-group">
		        <button type="submit" name="submit" value="submit" class="btn btn-primary">Aceptar</button>
		    </div> 
		    
		</form>
		
		<hr>
		
	  </div>
   </div>
   

 </div>

	
<%@ include file="/WEB-INF/views/footer.jsp" %>




