Feature: PublicacionVista
	
	Scenario: Cambio el id de PublicacionVista
    	Given Tengo una publicacionVista inicializada
    	When Cambio su id a "58521d861f"
    	Then El id devuelto es "58521d861f"
    	
    Scenario: Cambio de nickname PublicacionVista
    	Given Tengo una publicacionVista inicializada
    	When Cambio el nickname a el nuevo "pepe.pepe"
    	Then El nickname devuelto es "pepe.pepe"
    	
    Scenario: Cambio de texto PublicacionVista
    	Given Tengo una publicacionVista inicializada
    	When Cambio su texto a "Hola Mundo"
    	Then El texto devuelto es "Hola Mundo"
    	
    Scenario: Cambio de fecha PublicacionVista
    	Given Tengo una publicacionVista inicializada
    	When Cambio su fecha a "01/01/2020"
    	Then La fecha devuelta es "01/01/2020"
    	
    Scenario: Cambio de nombre PublicacionVista
	    Given Tengo una publicacionVista inicializada
	    When Cambio su nombre a "Eduardo"
	    Then El nombre devuelto es "Eduardo"
	    
	Scenario: Cambio la foto PublicacionVista
    	Given Tengo una publicacionVista inicializada
    	When Cambio su foto a "edu.parra"
    	Then La foto devuelta es "edu.parra"
    	
    Scenario: Cambio de la privacidad en PublicacionVista
    	Given Tengo una publicacionVista inicializada
    	When Cambio su privacidad a "publico"
    	Then La privacidad devuelta es "publico"
    	
