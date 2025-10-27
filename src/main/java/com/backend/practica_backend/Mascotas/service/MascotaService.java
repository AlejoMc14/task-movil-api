package com.backend.practica_backend.Mascotas.service;

import com.backend.practica_backend.Mascotas.model.Mascota;
import com.backend.practica_backend.Mascotas.repository.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {

    private final MascotaRepository mascotaRepository;

    public MascotaService(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    // Listar todas las mascotas
    public List<Mascota> obtenerTodas() {
        return mascotaRepository.findAll();
    }

    // Obtener por ID
    public Optional<Mascota> obtenerPorId(Long id) {
        return mascotaRepository.findById(id);
    }

    // Crear
    public Mascota crearMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    // Actualizar
    public Mascota actualizarMascota(Long id, Mascota mascotaActualizada) {
        return mascotaRepository.findById(id)
                .map(mascota -> {
                    mascota.setNombre(mascotaActualizada.getNombre());
                    mascota.setEspecie(mascotaActualizada.getEspecie());
                    mascota.setRaza(mascotaActualizada.getRaza());
                    mascota.setEdad(mascotaActualizada.getEdad());
                    return mascotaRepository.save(mascota);
                })
                .orElse(null);
    }

    // Eliminar
    public void eliminarMascota(Long id) {
        mascotaRepository.deleteById(id);
    }
}
