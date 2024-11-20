package com.aluracursos.adopet.api.controller;

import com.aluracursos.adopet.api.dto.ActualizacionTutorDTO;
import com.aluracursos.adopet.api.dto.RegistroTutorDTO;
import com.aluracursos.adopet.api.excepcion.ValidacionException;
import com.aluracursos.adopet.api.repository.TutorRepository;
import com.aluracursos.adopet.api.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private TutorService tutorService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> registrar(@RequestBody @Valid RegistroTutorDTO dto) {
        try {
            tutorService.registrar(dto);
            return ResponseEntity.ok().build();
        }catch (
            ValidacionException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }


    @PutMapping
    @Transactional
    public ResponseEntity<String> actualizar(@RequestBody @Valid ActualizacionTutorDTO dto) {
        tutorService.actualizar(dto);
        return  ResponseEntity.ok().build();
    }

}
