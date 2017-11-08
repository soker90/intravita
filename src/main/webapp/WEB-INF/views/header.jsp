<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="en">
	<head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8"> 
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link href="${var.url}/resources/css/bootstrap.css" rel="stylesheet">
        <!--[if lt IE 9]>
          <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <link href="${var.url}/resources/css/facebook.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.ckeditor.com/4.7.3/basic/ckeditor.js"></script>
        
                <title>Intravita</title>
        
    </head>
    
    <body>
        
        <div class="wrapper">
			<div class="box">
				<div class="row row-offcanvas row-offcanvas-left">
					
					<!-- main right col -->
					<div class="column col-sm-12 col-xs-13" id="main">
						
						<!-- top nav -->
						<div class="navbar navbar-blue navbar-static-top">  
							<div class="navbar-header">
							  <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-collapse">
								<span class="sr-only">Toggle</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							  </button>
							  <div class="navbar-brand logo">In</div>
							</div>
							<nav class="collapse navbar-collapse" role="navigation">
							
							<ul class="nav navbar-nav">
							  
							  <c:if test="${vista eq 'usuario'}">
							  		<li>
										<a href="${var.url}/user"><i class="glyphicon glyphicon-home"></i> Inicio </a>
							  		</li>
							  		<li>
										<a href="${var.url}/user/ver/${user.nickname}"><i class="glyphicon glyphicon-eye-open"></i> Perfil </a>
							  		</li>
							  		<li>
										<a href="${var.url}/#"><i class="glyphicon glyphicon-envelope"></i> Mensajes </a>
							  		</li>
							  		<li>
										<a href="${var.url}/user/amigos"><i class="glyphicon glyphicon-user"></i> Amigos </a>
							  		</li>
								  	<li>
										<a href="#postModal" role="button" data-toggle="modal"><i class="glyphicon glyphicon-plus"></i> Nueva publicación </a>
								  	</li>
							  	</c:if>
							  	
							  <c:if test="${user.rol eq 'ROLE_ADMIN'}">
							  	<c:if test="${vista eq 'usuario'}">
								  	<li>
										<a href="${var.url}/admin"><span class="badge">Usuario</span></a>
								  	</li>
							  	</c:if>
							  	
							  	<c:if test="${vista eq 'admin'}">
							  		<li>
										<a href="${var.url}/admin/usuarios"><i class="glyphicon glyphicon-user"></i> Usuarios </a>
							  		</li>
								  	<li>
										<a href="${var.url}/admin/publicaciones"><i class="glyphicon glyphicon-list-alt"></i> Publicaciones </a>
								  	</li>
								  	<li>
										<a href="${var.url}/user"><span class="badge">Administrador</span></a>
								  	</li>
							  	</c:if>
							  </c:if>
							  
							  <c:if test="${vista eq 'usuario'}">
							  		<form action="${var.url}/user/buscar" class="navbar-form navbar-left" method="post">
										<div class="input-group input-group-sm" style="max-width:360px;">
									  		<input class="form-control" placeholder="Buscar usuario" name="busqueda" id="busqueda" type="text">
									  		<div class="input-group-btn">
												<button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
									  		</div>
										</div>
									</form>
							  	</c:if>
							  
							  
							</ul>
							<ul class="nav navbar-nav navbar-right">
							  <li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i></a>
								<ul class="dropdown-menu">
								  <li><a href="${var.url}/user/configuracion">Configuraci&oacute;n</a></li>
								  <li><a href="${var.url}/logout">Salir</a></li>
								</ul>
							  </li>
							</ul>
							</nav>
						</div>
						<!-- /top nav -->
					  
						<div class="padding">
							<div class="full col-sm-9">
							  
								<!-- content -->                      
								<div class="row">
								  
								  <c:if test="${not empty perfil}">
							  		<div class="col-sm-3">
								   
									  <div class="panel panel-default">
										<div class="panel-thumbnail"><img src="${var.url}/${perfil.foto}" class="img-responsive"></div>
										<div class="panel-body">
										  <p class="lead"><c:out value="${perfil.nombre}"/> <c:out value="${perfil.apellido}"/></p>
										</div>
									  </div>
									</div>
							  	</c:if>
							  	
							  	 <c:if test="${empty perfil}">
							  		<div class="col-sm-3">
								   
									  <div class="panel panel-default">
										<a href="${var.url}/user/configuracion"><div class="panel-thumbnail"><img src="${var.url}/${user.foto}" class="img-responsive"></div></a>
										<div class="panel-body">
										  <p class="lead"><c:out value="${user.nombre}"/> <c:out value="${user.apellido}"/></p>
										  <c:if test="${not empty amigos}"><p>${amigos} Amigos</p></c:if>
										  <c:if test="${pendientes > 0 && not empty pendientes}"><a href="${var.url}/user/amigos"><p style="color:green;">${pendientes} Solicitudes de amistad pendientes</p></a></c:if>
										</div>
									  </div>
									</div>
							  	</c:if>
							  	
								 
									  