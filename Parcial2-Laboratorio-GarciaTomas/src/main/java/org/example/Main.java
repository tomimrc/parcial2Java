package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dao.impl.GenericInterfaceDAO;
import org.example.dao.impl.PedidoDAOClass;
import org.example.dao.impl.ProductoDAOClass;
import org.example.modelo.Pedido;
import org.example.modelo.Producto;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());

    private static final GenericInterfaceDAO<Producto, Integer> productoDAO = new ProductoDAOClass();
    private static final GenericInterfaceDAO<Pedido, Integer> pedidoDAO = new PedidoDAOClass();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            System.out.print("Elija una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> crearProducto(scanner);
                case "2" -> listarProductos();
                case "3" -> eliminarProducto(scanner);
                case "4" -> crearPedido(scanner);
                case "5" -> listarPedidos();
                case "6" -> eliminarPedido(scanner);
                case "7" -> salir = true;
                default -> LOGGER.info("Opción inválida. Intente de nuevo.");
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n-- Menú --");
        System.out.println("1. Crear producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Eliminar producto");
        System.out.println("4. Crear pedido");
        System.out.println("5. Listar pedidos");
        System.out.println("6. Eliminar pedido");
        System.out.println("7. Salir");
    }

    private static void crearProducto(Scanner scanner) {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción del producto: ");
        String categoria = scanner.nextLine();
        System.out.print("Precio del producto: ");
        double precio = scanner.nextInt();

        if (nombre.isBlank() || categoria.isBlank()) {
            LOGGER.warn("Nombre y descripción no pueden estar vacíos.");
            return;
        }

        Producto producto = new Producto(0, nombre, categoria,precio);
        productoDAO.crear(producto);
        LOGGER.info("Producto creado exitosamente.");
    }
    private static void crearPedido(Scanner scanner) {
        System.out.print("Nombre del cliente: ");
        String nombreCliente = scanner.nextLine();

        listarProductos();
        System.out.print("ID del producto: ");
        String idProductoStr = scanner.nextLine();

        System.out.print("Cantidad: ");
        String cantidadStr = scanner.nextLine();

        if (nombreCliente.isBlank() || idProductoStr.isBlank() || cantidadStr.isBlank()) {
            LOGGER.warn("Todos los campos son obligatorios.");
            return;
        }

        try {
            int idProducto = Integer.parseInt(idProductoStr);
            int cantidad = Integer.parseInt(cantidadStr);
            Pedido pedido = new Pedido(0, nombreCliente, idProducto, LocalDate.now(), cantidad);
            pedidoDAO.crear(pedido);
            LOGGER.info("Pedido creado exitosamente.");
        } catch (NumberFormatException e) {
            LOGGER.warn("ID de producto o cantidad inválida.");
        }
    }

    private static void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        if (productos.isEmpty()) {
            LOGGER.info("No hay productos disponibles.");
        } else {
            productos.forEach(System.out::println);
        }
    }
    private static void listarPedidos() {
        List<Pedido> pedidos = pedidoDAO.listarTodos();
        if (pedidos.isEmpty()) {
            LOGGER.info("No hay pedidos disponibles.");
        } else {
            pedidos.forEach(System.out::println);
        }
    }
    private static void eliminarProducto(Scanner scanner) {
        System.out.print("ID del producto: ");
        String id = scanner.nextLine();
        if (id.isBlank()) {
            LOGGER.warn("Debe ingresar un ID.");
            return;
        }
        productoDAO.eliminar(Integer.parseInt(id));
    }

    private static void eliminarPedido(Scanner scanner) {
        System.out.print("ID del pedido: ");
        String id = scanner.nextLine();
        if (id.isBlank()) {
            LOGGER.warn("Debe ingresar un ID.");
            return;
        }
        pedidoDAO.eliminar(Integer.parseInt(id));
    }
}