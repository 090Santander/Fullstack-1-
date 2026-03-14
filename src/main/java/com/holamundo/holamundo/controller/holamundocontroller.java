package com.holamundo.holamundo.controller;

import org.springframework.http.HttpStatus;  
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/productos") // Ruta base para tu CRUD
public class holamundocontroller {

    // Lista temporal para guardar datos mientras el servidor esté encendido
    private List<String> productos = new ArrayList<>(List.of("Laptop", "Mouse", "Teclado"));

    // 1. READ (Leer todo) - Responde 200 OK
    @GetMapping
    public ResponseEntity<List<String>> obtenerProductos() {
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    // 2. CREATE (Crear) - Responde 201 Created
    @PostMapping
    public ResponseEntity<String> crearProducto(@RequestBody String nuevoNombre) {
        productos.add(nuevoNombre);
        return new ResponseEntity<>("Producto creado: " + nuevoNombre, HttpStatus.CREATED);
    }

    // 3. UPDATE (Actualizar) - Responde 200 OK
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable int id, @RequestBody String nombreNuevo) {
        if (id >= 0 && id < productos.size()) {
            productos.set(id, nombreNuevo);
            return new ResponseEntity<>("Actualizado con éxito", HttpStatus.OK);
        }
        return new ResponseEntity<>("No encontrado", HttpStatus.NOT_FOUND);
    }

    // 4. DELETE (Borrar) - Responde 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        if (id >= 0 && id < productos.size()) {
            productos.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}