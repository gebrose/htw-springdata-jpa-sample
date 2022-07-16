package de.htw.imi.springdatajpa.services;

import de.htw.imi.springdatajpa.entities.Professor;
import de.htw.imi.springdatajpa.repos.ProfessorRepository;
import de.htw.imi.springdatajpa.web.ProfessorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Professor createFrom(final ProfessorDto professorDto) {
        final Professor entity = new Professor();
        entity.setName(professorDto.getName());
        entity.setRang(professorDto.getRang());
        entity.setGehalt(professorDto.getGehalt());
        return professorRepository.save(entity);
    }
}
