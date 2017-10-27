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
		
		<form>
			<div class="form-group">
		        <label for="nick_id" class="control-label">Nombre de usuario</label>
		        <input type="text" class="form-control" id="nick" name="nick" readonly value="${user_edit.nickname}">
		    </div>   
		    <div class="form-group"> <!-- Full Name -->
		        <label for="nombre_id" class="control-label">Nombre</label>
		        <input type="text" class="form-control" id="nombre" name="nombre" value="${user_edit.nombre}" placeholder="Esriba aqui su nombre">
		    </div>    
		
		    <div class="form-group">
		        <label for="apellidos_id" class="control-label">Apellidos</label>
		        <input type="text" class="form-control" id="apellidos" name="apellidos" value="${user_edit.apellido}" placeholder="Escriba aqui sus apellidos">
		    </div>                    
		                            
		    <div class="form-group">
		        <label for="foto_id" class="control-label">Foto</label>
		        <input type="text" class="form-control" id="foto" name="foto" value="${user_edit.foto}" placeholder="Introduzca un enlace a una imagen">
		    </div>                                    
		                            
		    <div class="form-group">
		        <label for="email_id" class="control-label">Correo electrónico</label>
		        <input type="email" class="form-control" id="email" name="email" value="${user_edit.email}">
		    </div>     
		    
		    <div class="form-group">
		        <label for="email_id" class="control-label">Cambiar Rol(pendiente)</label>
		    </div> 
		    
		    <div class="form-group">
		        <button type="submit" class="btn btn-primary">Aceptar</button>
		    </div>     
		    
		</form>
		
		<hr>
		
	  </div>
   </div>
   
   <div class="panel panel-default">
   	  <div class="panel-heading"><h4>Cambiar contraseña</h4></div>
	  <div class="panel-body">
		<div class="clearfix"></div>
		
		<form>   
		    
		    <div class="form-group">
		        <label for="password_id" class="control-label">Contraseña</label>
		        <input type="password" class="form-control" id="password" name="password">
		    </div>   
		    
		    <div class="form-group">
		        <label for="password2_id" class="control-label">Repita la contraseña</label>
		        <input type="password" class="form-control" id="password2" name="password2">
		    </div>   
		    
		    <div class="form-group">
		        <button type="submit" class="btn btn-primary">Aceptar</button>
		    </div>     
		    
		</form>
		
		<hr>
		
	  </div>
   </div>
   

 </div>

	
<%@ include file="/WEB-INF/views/footer.jsp" %>




