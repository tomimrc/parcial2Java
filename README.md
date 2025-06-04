# parcial2Java

## Proyecto: CRUD de Productos y Pedidos
Aplicación en Java que se conecta a una base de datos MySQL para gestionar productos y pedidos. Permite crear, leer, actualizar y eliminar registros de manera sencilla.

### 🛠 Tecnologías utilizadas
Java 17+

MySQL

JDBC

Gradle

IDE sugerido: IntelliJ / Eclipse / NetBeans

--

### 🗃️ Funcionalidades principales
Listar productos

Listar pedidos

Crear nuevos productos/pedidos

Eliminar productos/pedidos
--

### ⚙️ Requisitos
Java 17 o superior

MySQL Server (local o remoto)

Conexión JDBC configurada (url, user, password)

--

#### Configuración inicial


Clonar el repositorio:
git clone https://github.com/tomimrc/parcial2Java.git

--

#### Crear la base de datos:

sql

-- Crear base de datos
CREATE DATABASE IF NOT EXISTS empresa_pedidos;
USE empresa_pedidos;

-- Tabla: producto
CREATE TABLE producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    precio DECIMAL(10,2) NOT NULL
);

-- Tabla: pedido
CREATE TABLE pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_cliente VARCHAR(100) NOT NULL,
    id_producto INT NOT NULL,
    fecha DATE NOT NULL,
    cantidad INT NOT NULL,
    FOREIGN KEY (id_producto) REFERENCES producto(id));


--

#### Configurar los parámetros de conexión 

private static final String URL = "jdbc:mysql://localhost:3306/empresa_pedidos";
private static final String USER = "";
private static final String PASSWORD = "tu_contraseña";
