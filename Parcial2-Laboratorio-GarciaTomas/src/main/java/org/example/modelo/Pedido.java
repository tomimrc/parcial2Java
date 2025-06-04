package org.example.modelo;

import java.time.LocalDate;

public class Pedido {
    private int id;
    private String nombreCliente;
    private int idProducto;
    private LocalDate fecha;
    private int cantidad;

    public Pedido() {}

    public Pedido(int id, String nombreCliente, int idProducto, LocalDate fecha, int cantidad) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.idProducto = idProducto;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", nombreCliente='" + nombreCliente + ", idProducto=" + idProducto + ", fecha=" + fecha + ", cantidad=" + cantidad + '}';
    }
}