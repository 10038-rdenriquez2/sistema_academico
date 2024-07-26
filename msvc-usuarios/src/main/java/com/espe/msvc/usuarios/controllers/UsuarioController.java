package com.espe.msvc.usuarios.controllers;

import com.espe.msvc.usuarios.models.entity.Usuario;
import com.espe.msvc.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.Optional;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
// habilite el cors para que el front pueda acceder a los recursos
@CrossOrigin(origins = {"http://localhost:3000"})
// anotacion para que sea un controlador rest
@RestController
public class UsuarioController {
    // inyeccion de dependencias
        @Autowired
        private UsuarioService usuarioService;
    // metodos
        @GetMapping("/api/usuarios")
        public List<Usuario> listar() {
            return usuarioService.listar();
        }
    //endpoints para el crud
    //endpint para obtener un usuario por id
        @GetMapping("/api/usuarios/detalle/{id}")
        public ResponseEntity<?> detalle(@PathVariable Long id) {
            Optional<Usuario> usuarioOptional = usuarioService.porId(id);
            if (usuarioOptional.isPresent()) {
                return ResponseEntity.ok().body(usuarioOptional.get());
            }
            return ResponseEntity.notFound().build();
        }
    //endpint para crear un usuario
        @PostMapping("/api/usuarios/crear")
        public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario, BindingResult result) {
            if (result.hasErrors()) {
                return validar(result);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(usuario));
        }
    //metodo para validar los campos
        private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errores);
        }
    //endpint para editar un usuario
        @PutMapping("/api/usuarios/editar/{id}")
        public ResponseEntity<?> editar(@Valid @RequestBody Usuario usuario,BindingResult result, @PathVariable Long id) {
            if (result.hasErrors()) {
                return validar(result);
            }
            Optional<Usuario> usuarioOptional = usuarioService.porId(id);
            if (usuarioOptional.isPresent()) {
                Usuario usuarioActual = usuarioOptional.get();
                usuarioActual.setNombre(usuario.getNombre());
                usuarioActual.setEmail(usuario.getEmail());
                usuarioActual.setPassword(usuario.getPassword());
                return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(usuarioActual));
            }
            return ResponseEntity.notFound().build();
        }
    //endpint para eliminar un usuario
        @DeleteMapping("/api/usuarios/eliminar/{id}")
        public ResponseEntity<?> eliminar(@PathVariable Long id) {
            Optional<Usuario> usuarioOptional = usuarioService.porId(id);
            if (usuarioOptional.isPresent()) {
                usuarioService.eliminar(id);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        }

}
