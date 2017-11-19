@featureTest
Feature: userEditar

En este test se va a desarrollar toda la parte correspondiente al panel
de control de los usuarios de la aplicacion. Desde borrar cuenta, editar informacion
del usuario, cambiar la password y modificar la imagen. 

@Scenario1
Scenario: borrar cuenta
	Given Un usuario registrado en la aplicacion
	When borra cuenta
	Then usuario no existe en la base de datos