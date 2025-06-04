package org.example.dao.impl;

import org.example.modelo.Pedido;
import org.example.util.ConexionBD;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOClass implements GenericInterfaceDAO<Pedido, Integer> {

    @Override
    public void crear(Pedido pedido) {
        String sql = "INSERT INTO pedido (nombre_cliente, id_producto, fecha, cantidad) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pedido.getNombreCliente());
            stmt.setInt(2, pedido.getIdProducto());
            stmt.setDate(3, Date.valueOf(pedido.getFecha()));
            stmt.setInt(4, pedido.getCantidad());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pedido buscarPorId(Integer id) {
        String sql = "SELECT * FROM pedido WHERE id = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Pedido(
                        rs.getInt("id"),
                        rs.getString("nombre_cliente"),
                        rs.getInt("id_producto"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getInt("cantidad")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Pedido> listarTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido";
        try (Connection conn = ConexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                pedidos.add(new Pedido(
                        rs.getInt("id"),
                        rs.getString("nombre_cliente"),
                        rs.getInt("id_producto"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getInt("cantidad")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    @Override
    public void actualizar(Pedido pedido) {
        String sql = "UPDATE pedido SET nombre_cliente = ?, id_producto = ?, fecha = ?, cantidad = ? WHERE id = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pedido.getNombreCliente());
            stmt.setInt(2, pedido.getIdProducto());
            stmt.setDate(3, Date.valueOf(pedido.getFecha()));
            stmt.setInt(4, pedido.getCantidad());
            stmt.setInt(5, pedido.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM pedido WHERE id = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                System.out.println("No se encontró ningún pedido con ese ID.");
            } else {
                System.out.println("Pedido eliminado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}