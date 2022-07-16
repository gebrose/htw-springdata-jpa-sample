package de.htw.imi.springdatajpa.repos;

import de.htw.imi.springdatajpa.entities.BueroRaum;
import de.htw.imi.springdatajpa.entities.Professor;
import de.htw.imi.springdatajpa.services.ProfessorService;
import de.htw.imi.springdatajpa.web.ProfessorDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProfessorRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    ProfessorService professorService;

    @Test
    void findAll() {
        final List<Professor> all = professorRepository.findAll();
        assertThat(all).isNotEmpty();
        assertThat(all)
                .extracting(Professor::getId)
                .isNotEmpty();
    }

    @Test
    void findById() {
        final Optional<Professor> professorOptional = professorRepository.findById(125L);
        assertThat(professorOptional).isPresent();
        assertThat(professorOptional.get().getName()).isEqualTo("Sokrates");
    }

    @Test
    void findByIdWithFetchedRooms() {
        final Optional<Professor> professorOptional = professorRepository.findById(133L);
        assertThat(professorOptional).isPresent();
        final BueroRaum raeume = professorOptional.get().getRaum();
        assertThat(raeume.getName()).isEqualTo("Office#6");
    }

    @Test
    void cannotfindById() {
        final Optional<Professor> stockwerkOptional = professorRepository.findById(0L);
        assertThat(stockwerkOptional).isNotPresent();
    }

    @Test
    void createNew() {
        final Professor stockwerk = professorRepository.save(new Professor());
        assertThat(stockwerk).isNotNull();
        assertThat(stockwerk.getId()).isPositive();

        final Professor stockwerk1 = professorRepository.save(new Professor());
        assertThat(stockwerk.getId()).isLessThan(stockwerk1.getId());
    }

    @Test
    void save() {
        final Professor stockwerk = professorRepository.save(new Professor());
        stockwerk.setGehalt(27000);

        professorRepository.save(stockwerk);

        final Optional<Professor> stockwerkOptional = professorRepository.findById(stockwerk.getId());
        assertThat(stockwerkOptional).isPresent();
        assertThat(stockwerkOptional.get().getGehalt()).isEqualTo(27000);
    }

    @Test
    void delete() {
        final Professor professor = professorRepository.save(new Professor());

        professorRepository.delete(professor);

        final Optional<Professor> professorOptional = professorRepository.findById(professor.getId());
        assertThat(professorOptional).isNotPresent();
    }

    @Test
    void createFrom() {
        final ProfessorDto professorDto = new ProfessorDto();
        professorDto.setName("Captain Nemo");
        professorDto.setGehalt(27000);

        final Professor from = professorService.createFrom(professorDto);

        final Optional<Professor> stockwerkOptional = professorRepository.findById(from.getId());
        assertThat(stockwerkOptional).isPresent();
        assertThat(stockwerkOptional.get().getGehalt()).isEqualTo(27000);
    }

    @Test
    void update() {
        final Optional<Professor> stockwerkOptional = professorRepository.findById(127L);
        stockwerkOptional.ifPresent(b -> {
            b.setRang("C4");
            professorRepository.save(b);
        });
        final Optional<Professor> reloadedStockwerk = professorRepository.findById(127L);
        assertThat(reloadedStockwerk).isPresent();
        assertThat(reloadedStockwerk.get().getRang()).isEqualTo("C4");
    }
}