# proyecto_hilos
Proyecto Hilos Java, Análisis de Algoritmos

# ANALISIS DE ALGORITMOS

Un almacén lleva su sistema de inventarios (productos) y ventas en una base de datos, una matriz o un contenedor de tipo Object, por cada producto se da: id del producto, nombre, valor, existencia actual, familia o categoría, stock mínimo y stock máximo. Se quiere crear los siguientes procesos:

* Proceso que se encargue de rastrear el stock mínimo de cada producto del inventario. Si la existencia actual es igual o inferior al stock mínimo + 10, el proceso debe lanzar una alerta para insertar más elementos de ese producto.

* Proceso que se encargue de producir productos, estos productos no pueden superar al stock máximo, además debe estar pendiente de las
alertas del stock mínimo para surtir.

* Proceso que se encargue de consumir productos, se pueden consumir los productos hasta su stock mínimo + 10, de ahí no puede pasar.
