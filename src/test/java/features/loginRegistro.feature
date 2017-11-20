@featureTest
Feature: loginRegistro

Se va a probar la historia de usuario correspondiente al registro de un nuevo
usuario y los escenarios alternativos que se generan

@Scenario1
Scenario: registrar un usuario no existente
	Given Un nuevo usuario inserta sus datos
	When se comprueba en la bbdd
	Then usuario creado
	
@Scenario2
Scenario: registrar un usuario ya existente
	Given Un usuario inserta sus datos
	When buscar que no exista
	Then usuario no registrado
	
@Scenario3
Scenario: password1 y password2 no coinciden
	Given Un usuario inserta sus datos
	When password no coinciden
	Then usuario no registrado
	
@Scenario4
Scenario: Acceder con cuenta ya creada
	Given nickname y pass
	When ya esta registrado
	Then acceso permitido
	
@Scenario5
Scenario: Acceder con password incorrecto
	Given nickname y password
	When password incorrecta
	Then acceso denegado
	
@Scenario6
Scenario: Accedor sin registro
	Given nickname y password
	When usuario no registro
	Then acceso denegado
	
	
	