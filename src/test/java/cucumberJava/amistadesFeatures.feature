@featuretest
Feature: amistades

Se va a probar la historia de usuario correspondiente a las amistades, las solicitudes de amistad
 y los escenarios alternativos que se generan
 
@Scenario1
Scenario: Enviar nueva solicitud de amistad de pepe a juan
	Given primer usuario "pepe"
	And segundo usuario "juan"
	When "pepe" envia solicitud a "juan"
	Then "juan" tiene solicitud pendiente
	
@Scenario2
Scenario: Revocar una solicitud de amistad
	Given primer usuario "pepe"
	And segundo usuario "juan"
	And "pepe" envia solicitud a "juan"
	When "pepe" revoca la solicitud a "juan"
	Then "juan" no tiene solicitudes pendientes
	
@Scenario3
Scenario: Rechazar solicitud de amistad de pepe a juan
	Given primer usuario "pepe"
	And segundo usuario "juan"
	And "pepe" envia solicitud a "juan"
	When "juan" rechaza la solicitud
	Then "juan" no tiene solicitudes pendientes
	
@Scenario4
Scenario: Aceptar solicitud de amistad pepe a juan
	Given primer usuario "pepe"
	And segundo usuario "juan"
	And "pepe" envia solicitud a "juan"
	When "juan" acepta la solicitud
	Then "pepe" y "juan" son amigos