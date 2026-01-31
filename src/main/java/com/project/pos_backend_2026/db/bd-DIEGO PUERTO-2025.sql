CREATE DATABASE software_punto_venta;
USE software_punto_venta;

-- 1. TABLAS DE CONTROL Y ACCESO 
-- ------------------------------------------------------------------

-- USUARIOS: Gestiona a los empleados que acceden al sistema
CREATE TABLE USUARIOS (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL, -- La contraseña debe ir cifrada
    nombre_completo VARCHAR(100),
    estado BOOLEAN DEFAULT TRUE
);

-- ROLES: Define los niveles de acceso (Administrador, Vendedor, Supervisor)
CREATE TABLE ROLES (
    id_rol INT PRIMARY KEY AUTO_INCREMENT,
    nombre_rol VARCHAR(50) NOT NULL UNIQUE,
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES USUARIOS(id_usuario)
);

-- PERMISOS: Lista de todas las funcionalidades/módulos del sistema
CREATE TABLE PERMISOS (
    id_permiso INT PRIMARY KEY AUTO_INCREMENT,
    nombre_permiso VARCHAR(100) NOT NULL UNIQUE,
    codigo_modulo VARCHAR(50) UNIQUE -- Ej: 'MOD_VENTAS', 'MOD_INVENTARIO'
);

-- ROL_PERMISOS: Relación muchos a muchos (M:N) entre ROLES y PERMISOS
CREATE TABLE ROL_PERMISOS (
    id_rol_permiso INT PRIMARY KEY AUTO_INCREMENT,
    id_rol INT NOT NULL,
    id_permiso INT NOT NULL,
    UNIQUE KEY (id_rol, id_permiso),
    FOREIGN KEY (id_rol) REFERENCES ROLES(id_rol),
    FOREIGN KEY (id_permiso) REFERENCES PERMISOS(id_permiso)
);

-- EMPRESA: Datos fiscales y de contacto del negocio
CREATE TABLE EMPRESA (
    id_empresa INT PRIMARY KEY AUTO_INCREMENT,
    nombre_legal VARCHAR(150) NOT NULL,
    nit_rut VARCHAR(50) NOT NULL UNIQUE,
    direccion_fiscal VARCHAR(255),
    telefono VARCHAR(20),
    email VARCHAR(100)
);

-- 2. TABLAS DE INVENTARIO Y ABASTECIMIENTO 
-- ------------------------------------------------------------------

-- PROVEEDORES: Información de las empresas que suministran productos
CREATE TABLE PROVEEDORES (
    id_proveedor INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(150) NOT NULL,
    numero_identificacion VARCHAR(50) UNIQUE,
    celular VARCHAR(20),
    direccion VARCHAR(255),
    correo VARCHAR(100)
);

CREATE TABLE TELEFONOS_PROVEEDOR (
    id_telefono INT PRIMARY KEY AUTO_INCREMENT,
    id_proveedor INT NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_proveedor) REFERENCES PROVEEDORES(id_proveedor)
);

CREATE TABLE CORREO_PROVEEDOR (
    id_correo INT PRIMARY KEY AUTO_INCREMENT,
    id_proveedor INT NOT NULL,
    correo VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_proveedor) REFERENCES PROVEEDORES(id_proveedor)
);

-- 1. Crear la tabla incluyendo la descripción
CREATE TABLE CATEGORIAS (
    id_categoria INT PRIMARY KEY AUTO_INCREMENT,
    nombre_categoria VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(255) 
);

-- 2. Insertar los datos correctamente
INSERT INTO CATEGORIAS (nombre_categoria, descripcion) 
VALUES ('Alimentos', 'Productos alimenticios');

-- PRODUCTOS: Inventario central del negocio
CREATE TABLE PRODUCTOS (
    id_producto INT PRIMARY KEY AUTO_INCREMENT,
    nombre_producto VARCHAR(150) NOT NULL,
    precio_venta DECIMAL(10, 2) NOT NULL,
    costo_compra DECIMAL(10, 2),
    stock INT NOT NULL DEFAULT 0,
    id_categoria INT,
    FOREIGN KEY (id_categoria) REFERENCES CATEGORIAS(id_categoria)
);

-- 3. TABLAS DE COMPRAS Y EGRESOS 
-- ------------------------------------------------------------------

-- PEDIDOS_COMPRA: Cabecera de la orden de compra
CREATE TABLE PEDIDOS_COMPRA (
    id_pedido INT PRIMARY KEY AUTO_INCREMENT,
    id_proveedor INT,
    fecha_pedido DATE NOT NULL,
    fecha_recepcion DATE,
    monto_total DECIMAL(10, 2),
    estado_pedido VARCHAR(20),
    FOREIGN KEY (id_proveedor) REFERENCES PROVEEDORES(id_proveedor)
);

-- DETALLE_COMPRA: Productos incluidos en un pedido de compra
CREATE TABLE DETALLE_COMPRA (
    id_detalle_compra INT PRIMARY KEY AUTO_INCREMENT,
    id_pedido INT,
    id_producto INT,
    cantidad_comprada INT NOT NULL,
    costo_unitario DECIMAL(10, 2) NOT NULL,
    subtotal_compra DECIMAL(10, 2),
    FOREIGN KEY (id_pedido) REFERENCES PEDIDOS_COMPRA(id_pedido),
    FOREIGN KEY (id_producto) REFERENCES PRODUCTOS(id_producto)
);

-- GASTOS: Registro de egresos operativos
CREATE TABLE GASTOS (
    id_gasto INT PRIMARY KEY AUTO_INCREMENT,
    concepto VARCHAR(255) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    entidad VARCHAR(150),
    fecha DATE NOT NULL,
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES USUARIOS(id_usuario)
);

-- 4. TABLAS DE CLIENTES Y VENTAS 
-- ------------------------------------------------------------------

-- CLIENTES: Información de los compradores
CREATE TABLE CLIENTES (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    numero_identificacion VARCHAR(50) UNIQUE,
    celular VARCHAR(20),
    direccion VARCHAR(255),
    correo VARCHAR(100)
);

CREATE TABLE TELEFONOS_CLIENTE (
    id_telefono_cliente INT PRIMARY KEY AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES CLIENTES(id_cliente)
);

CREATE TABLE CORREO_CLIENTE (
    id_correo_cliente INT PRIMARY KEY AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    correo VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES CLIENTES(id_cliente)
);

-- VENTAS: Cabecera de la factura de venta
CREATE TABLE VENTAS (
    id_venta INT PRIMARY KEY AUTO_INCREMENT,
    fecha_venta DATE NOT NULL,
    hora_venta TIME,
    total_pagar DECIMAL(10, 2) NOT NULL,
    estado VARCHAR(20) DEFAULT 'Pagada',
    id_cliente INT,
    id_usuario INT,
    FOREIGN KEY (id_cliente) REFERENCES CLIENTES(id_cliente),
    FOREIGN KEY (id_usuario) REFERENCES USUARIOS(id_usuario)
);

-- DETALLE_VENTA: Productos incluidos en la factura de venta
CREATE TABLE DETALLE_VENTA (
    id_detalle INT PRIMARY KEY AUTO_INCREMENT,
    id_venta INT,
    id_producto INT,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_venta) REFERENCES VENTAS(id_venta),
    FOREIGN KEY (id_producto) REFERENCES PRODUCTOS(id_producto)
);





















