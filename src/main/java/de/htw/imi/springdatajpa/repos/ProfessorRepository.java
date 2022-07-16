package de.htw.imi.springdatajpa.repos;

import de.htw.imi.springdatajpa.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
