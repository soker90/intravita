@featuretest
Feature: amistades

Se va a probar la historia de usuario correspondiente a las amistades, las solicitudes de amistad
 y los escenarios alternativos que se generan
 
@Scenario1
Scenario: Enviar nueva solicitud de amistad de  "pepe" a "juan"
	Given usuario1 "pepe"
	And usuario2 "juan"
	When pepe envia solicitud a juan
	Then juan tiene solicitud pendiente
	
@Scenario2
Scenario: Revocar una solicitud de amistad
	Given usuario1 "pepe"
	And solicitud de amistad
	When pepe revoca la solicitud
	Then solicitud no existe
	
@Scenario3
Scenario: Rechazar solicitud de amistad "juan" a "pepe"
	Given usuario2 "juan"
	And solicitud de amistad
	When juan rechaza la solicitud
	Then solicitud no existe
	
@Scenario4
Scenario: Aceptar solicitud de amistad "juan" a "pepe"
	Given usuario2 "juan"
	And solicitud de amistad
	When juan acepta la solicitud
	Then solicitud existe