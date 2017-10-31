Feature: UserDAOImpl

Scenario: Insertar un usuario
	Given Tengo un UserDAOImpl inicializado
	When Inserto un usuario
	Then Usuario insertado
	
Scenario: Borrar un usuario
	Given Tengo un UserDAOImpl inicializado
	When Borro un usuario
	Then El usuario es null