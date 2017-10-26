<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
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
