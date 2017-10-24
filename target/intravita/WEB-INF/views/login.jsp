<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title> Login | Intravita </title>

</head>

<body onload='document.loginForm.username.focus();'>

<div class="top-content">

    <div class="inner-bg">
        <div class="container">

            <div class="row">
                <div class="col-sm-5">

                    <div class="form-box">
                        <div class="form-top">
                            <div class="form-top-left">
                                <h3>Inicar sesión</h3>
                                <p style="color: red;">
                                    <c:if test="${not empty mensaje2}">
                                        <div class="msg">${mensaje2}</div>
                                    </c:if>
                                </p>
                            </div>
                            <div class="form-top-right">
                                <i class="fa fa-key"></i>
                            </div>
                        </div>
                        <div class="form-bottom">
                            <form name='loginForm' role="form" action="logear" method="post" class="login-form">
                                <div class="form-group">
                                    <label class="sr-only" for="form-username">Usuario</label>
                                    <input type="text" name="username" placeholder="Usuario" class="form-username form-control" id="form-username">
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="form-password">Contraseña</label>
                                    <input type="password" name="password" placeholder="Contraseña" class="form-password form-control" id="form-password">
                                </div>
                                <button id="form-login" type="submit" name="submit" value="submit" class="btn">Entrar</button>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            </form>
                        </div>
                    </div>

                </div>

                <div class="col-sm-1 middle-border"></div>
                <div class="col-sm-1"></div>

                <div class="col-sm-5">

                    <div class="form-box">
                        <div class="form-top">
                            <div class="form-top-left">
                                <h3>Registrarse</h3>
                                <p style="color: red;">
                                    <c:if test="${not empty mensaje}">
                                <div class="error">${mensaje}</div>
                                </c:if>
                                </p>
                            </div>
                            <div class="form-top-right">
                                <i class="fa fa-pencil"></i>
                            </div>
                        </div>
                        <div class="form-bottom">
                            <form role="form" action="registro" method="post" class="registration-form">
                                <div class="form-group">
                                    <label class="sr-only" for="form-first-name">Nombre</label>
                                    <input type="text" name="nombre" placeholder="Nombre" class="form-first-name form-control" id="form-first-name"><br>
                                    <label class="sr-only" for="form-last-name">Apellidos</label>
                                    <input type="text" name="apellido" placeholder="Apellidos" class="form-last-name form-control" id="form-last-name"><br>
                                    <label class="sr-only" for="form-email">Email</label><br>
                                    <input type="text" name="email" placeholder="Correo electrónico" class="form-email form-control" id="form-email">
                                    <label class="sr-only" for="form-passwordd">Contraseña</label><br>
                                    <input type="password" name="password" placeholder="Contraseña" class="form-email form-control" id="form-passwordd">
                                    <label class="sr-only" for="form-password2">Repita contraseña</label><br>
                                    <input type="password" name="password2" placeholder="Contraseña" class="form-email form-control" id="form-password2">
                                    </div>
                                <button type="submit" class="btn">Registrarse</button>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                        </div>
                    </div>

                </div>
            </div>

        </div>
    </div>

</div>

</body>

</html>