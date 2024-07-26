package com.espe.msvc_respuestas.services;

import com.espe.msvc_respuestas.clients.ExamenClientRest;
import com.espe.msvc_respuestas.clients.UsuarioClientRest;
import com.espe.msvc_respuestas.models.Examen;
import com.espe.msvc_respuestas.models.Usuario;
import com.espe.msvc_respuestas.models.entity.Respuesta;
import com.espe.msvc_respuestas.repositories.RespuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RespuestaServiceImpl implements RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private ExamenClientRest examenClientRest;

    @Autowired
    private UsuarioClientRest usuarioClientRest;

    @Override
    @Transactional(readOnly = true)
    public List<Respuesta> listar() {
        return (List<Respuesta>) respuestaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Respuesta> porId(Long id) {
        return respuestaRepository.findById(id);
    }

    @Override
    @Transactional
    public Respuesta guardar(Respuesta respuesta) {
        // Validar que el examen existe
        Optional<Examen> examen = Optional.ofNullable(examenClientRest.detalle(respuesta.getExamenId()));
        if (!examen.isPresent()) {
            throw new RuntimeException("El examen con ID " + respuesta.getExamenId() + " no existe.");
        }

        // Validar que el usuario existe
        Optional<Usuario> usuario = Optional.ofNullable(usuarioClientRest.detalle(respuesta.getUsuarioId()));
        if (!usuario.isPresent()) {
            throw new RuntimeException("El usuario con ID " + respuesta.getUsuarioId() + " no existe.");
        }

        return respuestaRepository.save(respuesta);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        respuestaRepository.deleteById(id);
    }
}
