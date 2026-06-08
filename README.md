# ElectroMart

ElectroMart es un sistema de gestión para una tienda de tecnología y electrodomésticos, desarrollado en Java 17 bajo un enfoque de Programación Orientada a Objetos y una estructura inicial basada en Modelo - Vista - Controlador.

El sistema permite administrar usuarios, roles, productos, clientes, pedidos, control de stock y reportes básicos desde consola.

## Tecnologías utilizadas

* Java 17
* Apache NetBeans
* Git y GitHub
* Programación Orientada a Objetos
* Arquitectura Modelo - Vista - Controlador
* Manejo de listas con `ArrayList`
* Entrada de datos por consola con `Scanner`

## Arquitectura del proyecto

El proyecto está organizado en paquetes:

```text
electromart
├── controller
│   └── SistemaController.java
│
├── model
│   ├── Producto.java
│   ├── Computadora.java
│   ├── Electrodomestico.java
│   ├── Cliente.java
│   ├── Pedido.java
│   ├── DetallePedido.java
│   ├── Usuario.java
│   ├── Rol.java
│   └── EstadoPedido.java
│
├── view
│   └── MenuConsola.java
│
└── ElectroMart.java
```

## Descripción de la arquitectura MVC

### Modelo

El paquete `model` contiene las clases que representan los datos y reglas principales del sistema.

Ejemplos:

* `Producto`
* `Computadora`
* `Electrodomestico`
* `Cliente`
* `Pedido`
* `DetallePedido`
* `Usuario`
* `Rol`
* `EstadoPedido`

Aquí se manejan datos como productos, clientes, pedidos, roles, estados, precios, stock y cálculos.

### Vista

El paquete `view` contiene la clase `MenuConsola`, encargada de mostrar el menú principal al usuario.

La vista se encarga de presentar información, pero no contiene la lógica principal del negocio.

### Controlador

El paquete `controller` contiene la clase `SistemaController`, encargada de procesar las acciones del usuario.

Desde esta clase se gestionan operaciones como:

* Mostrar usuarios
* Mostrar productos
* Mostrar clientes
* Mostrar pedidos
* Registrar productos
* Registrar clientes
* Crear pedidos
* Mostrar reportes
* Validar entradas numéricas
* Login de usuarios

## Funcionalidades implementadas

### Usuarios y roles

El sistema maneja usuarios con roles definidos mediante un `enum`.

Roles disponibles:

* `ADMINISTRADOR`
* `GERENTE_INVENTARIO`
* `OPERADOR_PEDIDOS`

Usuarios de prueba:

```text
Usuario: admin
Password: 1234
Rol: ADMINISTRADOR

Usuario: inventario
Password: 1234
Rol: GERENTE_INVENTARIO

Usuario: pedidos
Password: 1234
Rol: OPERADOR_PEDIDOS
```

### Login

El sistema cuenta con un login básico por consola.

Características:

* Solicita usuario y contraseña.
* Permite hasta 3 intentos.
* Si las credenciales son correctas, muestra el menú según el rol.
* Si se superan los intentos fallidos, el sistema finaliza.

### Menú por rol

El menú muestra opciones según el rol del usuario autenticado.

El administrador puede acceder a todas las funciones principales.

El gerente de inventario puede gestionar productos y stock.

El operador de pedidos puede gestionar clientes y pedidos.

### Productos

El sistema maneja productos mediante herencia.

Clase base:

* `Producto`

Clases hijas:

* `Computadora`
* `Electrodomestico`

Cada tipo de producto tiene atributos específicos.

Computadora:

* Procesador
* RAM

Electrodoméstico:

* Consumo energético
* Garantía en meses

### Clientes

El sistema permite registrar y listar clientes.

Datos principales del cliente:

* ID
* Nombre
* Email
* Teléfono

### Pedidos

El sistema permite crear pedidos desde el menú.

Un pedido contiene:

* ID
* Cliente
* Fecha
* Estado
* Lista de detalles

Cada detalle de pedido contiene:

* Producto
* Cantidad
* Precio unitario
* Subtotal

### Control de stock

El sistema valida el stock antes de crear un pedido.

Si la cantidad solicitada supera el stock disponible, el sistema muestra un mensaje de error.

Si la venta es válida, descuenta el stock del producto.

### Reportes básicos

El sistema cuenta con reportes generales:

* Cantidad de pedidos
* Total de ventas
* Productos registrados
* Clientes registrados
* Productos con stock bajo

También muestra una tabla con los productos que tienen stock bajo.

### Validaciones de entrada

Se agregaron validaciones para evitar errores cuando el usuario escribe letras, símbolos o valores inválidos en campos numéricos.

Ejemplos de campos validados:

* Precio base
* Stock
* RAM
* Garantía
* ID de cliente
* Cantidad del pedido
* Opciones del menú

## Opciones principales del menú

```text
1. Ver usuarios
2. Ver productos
3. Ver clientes
4. Ver pedidos
5. Ver permisos
6. Ver stock final
8. Registrar computadora
9. Registrar cliente
10. Registrar electrodoméstico
11. Crear pedido
12. Ver reportes
13. Acerca del sistema
99. Cerrar sesión
0. Salir
```

Las opciones visibles pueden cambiar según el rol del usuario.

## Estado actual del proyecto

El proyecto actualmente funciona con datos en memoria usando `ArrayList`.

Esto significa que los datos se pueden registrar, consultar y modificar mientras el programa está en ejecución, pero no se guardan permanentemente al cerrar el sistema.

## Pendiente por implementar

La siguiente etapa del proyecto será integrar base de datos.

Pendientes:

* Diseñar tablas SQL
* Crear `schema.sql`
* Crear `data.sql`
* Conectar Java con MySQL usando JDBC
* Crear clases DAO o Repository
* Cargar usuarios, productos y clientes desde base de datos
* Guardar nuevos registros en base de datos

## Cómo ejecutar el proyecto

1. Abrir el proyecto en Apache NetBeans.
2. Verificar que el proyecto use Java 17.
3. Ejecutar la clase principal:

```text
ElectroMart.java
```

4. Iniciar sesión con alguno de los usuarios de prueba.
5. Usar el menú según el rol del usuario.

## Autor

Proyecto desarrollado por Ing. Vitor A. Guzman G. como ejercicio académico para aplicar Programación Orientada a Objetos, Java 17, MVC básico, control 
de stock, manejo de pedidos y preparación para integración con base de datos.

## Base de datos

El proyecto incluye integración con base de datos MySQL/MariaDB usando JDBC.

La base de datos está definida en la carpeta:

```text
database
```

Archivos incluidos:

```text
schema.sql
data.sql
```

### schema.sql

Este archivo crea la base de datos `electromart_db` y sus tablas principales:

* roles
* usuarios
* clientes
* productos
* pedidos
* detalle_pedido

### data.sql

Este archivo carga datos iniciales para pruebas:

* 3 roles principales
* 50 usuarios
* 50 clientes
* 50 productos
* 50 pedidos
* 50 detalles de pedido

Los roles principales del sistema son:

* ADMINISTRADOR
* GERENTE_INVENTARIO
* OPERADOR_PEDIDOS

### Conexión JDBC

La conexión a la base de datos se maneja desde la clase:

```text
electromart.config.ConexionBD
```

Configuración usada en entorno local con XAMPP/phpMyAdmin:

```text
URL: jdbc:mysql://localhost:3306/electromart_db
Usuario: root
Password: vacío
```

### DAO implementados

El proyecto incluye clases DAO para separar el acceso a datos:

```text
electromart.dao.ProductoDAO
electromart.dao.ClienteDAO
electromart.dao.UsuarioDAO
electromart.dao.PedidoDAO
```

Estas clases consultan la base de datos y convierten los registros en objetos Java del modelo.

### Integración actual con MySQL

Actualmente el sistema carga desde MySQL:

* usuarios
* productos
* clientes
* pedidos
* detalles de pedidos

El login utiliza usuarios cargados desde la base de datos.

Las opciones de consulta del menú muestran información obtenida desde MySQL.

Las operaciones de registro desde consola todavía pueden trabajar principalmente en memoria, pero el proyecto ya cuenta con la estructura necesaria para extender los DAO y guardar nuevos registros directamente en MySQL.

### Ejecución de scripts SQL

Para preparar la base de datos se deben ejecutar los archivos en este orden:

```text
1. database/schema.sql
2. database/data.sql
```

Primero se crea la estructura de tablas y luego se insertan los datos iniciales.

### Requisitos para base de datos

* XAMPP instalado
* Apache iniciado
* MySQL iniciado
* phpMyAdmin disponible en `http://localhost/phpmyadmin`
* MySQL Connector/J agregado a las librerías del proyecto
