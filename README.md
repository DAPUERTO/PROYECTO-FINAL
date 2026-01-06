# POS Backend - Sistema de Punto de Venta

## Descripción
Este repositorio contiene el código fuente del backend para el sistema "Software Punto de Venta". El proyecto está desarrollado en Java puro utilizando JDBC para la conexión con la base de datos MySQL.

El objetivo del sistema es gestionar las operaciones de un punto de venta, incluyendo inventario, ventas y administración.

## Tecnologías Utilizadas
- **Lenguaje**: Java
- **Base de Datos**: MySQL
- **Conectividad**: JDBC (Java Database Connectivity)
- **IDE Recomendado**: Visual Studio Code

## Estructura del Proyecto
El proyecto sigue una arquitectura organizada en paquetes dentro de `src/`:

- `config`: Contiene la configuración de la base de datos (`Conexiondb.java`).
- `models`: (Pendiente) Clases que representan las entidades del negocio.
- `dao`: (Pendiente) Data Access Objects para la interacción con la BD.
- `services`: (Pendiente) Lógica de negocio.
- `controllers`: (Pendiente) Controladores de la aplicación.
- `lib`: Librerías y dependencias externas.
- `bin`: Archivos binarios compilados.

## Configuración

### 1. Base de Datos
El sistema espera una base de datos MySQL local.
- **Nombre de la BD**: `software_punto_venta`
- **Puerto**: `3306`

### 2. Credenciales
Actualmente, las credenciales de conexión están definidas en `src/config/Conexiondb.java`.
- **Usuario**: `root`
- **Contraseña**: *Configurada en el código*

> **Nota**: Se recomienda refactorizar la gestión de credenciales para usar variables de entorno en futuras versiones.

## Ejecución
Para probar la conexión a la base de datos:
1. Abra el archivo `src/App.java`.
2. Ejecute el método `main`.
3. Si la conexión es exitosa, verá el mensaje de conexión y el objeto de conexión en la consola.
