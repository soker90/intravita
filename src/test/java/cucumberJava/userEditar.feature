@featureTest
Feature: userEditar

En este test se va a desarrollar toda la parte correspondiente al panel
de control de los usuarios de la aplicacion. Desde borrar cuenta, editar informacion
del usuario hasta cambiar la password

@Scenario1
Scenario: borrar cuenta
	Given Un usuario registrado en la aplicacion
	When borra cuenta
	Then usuario no existe en la base de datos
	
@Scenario2
Scenario: editar informacion
	Given Un usuario registrado en la aplicacion
	When editar informacion
	Then usuario con datos actualizados
	
@Scenario3
Scenario: cambiar password
	Given Un usuario registrado en la aplicacion
	When inserta nueva password
	Then password actualizada