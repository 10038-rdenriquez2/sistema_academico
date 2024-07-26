package com.espe.msvc_cursos.services;

import com.espe.msvc_cursos.clients.UsuarioClientRest;
import com.espe.msvc_cursos.models.Usuario;
import com.espe.msvc_cursos.models.entity.Curso;
import com.espe.msvc_cursos.models.entity.CursoUsuario;
import com.espe.msvc_cursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
//Implementacion de los metodos para consumir los servicios de cursos
@Service
public class CursoServiceImpl implements CursoService{
    //Inyeccion de dependencias
    @Autowired
    private CursoRepository cursoRepository;
    //Inyeccion de dependencias
    @Autowired
    UsuarioClientRest usuarioClientRest;
    //Metodos para consumir los servicios de cursos
    @Override
    @Transactional(readOnly = true)
    public List<Curso> listar() {
        return (List<Curso>) cursoRepository.findAll();
    }
    //Obtener todos los cursos
    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> porId(Long id) {
        return cursoRepository.findById(id);
    }
    //Obtener un curso por su id
    @Override
    @Transactional
    public Curso guardar(Curso curso) {
        return cursoRepository.save(curso);
    }
    //Crear un curso
    @Override
    @Transactional
    public void eliminar(Long id) {
        cursoRepository.deleteById(id);
    }
    //Eliminar un curso
    @Override
    @Transactional
    public Optional<Usuario> agregarUsuario(Usuario usuario, Long idCurso) {
        //verificar que exista el curso}
        Optional<Curso> microCurso = cursoRepository.findById(idCurso);
        if (microCurso.isPresent()) {
            //verificar que exista el usuario
            Usuario usuarioMicro = usuarioClientRest.detalle(usuario.getId());

            //agregar el usuario al curso
            Curso curso = microCurso.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioMicro.getId());

            curso.addCursoUsuario(cursoUsuario);
            cursoRepository.save(curso);
        }
        return Optional.empty();
    }
    //Agregar un usuario
    @Override
    @Transactional
    public Optional<Usuario> crearUsuario(Usuario usuario, Long idCurso) {
        Optional<Curso> o = cursoRepository.findById(idCurso);
        if (o.isPresent()) {
            Usuario usuarioMicro = usuarioClientRest.crear(usuario);

            Curso curso = o.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioMicro.getId());

            curso.addCursoUsuario(cursoUsuario);
            cursoRepository.save(curso);

            return Optional.of(usuarioMicro);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> eliminarUsuario(Usuario usuario, Long idCurso) {
        Optional<Curso> o = cursoRepository.findById(idCurso);
        if (o.isPresent()) {
            Usuario usuarioMicro = usuarioClientRest.detalle(usuario.getId());

            Curso curso = o.get();
            CursoUsuario cursoUsuario = curso.getCursoUsuarios().stream()
                    .filter(cu -> cu.getUsuarioId().equals(usuarioMicro.getId()))
                    .findFirst()
                    .orElse(null);

            if (cursoUsuario != null) {
                curso.removeCursoUsuario(cursoUsuario); // Remove the relationship
                cursoRepository.save(curso); // Save the course without the relationship
                return Optional.of(usuarioMicro);
            }
        }
        return Optional.empty();
    }
}