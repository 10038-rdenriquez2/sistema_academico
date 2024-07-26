package com.espe.msvc_respuestas.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "respuestas")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String contenido;

    @Column(name = "examen_id", nullable = false)
    private Long examenId;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "pregunta")
    private String pregunta; // Nuevo campo añadido

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotEmpty String getContenido() {
        return contenido;
    }

    public void setContenido(@NotEmpty String contenido) {
        this.contenido = contenido;
    }

    public Long getExamenId() {
        return examenId;
    }

    public void setExamenId(Long examenId) {
        this.examenId = examenId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
}
