package org.example.dao.impl;

import java.util.List;

public interface GenericInterfaceDAO<T, ID> {
    void crear(T entidad);
    T buscarPorId(ID id);
    List<T> listarTodos();
    void actualizar(T entidad);
    void eliminar(ID id);
}
