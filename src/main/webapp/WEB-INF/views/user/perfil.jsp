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

   <div class="panel panel-default">
   	  <div class="panel-heading"><h4>Perfil</h4></div>
	  <div class="panel-body">
		<div class="clearfix"></div>
		
		<form action="${var.url}/user/editarCuenta" method="post">
			<div class="form-group">
		        <label for="nick_id" class="control-label">Nombre de usuario</label>
		        <c:if test="${user.nickname ne 'super.admin'}">
		        	<input type="text" class="form-control" id="nick" name="nick" value="${user.nickname}">
		        </c:if>
		        <c:if test="${user.nickname eq 'super.admin'}">
		        	<input type="text" class="form-control" id="nick" name="nick" readonly value="${user.nickname}">
		        </c:if>
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
		        <label for="email_id" class="control-label">Correo electrónico</label>
		        <input type="email" class="form-control" id="email" name="email" value="${user.email}">
		    </div>     
		    
		    <div class="form-group">
		        <button type="submit" name="submit" value="submit" class="btn btn-primary">Aceptar</button>
		        <a href="${var.url}/user/configuracion"><input type="button" name="Cancelar" value="Cancelar" class="btn btn-danger"/></a>
		    </div> 
		    
		</form>
		
		<hr>
		
	  </div>
   </div>
   
   <div class="panel panel-default">
   	  <div class="panel-heading"><h4>Fotografía</h4></div>
	  <div class="panel-body">
		<div class="clearfix"></div>
		
		<form id="imagenes" name="imagenes" method="POST" action="${var.url}/uploadFile" enctype="multipart/form-data">
			<div class="form-group">
				<input type="file" name="file" accept="image/jpeg"> 
			</div>
			<div class="form-group">
				<button type="submit" name="submit" value="submit" class="btn btn-primary">Aceptar</button>
				<input type="text" id="nick" name="nick" hidden="" value="${user.nickname}">
				<a href="${var.url}/user/configuracion"><input type="button" name="Cancelar" value="Cancelar" class="btn btn-danger"/></a>
			</div>
		</form>
		
		<hr>
		
	  </div>
   </div>
   
   <div class="panel panel-default">
   	  <div class="panel-heading"><h4>Cambiar contraseña</h4></div>
	  <div class="panel-body">
		<div class="clearfix"></div>
		
		<form action="${var.url}/user/cambiarPassword" method="post">   
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
		        <a href="${var.url}/user/configuracion"><input type="button" name="Cancelar" value="Cancelar" class="btn btn-danger"/></a>
		    </div> 
		    
		</form>
		
		<hr>
		
	  </div>
   </div>
   
   <c:if test="${user.nickname ne 'super.admin' }">
	   <div class="panel panel-default">
	   	  <div class="panel-heading"><h4>Borrar cuenta</h4></div>
		  <div class="panel-body">
			<div class="clearfix"></div>
			
			<form action="${var.url}/user/borrarCuenta">   
			    
			    <div class="form-group">
			        <label for="borrar_id" class="control-label">Esta operación no se puede deshacer</label>
			    </div>
			    <div class="form-group">
			        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#btnBorrar">Borrar cuenta</button>
			    </div>
			    
			    <div class="modal fade" id="btnBorrar" role="dialog" >
				    <div class="modal-dialog">
				    
				      <!-- Modal content-->
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Borrar usuario</h4>
				        </div>
				        <div class="modal-body">
				        <div class="form-group">
				          <p class="caja-modal" >¿Est&aacute; seguro que desea borrar su usuario?</p>
				        </div>
				        </div>
				        <div class="modal-footer">
				          <button type="submit" class="btn btn-danger">Borrar cuenta</button>
				          <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				        </div>
				      </div>
				      
				    </div>
				</div>
        
			    
			</form>
			
			<hr>
			
		  </div>
	   </div>
   </c:if>
   


 </div>

	
<%@ include file="/WEB-INF/views/footer.jsp" %>




