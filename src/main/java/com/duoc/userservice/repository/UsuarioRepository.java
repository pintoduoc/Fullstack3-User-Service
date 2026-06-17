package com.duoc.userservice.repository;

import com.duoc.userservice.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByRut(String rut);
    List<Usuario> findByRol(Usuario.Rol rol);
}
