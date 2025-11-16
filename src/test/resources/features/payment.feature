Feature: PaymentRepository

  Scenario: Guardar y Buscar un Pago
    Given no existe el Pago
    When registro un pago
    Then el pago debería estar registrado