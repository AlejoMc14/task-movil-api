package com.backend.practica_backend.Productos.controller;

import com.backend.practica_backend.Productos.model.Producto;
import com.backend.practica_backend.Productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:5173") // ajusta según tu frontend
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Listar (opcional: ?q=busqueda por nombre)
    @GetMapping
    public List<Producto> listar(@RequestParam(value = "q", required = false) String q) {
        if (q != null && !q.isBlank()) {
            return productoService.buscarPorNombre(q);
        }
        return productoService.listarProductos();
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtener(@PathVariable Long id) {
        Optional<Producto> producto = productoService.obtenerProducto(id);
        return producto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Crear
    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        Producto creado = productoService.guardarProducto(producto);
        return ResponseEntity.status(201).body(creado);
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        Producto actualizado = productoService.actualizarProducto(id, producto);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!productoService.obtenerProducto(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
