@featureTest
Feature: loginRegistro

Se va a probar la historia de usuario correspondiente al registro de un nuevo
usuario y los escenarios alternativos que se generan

@Scenario1
Scenario: registrar un usuario no existente
	Given Un nuevo usuario inserta sus datos
	When se comprueba en la bbdd
	Then usuario creado
	
