package com.agn.login.repository;

import com.agn.login.entity.Materia;
import com.agn.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {

    Optional<Materia> findMateriaById(Long id);
    Optional<Materia> findMateriaByName(String username);
}