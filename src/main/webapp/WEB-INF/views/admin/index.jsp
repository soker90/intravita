<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
    <body>
        <h1 id="title">Bienvenido admin ${user.nickname}</h1>

        <c:url value="/j_spring_security_logout" var="logoutUrl" />

        <!-- csrt for log out-->
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
            Bienvenido : ${user.nickname} | <a id="logout"
                                               href="javascript:formSubmit()"> Salir</a>
            <a id="borrarCuenta" href="/borrarCuenta" >Borrar Cuenta</a>
        </h2>
    </body>
</html>
