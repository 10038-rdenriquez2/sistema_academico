package com.espe.msvc_respuestas.clients;

import org.springframework.cloud.openfeign.FeignClient;
import com.espe.msvc_respuestas.models.Examen;
import org.springframework.web.bind.annotation.*;
//cliente feign para consumir el servicio de examenes
@FeignClient(name = "msvc-examenes", url = "localhost:8003") // Ajusta el URL según tu configuración
public interface ExamenClientRest {
    //metodo para obtener el detalle de un examen
    @GetMapping("/api/examenes/detalle/{id}")
    Examen detalle(@PathVariable("id") Long id);
}
