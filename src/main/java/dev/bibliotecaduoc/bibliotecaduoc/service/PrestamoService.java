package dev.bibliotecaduoc.bibliotecaduoc.service;

import dev.bibliotecaduoc.bibliotecaduoc.model.Libro;
import dev.bibliotecaduoc.bibliotecaduoc.model.Prestamo;
import dev.bibliotecaduoc.bibliotecaduoc.repository.LibroRepository;
import dev.bibliotecaduoc.bibliotecaduoc.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;

    public PrestamoService(PrestamoRepository prestamoRepository, LibroRepository libroRepository) {
        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
    }

    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.findAll();
    }

    public Optional<Prestamo> obtenerPorId(int idPrestamo) {
        return prestamoRepository.findById(idPrestamo);
    }

    public Prestamo guardar(Prestamo prestamo) {
        validarPrestamo(prestamo);
        prestamo.setIdPrestamo(0);

        if (prestamo.getMultas() == null || prestamo.getMultas() < 0) {
            prestamo.setMultas(0);
        }

        return prestamoRepository.save(prestamo);
    }

    public Optional<Prestamo> actualizar(int idPrestamo, Prestamo nuevo) {
        Optional<Prestamo> existente = prestamoRepository.findById(idPrestamo);

        if (existente.isEmpty()) return Optional.empty();

        validarPrestamo(nuevo);

        Prestamo p = existente.get();
        p.setIdLibro(nuevo.getIdLibro());
        p.setRunSolicitante(nuevo.getRunSolicitante());
        p.setFechaSolicitud(nuevo.getFechaSolicitud());
        p.setFechaEntrega(nuevo.getFechaEntrega());
        p.setCantidadDias(nuevo.getCantidadDias());
        p.setMultas(nuevo.getMultas() == null ? 0 : Math.max(nuevo.getMultas(), 0));

        return Optional.of(prestamoRepository.save(p));
    }

    public boolean eliminar(int idPrestamo) {
        return prestamoRepository.deleteById(idPrestamo);
    }

    private void validarPrestamo(Prestamo p) {
        if (p.getIdLibro() == null || p.getIdLibro() <= 0)
            throw new IllegalArgumentException("El id del libro es obligatorio.");

        if (p.getRunSolicitante() == null || p.getRunSolicitante().isBlank())
            throw new IllegalArgumentException("El RUN es obligatorio.");

        if (p.getFechaSolicitud() == null)
            throw new IllegalArgumentException("La fecha es obligatoria.");

        if (p.getCantidadDias() == null || p.getCantidadDias() <= 0)
            throw new IllegalArgumentException("Días inválidos.");

        boolean existe = libroRepository.findAll().stream()
                .map(Libro::getId)
                .anyMatch(id -> id == p.getIdLibro());

        if (!existe)
            throw new IllegalArgumentException("El libro no existe.");
    }
}