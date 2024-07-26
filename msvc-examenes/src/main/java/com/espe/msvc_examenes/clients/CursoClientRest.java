package com.espe.msvc_examenes.clients;

import org.springframework.cloud.openfeign.FeignClient;
import com.espe.msvc_examenes.models.Curso;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.GetMapping;
@FeignClient(name = "msvc-cursos", url = "localhost:8002")
public interface CursoClientRest {
    // endpoints
    // endpoint para obtener todos los cursos
    @GetMapping("/api/cursos/detalle/{id}")
    Curso detalle(@PathVariable Long id);
    // endpoint para crear un curso
    @PostMapping("/api/cursos/crear")
    Curso crear(@RequestBody Curso curso);
    // endpoint para editar un curso
    @DeleteMapping("/api/cursos/eliminar/{id}")
    void eliminar(@PathVariable Long id);
}
