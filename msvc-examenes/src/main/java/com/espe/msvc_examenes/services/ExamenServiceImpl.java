package com.espe.msvc_examenes.services;

import com.espe.msvc_examenes.repositories.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.espe.msvc_examenes.models.entity.Examen;
import com.espe.msvc_examenes.models.Curso;
import com.espe.msvc_examenes.clients.CursoClientRest;
import java.util.List;
import java.util.Optional;

@Service
public class ExamenServiceImpl implements ExamenService {

        @Autowired
        private ExamenRepository examenRepository;

        @Autowired
        private CursoClientRest cursoClientRest;

        @Override
        public Examen guardar(Examen examen) {
                // Validar que el cursoId exista
                Curso curso = cursoClientRest.detalle(examen.getCursoId());
                if (curso == null) {
                        throw new IllegalArgumentException("Curso con ID " + examen.getCursoId() + " no existe");
                }
                return examenRepository.save(examen);
        }

        @Override
        @Transactional(readOnly = true)
        public Optional<Examen> porId(Long id) {
                return examenRepository.findById(id);
        }

        @Override
        @Transactional
        public void eliminar(Long id) {
                examenRepository.deleteById(id);
        }

        @Override
        @Transactional(readOnly = true)
        public List<Examen> listar() {
                return (List<Examen>) examenRepository.findAll();
        }
}

