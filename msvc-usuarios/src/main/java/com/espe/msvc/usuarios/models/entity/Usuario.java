package com.espe.msvc.usuarios.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotEmpty
        private String nombre;

        @NotEmpty
        @Email
        @Column(unique = true)
        private String email;

        @NotEmpty
        private String password;

        public Usuario() {
        }

        public Usuario(Long id, String nombre, String email, String password) {
            this.id = id;
            this.nombre = nombre;
            this.email = email;
            this.password = password;
        }

        public Long getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "Usuario{" +
                    "id=" + id +
                    ", nombre='" + nombre + '\'' +
                    ", email='" + email + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
}
