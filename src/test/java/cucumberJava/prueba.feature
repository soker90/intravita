@featureTest
Feature: Prueba

@Scenario1
Scenario: Crear usuario
	Given Tengo un objeto usuario
	When creo un DAOUsario y insert
	Then usuario insertado
	
