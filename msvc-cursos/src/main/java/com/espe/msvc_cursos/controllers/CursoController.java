package com.espe.msvc_cursos.controllers;

import com.espe.msvc_cursos.models.entity.Curso;
import com.espe.msvc_cursos.models.Usuario;
import com.espe.msvc_cursos.services.CursoService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.*;
//Menajar cors para permitir peticiones desde el frontend
@CrossOrigin(origins = {"http://localhost:3000"})
//Controlador para los cursos
@RestController
public class CursoController {
    //Inyeccion de dependencias
    @Autowired
    private CursoService cursoService;
    //Metodos para consumir los servicios de cursos
    @GetMapping("/api/cursos")
    public List<Curso> listar() {
        return cursoService.listar();
    }
    //Obtener todos los cursos
    @GetMapping("/api/cursos/detalle/{id}")
    public ResponseEntity<Curso> detalle(@PathVariable Long id) {
        Optional<Curso> curso = cursoService.porId(id);
        if (curso.isPresent()) {
            return new ResponseEntity<>(curso.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Obtener un curso por su id
    @PostMapping("/api/cursos/crear")
    public ResponseEntity<?> guardar(@Valid @RequestBody Curso curso, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }
        return new ResponseEntity<>(cursoService.guardar(curso), HttpStatus.CREATED);
    }
    //Crear un curso
    @PutMapping("/api/cursos/editar/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validar(result);
        }
        Optional<Curso> cursoOptional = cursoService.porId(id);
        if (cursoOptional.isPresent()) {
            Curso cursoActual = cursoOptional.get();
            cursoActual.setNombre(curso.getNombre());
            return new ResponseEntity<>(cursoService.guardar(cursoActual), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Editar un curso
    @DeleteMapping("/api/cursos/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cursoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //Eliminar un curso
    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

    //Asignar un usuario a un curso
    @PutMapping("/api/asignar-usuario/{idCurso}")
    public ResponseEntity<?> asignarUsuario(@RequestBody Usuario usuario, @PathVariable Long idCurso) {
        Optional<Usuario> o;
        try {
            o = cursoService.agregarUsuario(usuario, idCurso);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje", "error: " + e.getMessage()));
        }
        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    // Crear un usuario desde el curso
    @PostMapping("/api/crear-usuario/{idCurso}")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario, @PathVariable Long idCurso) {
        Optional<Usuario> o;
        try {
            o = cursoService.crearUsuario(usuario, idCurso);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("mensaje", "error: " + e.getMessage()));
        }
        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar un usuario de un curso
    @DeleteMapping("/api/eliminar-usuario/{idCurso}")
    public ResponseEntity<?> eliminarUsuario(@RequestBody Usuario usuario, @PathVariable Long idCurso) {
        Optional<Usuario> o;
        try {
            o = cursoService.eliminarUsuario(usuario, idCurso);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("mensaje", "error: " + e.getMessage()));
        }
        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }
}
