<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<c:set var="user" value="${user}" scope="request" /> <!-- Esto envia variables a las vistas de los includes -->
<c:set var="vista" value="usuario" scope="request" />
<%@ include file="/WEB-INF/views/header.jsp" %>

								  
 <!-- main col right -->
 <div class="col-sm-9">

   <div class="panel panel-default">
   	  <div class="panel-heading"><h4>Perfil</h4></div>
	  <div class="panel-body">
		<div class="clearfix"></div>
		
		<form action="/intravita/user/editarCuenta" method="post">
			<div class="form-group">
		        <label for="nick_id" class="control-label">Nombre de usuario</label>
		        <input type="text" class="form-control" id="nick" name="nick" readonly value="${user.nickname}">
		    </div>   
		    <div class="form-group"> <!-- Full Name -->
		        <label for="nombre_id" class="control-label">Nombre</label>
		        <input type="text" class="form-control" id="nombre" name="nombre" value="${user.nombre}" placeholder="Esriba aqui su nombre">
		    </div>    
		
		    <div class="form-group">
		        <label for="apellidos_id" class="control-label">Apellidos</label>
		        <input type="text" class="form-control" id="apellidos" name="apellidos" value="${user.apellido}" placeholder="Escriba aqui sus apellidos">
		    </div>                    
		                            
		    <div class="form-group">
		        <label for="foto_id" class="control-label">Foto</label>
		        <input type="file" name="foto">
		    </div>                                    
		                            
		    <div class="form-group">
		        <label for="email_id" class="control-label">Correo electrónico</label>
		        <input type="email" class="form-control" id="email" name="email" value="${user.email}">
		    </div>     
		    
		    <div class="form-group">
		        <button type="submit" name="submit" value="submit" class="btn btn-primary">Aceptar</button>
		    </div> 
		    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		    
		</form>
		
		<hr>
		
	  </div>
   </div>
   
   <div class="panel panel-default">
   	  <div class="panel-heading"><h4>Fotografía</h4></div>
	  <div class="panel-body">
		<div class="clearfix"></div>
		
		<form id="imagenes" name="imagenes" method="POST" action="/intravita/uploadFile?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
			<div class="form-group">
				<input type="file" name="file"> 
			</div>
			<div class="form-group">
				<button type="submit" name="submit" value="submit" class="btn btn-primary">Aceptar</button>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</div>
		</form>
		
		<hr>
		
	  </div>
   </div>
   
   <div class="panel panel-default">
   	  <div class="panel-heading"><h4>Cambiar contraseña</h4></div>
	  <div class="panel-body">
		<div class="clearfix"></div>
		
		<form action="/intravita/user/cambiarPassword" method="post">   
		    <div class="form-group">
		        <label for="password_id" class="control-label">Contraseña actual</label>
		        <input type="password" class="form-control" id="password_old" name="password_old">
		    </div>   
		    
		    <div class="form-group">
		        <label for="password_id" class="control-label">Contraseña nueva</label>
		        <input type="password" class="form-control" id="password" name="password">
		    </div>   
		    
		    <div class="form-group">
		        <label for="password2_id" class="control-label">Repita la contraseña</label>
		        <input type="password" class="form-control" id="password2" name="password2">
		    </div>   
		    
		    <div class="form-group">
		        <button type="submit" name="submit" value="submit" class="btn btn-primary">Aceptar</button>
		    </div> 
		    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>  
		    
		</form>
		
		<hr>
		
	  </div>
   </div>
   
   <c:if test="${user.nickname ne 'super.admin' }">
	   <div class="panel panel-default">
	   	  <div class="panel-heading"><h4>Borrar cuenta</h4></div>
		  <div class="panel-body">
			<div class="clearfix"></div>
			
			<form action="/intravita/user/borrarCuenta">   
			    
			    <div class="form-group">
			        <label for="borrar_id" class="control-label">Esta operación no se puede deshacer</label>
			    </div>
			    <div class="form-group">
			        <button type="submit" class="btn btn-danger">Borrar cuenta</button>
			    </div>      
			    
			</form>
			
			<hr>
			
		  </div>
	   </div>
   </c:if>
   


 </div>

	
<%@ include file="/WEB-INF/views/footer.jsp" %>




