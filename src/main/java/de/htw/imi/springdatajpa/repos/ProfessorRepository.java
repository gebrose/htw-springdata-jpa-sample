package de.htw.imi.springdatajpa.entities.repos;

import de.htw.imi.springdatajpa.entities.entities.Professor;
import org.springframework.data.repository.CrudRepository;

public interface ProfessorRepository extends CrudRepository<Professor, Long> {
}
