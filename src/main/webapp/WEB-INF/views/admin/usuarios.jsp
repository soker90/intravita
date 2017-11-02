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
 	<div class="panel panel-default">
   	  <div class="panel-heading"><h4>${var.mensaje}</h4></div>
   </div>
                                        
</c:if>

   <div class="panel panel-default">
   	  <div class="panel-heading"><h4>Usuarios</h4></div>
	  <div class="panel-body">
		<div class="clearfix"></div>
		
		<table id="usuarios" class="table table-striped table-bordered" cellspacing="0" width="100%">
			<thead>
	            <tr>
	            	<th>Nombre</th>
	 				<th>Apellidos</th>
	 				<th>Admin</th>
	 				<th>Editar</th>
	 				<th>Eliminar</th>
	 				<th>Validar</th>
	            </tr>
	        </thead>
	        <tfoot>
	            <tr>
	                <th>Nombre</th>
	 				<th>Apellidos</th>
	 				<th>Admin</th>
	 				<th>Editar</th>
	 				<th>Eliminar</th>
	 				<th>Validar</th>
	            </tr>
	        </tfoot>
	        <tbody>
	
	 		<c:forEach var="listVar" items="${listName}">
	 			<tr>
	 			    <td><c:out value="${listVar[0]}"/></td>
	 			    <td><c:out value="${listVar[1]}"/></td>
	 			    <td><c:if test="${listVar[3] ne 'super.admin'}">
	 			    <form action="${var.url}/admin/updateRol" method="post">
		 			    <input type="text" id="username" name="username" hidden="hidden" value="${listVar[3]}">
		 			    <input type="text" id="rol" name="rol" hidden="hidden" value="${listVar[2]}">
		 			    <button type="submit" name="submit" value="submit" class="btn btn-primary">${listVar[2]}</button>
	 			    </form>
	 			    </c:if></td>
	 			    
	 				<td><c:if test="${listVar[3] ne 'super.admin'}">
	 				<form action="${var.url}/admin/editarUsuario" method="post">
		 			    <input type="text" id="username" name="username" hidden="hidden" value="${listVar[3]}">
		 			    <button type="submit" name="submit" value="submit" class="btn btn-warning">Editar</button>
	 			    </form></c:if></td>
	 			    
	 				<td><c:if test="${listVar[3] ne 'super.admin'}">
	 				<form action="${var.url}/admin/borrarUsuario" method="post">
		 			    <input type="text" id="username" name="username" hidden="hidden" value="${listVar[3]}">
		 			    <button type="submit" name="submit" value="submit" class="btn btn-danger">Eliminar</button>
	 			    </form></c:if></td>
	 			    
	 			    <td><c:if test="${(listVar[3] ne 'super.admin') and (listVar[4] eq 'validar')}">
	 				<form action="${var.url}/admin/validar" method="post">
		 			    <input type="text" id="username" name="username" hidden="hidden" value="${listVar[3]}">
		 			    <button type="submit" name="submit" value="submit" class="btn btn-success">Validar</button>
	 			    </form></c:if></td>
	 		  	</tr>
	 		</c:forEach>
	 		</tbody>
 		</table>
		
		<hr>
		
	  </div>
   </div>


 </div>
 
 <script src="${var.url}/resources/js/jquery-1.11.1.min.js"></script>
 <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
 <script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>
  
 <script>
 	$(document).ready(function() {
	    $('#usuarios').DataTable();
	} );
 </script>

	
<%@ include file="/WEB-INF/views/footer.jsp" %>