package com.espe.msvc_cursos.repositories;

import com.espe.msvc_cursos.models.entity.Curso;
import org.springframework.data.repository.CrudRepository;
//Repositorio para los cursos
public interface CursoRepository extends CrudRepository<Curso, Long> {
}