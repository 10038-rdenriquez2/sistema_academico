package com.espe.msvc_examenes.repositories;

import com.espe.msvc_examenes.models.entity.Examen;
import org.springframework.data.repository.CrudRepository;
// repository para la entidad Examen
public interface ExamenRepository extends CrudRepository<Examen, Long> {
}
