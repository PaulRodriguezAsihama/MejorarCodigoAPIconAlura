package com.aluracursos.adopet.api.model;

import com.aluracursos.adopet.api.dto.RefugioDto;
import com.aluracursos.adopet.api.dto.RegistroRefugioDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "refugios")
public class Refugio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String telefono;

    private String email;

    @OneToMany(mappedBy = "refugio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("refugio_mascotas")
    private List<Mascota> mascotas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Refugio refugio = (Refugio) o;
        return Objects.equals(id, refugio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public Refugio() {
    }
    public Refugio(@Valid RegistroRefugioDTO dto){
        this.nombre= dto.nombre();
        this.telefono= dto.telefono();
        this.email= dto.email();
    }
}
