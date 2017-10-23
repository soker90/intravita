Feature: User

  Scenario: Cambio de nombre
    Given Tengo un usuario inicializado
    When Cambio el nombre a "Eduardo"
    Then El nombre es "Eduardo"

  Scenario: Cambio de nombre
    Given Tengo un usuario inicializado
    When Cambio el nombre a "Miguel"
    Then El nombre es "Miguel"

  Scenario: Cambio de apellido
    Given Tengo un usuario inicializado
    When Cambio de apellido a "Parra"
    Then El apellido es "Parra"

  Scenario: Cambio de apellido
    Given Tengo un usuario inicializado
    When Cambio de apellido a "Sánchez"
    Then El apellido es "Sánchez"

  Scenario: Cambio de username
    Given Tengo un usuario inicializado
    When Cambio de username a "rocky"
    Then El username es "rocky"

  Scenario: Cambio de username
    Given Tengo un usuario inicializado
    When Cambio de username a "balboa"
    Then El username es "balboa"

  Scenario: Cambio de contraseña
    Given Tengo un usuario inicializado
    When Cambio de contraseña a "pojo"
    Then La contraseña es "pojo"

  Scenario: Cambio de contraseña
    Given Tengo un usuario inicializado
    When Cambio de contraseña a "balboa"
    Then La contraseña es "balboa"