package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {
    //Modificar Nombre BD,usuario y contraseña
    //Script creacion BD

    /*
   Script Base de datos
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



     */
    private static final String URL = "jdbc:mysql://localhost:3306/empresa_pedidos";
    private static final String USER = "root";
    private static final String PASSWORD = "40972941";

    private static final Logger LOGGER = Logger.getLogger(ConexionBD.class.getName());

    public static Connection obtenerConexion() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al conectar con la base de datos", e);
            throw new RuntimeException(e);
        }
    }
}
