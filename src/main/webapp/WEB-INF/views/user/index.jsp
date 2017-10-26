<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<c:set var="user" value="${user}" scope="request" /> <!-- Esto envia variables a las vistas de los includes -->
<c:set var="vista" value="usuario" scope="request" />
<%@ include file="/WEB-INF/views/header.jsp" %>
<!-- <html>
<html>
    <body>
        <h1 id="title">Bienvenido usuario ${user.nombre}</h1>
            <h2>
                Bienvenido : ${user.nickname} | <a id="logout"
                    href="../logout"> Salir</a>
                <a id="borrarCuenta" href="../borrarCuenta" >Borrar Cuenta</a>
            </h2>
    </body>
</html>
    <body>
        <h1 id="title">Bienvenido usuario ${user.nickname}</h1>

        <c:url value="/j_spring_security_logout" var="logoutUrl" />

        <form action="${logoutUrl}" method="post" id="logoutForm">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}" />
        </form>

        <script>
            function formSubmit() {
                document.getElementById("logoutForm").submit();
            }
        </script>

            <h2>
                Bienvenido : ${user} | <a id="logout"
                    href="javascript:formSubmit()"> Salir</a>
                <a id="borrarCuenta" href="/intravita/borrarCuenta" >Borrar Cuenta</a>
                <p style="color: red;">
                	<c:if test="${not empty mensaje}">
                		<div class="msg">${mensaje}</div>
                	</c:if>
                </p>
            </h2>
    </body>
</html> -->


					  
								  
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
