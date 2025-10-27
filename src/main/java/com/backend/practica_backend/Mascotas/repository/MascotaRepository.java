package com.backend.practica_backend.Mascotas.repository;

import com.backend.practica_backend.Mascotas.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByNombreContainingIgnoreCase(String nombre);
}
