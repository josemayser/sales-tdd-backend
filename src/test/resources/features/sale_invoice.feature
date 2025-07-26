Feature: Registro de factura de venta
Scenario: Calcular correctamente descuentos combinados
  Given un cliente del grupo "Especialista" con 8.00% de descuento
  And un producto del grupo "Herramientas" con 3.00% de descuento y precio 50.00
  When se registra una factura con 2 unidades del producto
  Then el total de la factura debe ser 89.00
  And el total del descuento aplicado debe ser 11.00