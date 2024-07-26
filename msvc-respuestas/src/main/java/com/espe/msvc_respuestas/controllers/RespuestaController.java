package com.espe.msvc_respuestas.controllers;

import com.espe.msvc_respuestas.models.entity.Respuesta;
import com.espe.msvc_respuestas.services.RespuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.*;
// habilitar cors para permitir la comunicacion con el frontend
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
//endopoint para cinsumir desde un frontend
@RequestMapping("/api/respuestas")
public class RespuestaController {
    //inyeccion de dependencia para el servicio de respuestas
    @Autowired
    private RespuestaService respuestaService;
    //metodo para listar todas las respuestas
    @GetMapping
    public List<Respuesta> listar() {
        return respuestaService.listar();
    }
    //metodo para obtener el detalle de una respuesta
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Respuesta> detalle(@PathVariable Long id) {
        Optional<Respuesta> respuesta = respuestaService.porId(id);
        if (respuesta.isPresent()) {
            return new ResponseEntity<>(respuesta.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //metodo para guardar una respuesta
    @PostMapping("/crear")
    public ResponseEntity<?> guardar(@Valid @RequestBody Respuesta respuesta, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }
        return new ResponseEntity<>(respuestaService.guardar(respuesta), HttpStatus.CREATED);
    }
    //metodo para editar una respuesta
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Respuesta respuesta, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validar(result);
        }
        Optional<Respuesta> respuestaOptional = respuestaService.porId(id);
        if (respuestaOptional.isPresent()) {
            Respuesta respuestaActual = respuestaOptional.get();
            respuestaActual.setContenido(respuesta.getContenido());
            respuestaActual.setExamenId(respuesta.getExamenId());
            respuestaActual.setUsuarioId(respuesta.getUsuarioId());
            respuestaActual.setPregunta(respuesta.getPregunta()); // Actualizar el nuevo campo
            return new ResponseEntity<>(respuestaService.guardar(respuestaActual), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //metodo para eliminar una respuesta
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        respuestaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //metodo para validar los campos de la respuesta
    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
