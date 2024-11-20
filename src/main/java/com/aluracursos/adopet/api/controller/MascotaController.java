package com.aluracursos.adopet.api.controller;

import com.aluracursos.adopet.api.dto.DatosDetallesMascota;
import com.aluracursos.adopet.api.dto.MascotaDto;
import com.aluracursos.adopet.api.model.Mascota;
import com.aluracursos.adopet.api.repository.MascotaRepository;
import com.aluracursos.adopet.api.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaRepository repository;

    @Autowired
    private MascotaService service;


    @GetMapping
    public ResponseEntity<List<MascotaDto>> listarTodasDisponibles() {

        List<MascotaDto> mascotas = service.buscarMascotasDisponibles();
        return ResponseEntity.ok(mascotas);
    }

}
