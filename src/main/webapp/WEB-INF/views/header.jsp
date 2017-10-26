<!DOCTYPE html>
<html lang="en">
	<head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8"> 
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link href="/intravita/resources/css/bootstrap.css" rel="stylesheet">
        <!--[if lt IE 9]>
          <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <link href="/intravita/resources/css/facebook.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css" rel="stylesheet">
        
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
							  <div class="navbar-brand logo">I</div>
							</div>
							<nav class="collapse navbar-collapse" role="navigation">
							
							<ul class="nav navbar-nav">
							  <li>
								<a href="/intravita/default"><i class="glyphicon glyphicon-home"></i> Inicio</a>
							  </li>
							  <li>
								<a href="#postModal" role="button" data-toggle="modal"><i class="glyphicon glyphicon-plus"></i> Nueva publicación </a>
							  </li>
							  <c:if test="${user.rol eq 'ROLE_ADMIN'}">
							  	<c:if test="${vista eq 'usuario'}">
								  	<li>
										<a href="/intravita/admin/index"><span class="badge">Usuario</span></a>
								  	</li>
							  	</c:if>
							  	<c:if test="${vista eq 'usuario'}">
								  	<li>
										<a href="/intravita/user/index"><span class="badge">Administrador</span></a>
								  	</li>
							  	</c:if>
							  </c:if>
							  
							  
							</ul>
							<ul class="nav navbar-nav navbar-right">
							  <li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i></a>
								<ul class="dropdown-menu">
								  <li><a href="">Perfil</a></li>
								  <li><a href="/intravita/logout">Salir</a></li>
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
								  
								 <!-- main col left --> 
								 <div class="col-sm-5">
								   
									  <div class="panel panel-default">
										<div class="panel-thumbnail"><img src="/intravita/resources/img/bg_5.jpg" class="img-responsive"></div>
										<div class="panel-body">
										  <p class="lead"><c:out value="${user.nombre}"/> <c:out value="${user.apellido}"/></p>
										  <p>400 Amigos</p>
										</div>
									  </div>
								</div>
									  