package dev.bibliotecaduoc.bibliotecaduoc.controller;

import dev.bibliotecaduoc.bibliotecaduoc.model.Prestamo;
import dev.bibliotecaduoc.bibliotecaduoc.service.PrestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/prestamos")
public class PrestamoController {

    private final PrestamoService service;

    public PrestamoController(PrestamoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Prestamo>> listar() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable int id) {
        return service.obtenerPorId(id)
                .<ResponseEntity<?>>map(prestamo -> ResponseEntity.ok(prestamo))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensaje", "No encontrado")));
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Prestamo p) {
        try {
            Prestamo creado = service.guardar(p);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable int id, @RequestBody Prestamo p) {
        try {
            return service.actualizar(id, p)
                    .<ResponseEntity<?>>map(actualizado -> ResponseEntity.ok(actualizado))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(Map.of("mensaje", "No encontrado")));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        boolean eliminado = service.eliminar(id);

        if (eliminado) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("mensaje", "No encontrado"));
    }
}