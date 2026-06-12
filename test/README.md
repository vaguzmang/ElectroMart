## Busqueda Avanzada de Productos

El sistema ElectroMart incluye una funcionalidad de Busqueda Avanzada de Productos disponible desde el menu principal con la opcion:

```text
7. Busqueda Avanzada de Productos
```

Esta opcion esta disponible para los tres roles del sistema:

- Administrador
- Gerente de Inventario
- Operador de Pedidos

### Criterios de busqueda

El sistema solicita tres datos al usuario:

1. Categoria del producto.
2. Precio minimo.
3. Precio maximo.

Las categorias validas son:

```text
COMPUTADORA
ELECTRODOMESTICO
```

La busqueda usa el precio base del producto.

### Validaciones

- El usuario puede escribir la categoria en mayusculas o minusculas.
- Si escribe una categoria inexistente, el sistema muestra las categorias disponibles.
- El precio minimo y maximo aceptan enteros y decimales.
- Se aceptan decimales con punto o coma.
- Si el usuario escribe texto en lugar de numero, el sistema muestra una alerta y vuelve a preguntar.
- El precio maximo no puede ser menor que el precio minimo.
- En cada paso se puede escribir `0` para volver atras.

### Resultado

El sistema muestra una tabla enumerada con los productos que cumplen simultaneamente:

- Categoria exacta.
- Precio base mayor o igual al precio minimo.
- Precio base menor o igual al precio maximo.

Si no hay resultados, muestra un mensaje indicando que no se encontro ningun producto y permite:

```text
1. Realizar otra busqueda
0. Volver al menu principal
```

### Lambda y Stream API

La logica de filtrado se implementa con expresiones Lambda y Stream API de Java:

```java
List<Producto> productosFiltrados = productos.stream()
        .filter(producto -> obtenerCategoriaProducto(producto).equalsIgnoreCase(categoria))
        .filter(producto -> producto.getPrecioBase() >= precioMinimo)
        .filter(producto -> producto.getPrecioBase() <= precioMaximo)
        .collect(Collectors.toList());
```
