package de.htw.imi.springdatajpa.repos;

import de.htw.imi.springdatajpa.entities.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Page<Professor> findByRang(Pageable pageable, String rang);
}
