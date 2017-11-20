@featureTest
Feature: publicacionesFeature

En este test se va a desarrollar toda la parte correspondiente al gestion de publicaciones, nueva publicacion
editar una publicacion, eliminar una publicacion ya creada y eliminar todas las publicaciones de un usuario eliminado

@Scenario1
Scenario: crear una nueva publicacion
	Given Un usuario 
	And   escribe publicacion
	When inserta publicacion
	Then publicacion creada
	
@Scenario2
Scenario: borrar una publicacion
	Given Un usuario 
	And   publicacion creada
	When borra publicacion
	Then publicacion no existe en bd
	
@Scenario3
Scenario: editar una publicacion
	Given Un usuario 
	And   publicacion creada
	When edita publicacion
	Then publicacion modificada
	

@Scenario4
Scenario: eliminar publicaciones de un usuario eliminado
	Given Un usuario "pepe"
	And publicaciones creadas
	When Borra usuario "pepe"
	Then no hay publicaciones de usuario "pepe"
