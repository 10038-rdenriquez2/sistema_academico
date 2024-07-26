package com.espe.msvc_cursos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import com.espe.msvc_cursos.models.Usuario;
import org.springframework.web.bind.annotation.*;
//Endpoint para consumir los servicios de usuarios
@FeignClient(name = "msvc-usuarios", url = "localhost:8001")
public interface UsuarioClientRest {
 //Metodos para consumir los servicios de usuarios
    //Obtener todos los usuarios
    @GetMapping("/api/usuarios/detalle/{id}")
    Usuario detalle(@PathVariable Long id);
    //Obtener un usuario por su id
    @PostMapping("/api/usuarios/crear")
    Usuario crear(@RequestBody Usuario usuario);
    //Crear un usuario
    @DeleteMapping("/api/usuarios/eliminar/{id}")
    void eliminar(@PathVariable Long id);

}
