Feature: PublicacionVista
	
	Scenario: Cambio de _id
    	Given Tengo una publicacionVista inicializada
    	When Cambio el id a "58521d861f"
    	Then El id es "58521d861f"
    	
    Scenario: Cambio de nickname
    	Given Tengo una publicacionVista inicializada
    	When Cambio el nickname a "pepe.pepe"
    	Then El nickname es "pepe.pepe"
    	
    Scenario: Cambio de texto
    	Given Tengo una publicacionVista inicializada
    	When Cambio el texto a "Hola Mundo"
    	Then El text es "Hola Mundo"
    	
    Scenario: Cambio de privacidad
    	Given Tengo una publicacionVista inicializada
    	When Cambio la privacidad a "publico"
    	Then La privacidad es "publico"
    	
    Scenario: Cambio de fecha
    	Given Tengo una publicacionVista inicializada
    	When Cambio la fecha a "01/01/2020"
    	Then La fecha es "01/01/2020"
    	
    Scenario: Cambio de nombre
	    Given Tengo una publicacionVista inicializada
	    When Cambio el nombre a "Eduardo"
	    Then El nombre es "Eduardo"
	    
	Scenario: Cambio de apellido
    	Given Tengo una publicacionVista inicializada
    	When Cambio de apellido a "Parra"
    	Then El apellido es "Parra"