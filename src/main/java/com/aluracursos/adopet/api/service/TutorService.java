package com.aluracursos.adopet.api.service;

import com.aluracursos.adopet.api.dto.ActualizacionTutorDTO;
import com.aluracursos.adopet.api.dto.RegistroTutorDTO;
import com.aluracursos.adopet.api.model.Tutor;
import com.aluracursos.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TutorService {
    @Autowired
    TutorRepository tutorRepository;

    public ResponseEntity<String> registrar(RegistroTutorDTO dto) {

        boolean yaRegistrado= tutorRepository.existsByTelefonoOrByEmail(dto.telefono(),dto.email());

        if (yaRegistrado) {
            return ResponseEntity.badRequest().body("Datos ya registrados para otro tutor!");
        } else {
            tutorRepository.save(new Tutor(dto));
            return ResponseEntity.ok().build();
        }
    }
    public void actualizar(ActualizacionTutorDTO dto) {
        Tutor tutor=tutorRepository.getReferenceById(dto.id());
        tutor.actualizarDatos(dto);
    }
}
