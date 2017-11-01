Feature: UserDAOImpl

Scenario: Insertar un usuario
	Given Tengo un UserDAOImpl inicializado
	When Inserto un usuario
	Then Usuario insertado
	
Scenario: Borrar un usuario
	Given Tengo un UserDAOImpl inicializado
	When Borro un usuario
	Then El usuario es null
	
Scenario: Encontrar un usario 
	Given Tengo un UserDAOImpl inicializado
	When busco un usuario
	Then Tengo un usuario
	
Scenario: Seleccionar todos los usuarios
	Given Tengo un UserDAOImpl inicializado
	When Busco todos los usuarios en la base de datos
	Then Tengo todos los usuarios 
	
Scenario: Actualizar usuario
	Given Tengo un UserDAOImpl inicializado
	When Actualizo un usuario
	Then El usuario actualizado
	
Scenario: Actualizar Role
	Given Tengo un UserDAOImpl inicializado
	When Actualizo rol
	Then Rol actualizado
	
Scenario: Actualizar contrasena
	Given Tengo un UserDAOImpl inicializado
	When Actualizo contrasena de un usuario
	Then Contrasena actualizada
	
Scenario: Actualizar validacion
	Given Tengo un UserDAOImpl inicializado
	When Actualizo validacion de usuario
	Then Validacion de usuario actualizada
	