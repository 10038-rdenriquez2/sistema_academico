package com.espe.msvc_examenes.controllers;

import com.espe.msvc_examenes.models.entity.Examen;
import com.espe.msvc_examenes.services.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.*;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/examenes")
//Controlador para los examenes basado en el CRUD de dos modelos
public class ExamenController {
    //Inyeccion de dependencias
    @Autowired
    private ExamenService examenService;
    //Metodo para listar todos los examenes
    @GetMapping
    public List<Examen> listar() {
        return examenService.listar();
    }
    //Metodo para obtener un examen por su id
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Examen> detalle(@PathVariable Long id) {
        Optional<Examen> examen = examenService.porId(id);
        if (examen.isPresent()) {
            return new ResponseEntity<>(examen.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Metodo para crear un examen
    @PostMapping("/crear")
    public ResponseEntity<?> guardar(@Valid @RequestBody Examen examen, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }
        return new ResponseEntity<>(examenService.guardar(examen), HttpStatus.CREATED);
    }
    //Metodo para editar un examen
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Examen examen, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validar(result);
        }
        Optional<Examen> examenOptional = examenService.porId(id);
        if (examenOptional.isPresent()) {
            Examen examenActual = examenOptional.get();
            examenActual.setNombre(examen.getNombre());
            examenActual.setDescripcion(examen.getDescripcion());
            examenActual.setCursoId(examen.getCursoId());
            return new ResponseEntity<>(examenService.guardar(examenActual), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Metodo para eliminar un examen
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        examenService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //Metodo para validar los campos de un examen
    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
