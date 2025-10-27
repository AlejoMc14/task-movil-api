package com.backend.practica_backend.Productos.service;

import com.backend.practica_backend.Productos.model.Producto;
import com.backend.practica_backend.Productos.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Listar todos los productos
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    // Obtener por id
    public Optional<Producto> obtenerProducto(Long id) {
        return productoRepository.findById(id);
    }

    // Buscar por nombre (opcional)
    public List<Producto> buscarPorNombre(String q) {
        return productoRepository.findByNombreContainingIgnoreCase(q);
    }

    // Crear / guardar
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Actualizar
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setNombre(productoActualizado.getNombre());
                    producto.setMarca(productoActualizado.getMarca());
                    producto.setModelo(productoActualizado.getModelo());
                    producto.setPrecio(productoActualizado.getPrecio());
                    producto.setDescripcion(productoActualizado.getDescripcion());
                    return productoRepository.save(producto);
                })
                .orElse(null);
    }

    // Eliminar
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
