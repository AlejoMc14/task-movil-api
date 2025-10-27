package com.backend.practica_backend.Mascotas.controller;

import com.backend.practica_backend.Mascotas.model.Mascota;
import com.backend.practica_backend.Mascotas.repository.MascotaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mascotas")
@CrossOrigin(origins = "http://localhost:5173") // ajusta origen si tu frontend corre en otra URL/puerto
public class MascotaController {

    private final MascotaRepository repo;

    public MascotaController(MascotaRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Mascota> listar(@RequestParam(value = "q", required = false) String q) {
        if (q != null && !q.isBlank()) {
            return repo.findByNombreContainingIgnoreCase(q);
        }
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> obtener(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mascota crear(@RequestBody Mascota mascota) {
        return repo.save(mascota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascota> actualizar(@PathVariable Long id, @RequestBody Mascota mascota) {
        return repo.findById(id)
                .map(m -> {
                    m.setNombre(mascota.getNombre());
                    m.setEspecie(mascota.getEspecie());
                    m.setRaza(mascota.getRaza());
                    m.setEdad(mascota.getEdad());
                    return ResponseEntity.ok(repo.save(m));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
