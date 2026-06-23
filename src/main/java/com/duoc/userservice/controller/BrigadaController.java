package com.duoc.userservice.controller;

import com.duoc.userservice.model.Brigada;
import com.duoc.userservice.service.BrigadaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brigada")
public class BrigadaController {
    @Autowired
    private BrigadaService brigadaService;

    //Obtener todas las brigadas. No se requiere ningún parámetro ni cuerpo para esta consulta.
    @GetMapping
    public List<Brigada> findAll() {
        return brigadaService.findAll();
    }

    //Obtener brigada por id. Se debe enviar el id de la brigada en la URL. (Ejemplo: /api/brigada/1)
    @GetMapping("/{id}")
    public Brigada findById(@PathVariable Long id) {
        return brigadaService.findById(id);
    }

    //Crear brigada. Se debe enviar la brigada en el cuerpo de la solicitud.
    //El cuerpo puede contener todos los campos de la brigada, excepto el id.
    //Los campos de la brigada a ingresar son: nombre y sector
    //Ejemplo de cuerpo de solicitud:
    /*
    {
        "nombre": "Brigada de Rescate",
        "sector": "Temuco"
    }
    */
    @PostMapping
    public Brigada createBrigada(@RequestBody Brigada brigada) {
        return brigadaService.save(brigada);
    }

    //Actualizar brigada por id. Se debe enviar el id de la brigada a actualizar en la URL y la brigada actualizada en el cuerpo de la solicitud (sin id en el cuerpo).
    @PutMapping("/{id}")
    public Brigada updateBrigada(@PathVariable Long id, @RequestBody Brigada brigada) {
        brigada.setId(id);
        return brigadaService.save(brigada);
    }

    //Eliminar brigada por id. Se debe enviar el id de la brigada a eliminar en la URL. (Ejemplo: /api/brigada/1)
    @DeleteMapping("/{id}")
    public void deleteBrigada(@PathVariable Long id) {
        brigadaService.deleteById(id);
    }
}
