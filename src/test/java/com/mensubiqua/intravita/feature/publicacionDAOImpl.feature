Feature: PublicacionDAOImpl

	Scenario: Insertar una publicacion
		Given Tengo un PublicacionDAOImpl inicializado
		When Inserto una publicacion
		Then Publicacion insertada
		
	Scenario: Borrar una publicacion
		Given Tengo un PublicacionDAOImpl inicializado
		When Borro una publicacion
		Then La publicacion es null