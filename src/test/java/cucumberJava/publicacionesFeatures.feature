@featureTest
Feature: publicacionesFeature

En este test se va a desarrollar toda la parte correspondiente al gestion de publicaciones, nueva publicacion
editar una publicacion, cambiar visibilidad y eliminar

@Scenario1
Scenario: crear una nueva publicacion
	Given Un usuario 
	And   escribe publicacion
	When inserta publicacion
	Then publicacion creada
	