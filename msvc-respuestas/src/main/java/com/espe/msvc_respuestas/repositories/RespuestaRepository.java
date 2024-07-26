package com.espe.msvc_respuestas.repositories;

import com.espe.msvc_respuestas.models.entity.Respuesta;
import org.springframework.data.repository.CrudRepository;
// repositorio para las respuestas
public interface RespuestaRepository extends CrudRepository<Respuesta, Long> {
}
