package com.agn.login.controller;

import com.agn.login.controller.dto.MateriaDto;
import com.agn.login.entity.Materia;
import com.agn.login.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping(path = "/materia")
public class MateriaController {
    private final MateriaService materiaService;

    @Autowired
    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping
    public ResponseEntity<?> getMaterias() {
        return materiaService.getMaterias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMateria(@PathVariable("id") Long id) {
        return materiaService.getMateria(id);
    }

    @PostMapping
    public ResponseEntity<?> createMateria(@RequestBody MateriaDto materiaDto) throws URISyntaxException {
        return materiaService.createMateria(materiaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMateria(@RequestBody MateriaDto materiaDto, @PathVariable Long id) {
        return materiaService.updateMateria(materiaDto, id);
    }
}
