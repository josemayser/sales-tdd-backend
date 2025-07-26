Feature: Registro de cliente
Scenario: Registrar cliente con correo inválido
  Given que el grupo de cliente con ID "b5db4881-54b1-4c64-a2aa-0c79f59d22b0" existe
  And un cliente con nombre "Juan Pérez", código "JP001", dni "12345678", y email "correo-invalido" desea registrarse
  When intento registrar al cliente
  Then debería recibir un error indicando que el email no es válido