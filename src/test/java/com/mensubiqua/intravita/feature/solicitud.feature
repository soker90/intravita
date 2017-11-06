Feature: Solicitud

  Scenario: Cambio de solicitante
    Given Tengo una solicitud inicializada
    When Cambio el solicitante a "benito"
    Then El solicitante es "benito"

  Scenario: Cambio de solicitado
    Given Tengo una solicitud inicializada
    When Cambio el solicitado a "benito"
    Then El solicitado es "benito"

  Scenario: Cambio de aceptado
    Given Tengo una solicitud inicializada
    When Cambio de aceptado a "<True>"
    Then El aceptado es "<True>"
