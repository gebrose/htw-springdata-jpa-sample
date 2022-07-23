package de.htw.imi.springdatajpa.repos;

import de.htw.imi.springdatajpa.entities.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    List<Professor> findByRang(String rang);

    Page<Professor> findByRang(Pageable pageable, String rang);
}
