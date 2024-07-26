package com.espe.msvc_respuestas.services;

import com.espe.msvc_respuestas.models.entity.Respuesta;

import java.util.List;
import java.util.Optional;
// interfaz para el servicio de respuestas
public interface RespuestaService {
    List<Respuesta> listar();
    Optional<Respuesta> porId(Long id);
    Respuesta guardar(Respuesta respuesta);
    void eliminar(Long id);
}

