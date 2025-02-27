package com.espe.msvc.usuarios.services;

import com.espe.msvc.usuarios.models.entity.Usuario;
import java.util.List;
import java.util.Optional;
//interfaz para el servicio de usuario
public interface UsuarioService {
    List<Usuario> listar();
    Optional<Usuario> porId(long id);
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);
}
