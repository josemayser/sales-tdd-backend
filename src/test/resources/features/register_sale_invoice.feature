Feature: Registro de factura de venta
Scenario: No se puede registrar una factura de venta si el taxName es nulo
  Given que el grupo de cliente con ID "b5db4881-54b1-4c64-a2aa-0c79f59d22b0" existe
  And que la solicitud de factura tiene todos los campos válidos excepto el taxName que es nulo
  When intento registrar la factura de venta
  Then se lanza una excepción con el mensaje "Tax name cannot be null"