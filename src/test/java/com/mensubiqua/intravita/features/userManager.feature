Feature: UserManger

  Scenario: Cambio de nombre
    Given Tengo un userManager inicializado
    When Cambio introduzco los usuarios
      | Nombre | Apellidos     | Username  | Password |
      |Carlos  | Cano Pena     | cpena     | 123      |
      |Paco    | Rota Domingo  | prota     | 456      |
      |Carmen  | Santiago Pita | csantiago | 789      |
    Then Se guardan los usarios
      | Nombre | Apellidos     | Username  | Password |
      |Carlos  | Cano Pena     | cpena     | 123      |
      |Paco    | Rota Domingo  | prota     | 456      |
      |Carmen  | Santiago Pita | csantiago | 789      |