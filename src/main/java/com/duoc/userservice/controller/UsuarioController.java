package com.duoc.userservice.controller;

import com.duoc.userservice.model.Usuario;
import com.duoc.userservice.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

     //Obtener todos los usuarios. No se requiere ningún parámetro ni cuerpo para esta consulta.
    @GetMapping
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    //Obtener usuario por id. Se debe enviar el id del usuario en la URL. (Ejemplo: /api/usuario/1)
    @GetMapping("/{id}")
    public Usuario findById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    //Obtener usuario por rut. Se debe enviar el rut del usuario en la URL. (Ejemplo: /api/usuario/rut/12345678-9)
    @GetMapping("/rut/{rut}")
    public Usuario findByRut(@PathVariable String rut) {
        return usuarioService.findByRut(rut);
    }

    //Obtener usuarios por rol. Se debe enviar el rol del usuario en la URL. (Ejemplo: /api/usuario/rol?rol=BRIGADISTA)
    @GetMapping("/rol")
    public List<Usuario> findByRol(@RequestParam Usuario.Rol rol) {
        return usuarioService.findByRol(rol);
    }

    //Crear usuario. Se debe enviar el usuario en el cuerpo de la solicitud.
    //El cuerpo puede contener todos los campos del usuario, excepto el id.
    //Los campos de usuario a ingresar son: rut, nombreCompleto, contacto, rol (CIUDADANO/BRIGADISTA/ADMINISTRADOR) y (opcionalmente) el id de la brigada a la que pertenece.
    //Ejemplo de cuerpo de solicitud:
    /*
    {
        "rut": "12345678-9",
        "nombreCompleto": "Juan Alberto Pérez Soto",
        "contacto": "be.pintor@duocuc.cl y 930182210",
        "rol": "BRIGADISTA",
        "brigada": {
            "id": 1
        }
    }
    */
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    //Actualizar usuario por id. Se debe enviar el id del usuario a actualizar en la URL y el usuario actualizado en el cuerpo de la solicitud (sin id en el cuerpo).
    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        return usuarioService.save(usuario);
    }

    //Eliminar usuario por id. Se debe enviar el id del usuario a eliminar en la URL. (Ejemplo: /api/usuario/1)
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
    }
}
