package com.agn.login.service;

import com.agn.login.controller.dto.MateriaDto;
import com.agn.login.entity.Materia;
import com.agn.login.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {
    private final MateriaRepository materiaRepository;

    @Autowired
    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;

    }

    public ResponseEntity<?> getMaterias() {
        List<MateriaDto> materiaDtoList = materiaRepository.findAll()
                .stream()
                .map(materia -> MateriaDto.builder()
                        .id(materia.getId())
                        .name(materia.getName())
                        .user(materia.getUser())
                        .build())
                .toList();
        return ResponseEntity.ok(materiaDtoList);
    }

    public ResponseEntity<?> getMateria(Long id) {
        Optional<Materia> res = materiaRepository.findMateriaById(id);
        if (res.isPresent()) {
            Materia materia = res.get();
            MateriaDto materiaDto = MateriaDto.builder()
                    .id(materia.getId())
                    .name(materia.getName())
                    .user(materia.getUser())
                    .cantAlum(materia.getCantAlum())
                    .build();
            return ResponseEntity.ok(materiaDto);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> createMateria(MateriaDto materiaDto) throws URISyntaxException {
        if (materiaDto.getName().isBlank() || materiaDto.getCantAlum().isBlank() || materiaDto.getUser().getMaterias().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        materiaRepository.save(Materia.builder()
                .name(materiaDto.getName())
                .cantAlum(materiaDto.getCantAlum())
                .user(materiaDto.getUser())
                .build()

        );
        return ResponseEntity.created(new URI("/materia")).build();
    }

    public ResponseEntity<?> updateMateria(MateriaDto materiaDto, Long id) {
        Optional<Materia> res = materiaRepository.findMateriaById(id);
        if (res.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Materia materia = res.get();
        materiaRepository.save(Materia.builder()
                .id(materia.getId())
                .name(materia.getName())
                .cantAlum(materia.getCantAlum())
                .user(materia.getUser())
                .build());
        return ResponseEntity.ok(materia);
    }
}
