<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
    <body>
        <h1 id="title">Bienvenido admin ${user.nickname}</h1>

        <h2>
                Bienvenido : ${user.nickname} | <a id="logout"
                    href="../logout"> Salir</a>
                <a id="borrarCuenta" href="../borrarCuenta" >Borrar Cuenta</a>
        </h2>
        
        <p><a href="/intravita/user/index">Ir a vista de usuario</a></p>
 -        <br/>
 -		
 -		<table>
 -		<tr>
 -			<th>Nombre</th>
 -			<th>Apellidos</th>
 -			<th>Admin</th>
 -		</tr>
 -		<c:forEach var="listVar" items="${listName}">
 -			<tr>
 -			    <td><c:out value="${listVar[0]}"/></td>
 -			    <td><c:out value="${listVar[1]}"/></td>
 -			    <td><a href="<c:out value="${listVar[3]}"/>"><button type="button" class="btn btn-primary"><c:out value="${listVar[2]}"/></button></a></td>
 -		  	</tr>
 -		</c:forEach>
 -		</table>
        
    </body>
</html>
