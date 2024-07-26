package com.espe.msvc_cursos.services;

import com.espe.msvc_cursos.models.Usuario;
import com.espe.msvc_cursos.models.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    //Metodos para consumir los servicios de cursos
    //Obtener todos los cursos
    List<Curso> listar();
    //Obtener un curso por su id
    Optional<Curso> porId(Long id);
    //Crear un curso
    Curso guardar(Curso curso);
    //Editar un curso
    void eliminar(Long id);
    //Eliminar un curso
    Optional<Usuario> agregarUsuario(Usuario usuario, Long idCurso);
    Optional<Usuario> crearUsuario(Usuario usuario, Long idCurso);

    //Eliminar un usuario
    Optional<Usuario> eliminarUsuario(Usuario usuario, Long idCurso);
}