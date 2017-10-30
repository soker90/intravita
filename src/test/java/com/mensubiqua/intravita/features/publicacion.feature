Feature: Publicacion
	
	Scenario: Cambio de _id
    	Given Tengo una publicacion inicializada
    	When Cambio el _id a "58521d861f"
    	Then El _id es "58521d861f"
    	
    Scenario: Cambio de nickname
    	Given Tengo una publicacion inicializada
    	When Cambio el nickname a "pepe.pepe"
    	Then El nickname es "pepe.pepe"
    	
    Scenario: Cambio de texto
    	Given Tengo una publicacion inicializada
    	When Cambio el texto a "Hola Mundo"
    	Then El text es "Hola Mundo"
    	
    Scenario: Cambio de privacidad
    	Given Tengo una publicacion inicializada
    	When Cambio la privacidad a "publico"
    	Then La privacidad es "publico"
    	
    Scenario: Cambio de fecha
    	Given Tengo una publicacion inicializada
    	When Cambio la fecha a "01/01/2020"
    	Then La fecha es "01/01/2020"