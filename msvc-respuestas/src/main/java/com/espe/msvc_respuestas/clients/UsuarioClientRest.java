package com.espe.msvc_respuestas.clients;

import org.springframework.cloud.openfeign.FeignClient;
import com.espe.msvc_respuestas.models.Usuario;
import org.springframework.web.bind.annotation.*;
// cliente feign para consumir el servicio de usuarios
@FeignClient(name = "msvc-usuarios", url = "localhost:8001")
public interface UsuarioClientRest {
    // metodo para obtener el detalle de un usuario
    @GetMapping("/api/usuarios/detalle/{id}")
    Usuario detalle(@PathVariable Long id);
}
