package com.espe.msvc_examenes.services;

import com.espe.msvc_examenes.models.entity.Examen;

import java.util.List;
import java.util.Optional;

public interface ExamenService {
    public List<Examen> listar();
    public Optional<Examen> porId(Long id);
    public Examen guardar(Examen examen);
    public void eliminar(Long id);
}
