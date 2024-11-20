package com.aluracursos.adopet.api.service;

import com.aluracursos.adopet.api.dto.MascotaDto;
import com.aluracursos.adopet.api.dto.RefugioDto;
import com.aluracursos.adopet.api.dto.RegistroRefugioDTO;
import com.aluracursos.adopet.api.model.Refugio;
import com.aluracursos.adopet.api.repository.MascotaRepository;
import com.aluracursos.adopet.api.repository.RefugioRepository;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class RefugioService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private RefugioRepository refugioRepository;


    public List<RefugioDto> listarRefugios() {
        return refugioRepository
                .findAll()
                .stream()
                .map(RefugioDto::new)
                .toList();
    }
    public void registrar(@Valid RegistroRefugioDTO dto) {

        boolean nombreYaRegistrado = refugioRepository.existsByNombre(dto.nombre());
        boolean telefonoYaRegistrado = refugioRepository.existsByTelefono(dto.telefono());
        boolean emailYaRegistrado = refugioRepository.existsByEmail(dto.email());

        if (nombreYaRegistrado || telefonoYaRegistrado || emailYaRegistrado) {
            throw new ValidationException("Datos ya registrados para otro refugio!");
        }
        refugioRepository.save(new Refugio(dto));
    }

   @GetMapping("/{idONombre}/mascotas")
    public List<MascotaDto> listarMascotasDelRefugio(String idONombre) {
        Refugio refugio = cargarRefugio(idONombre);
        return  mascotaRepository
                .findByRefugio(refugio)
                .stream()
                .map(MascotaDto::new)
                .toList();
        }
   public Refugio cargarRefugio(String idONombre) {
        Optional<Refugio> optional;
        try {
            Long id = Long.parseLong(idONombre);
            optional = refugioRepository.findById(id);
        }catch (NumberFormatException exception){
            optional = refugioRepository.findbyNombre(idONombre);
        }
       return optional.orElseThrow(()-> new ValidationException("Refugio no encontrado"));
       }
}
