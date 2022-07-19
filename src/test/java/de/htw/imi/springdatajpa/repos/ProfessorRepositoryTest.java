package de.htw.imi.springdatajpa.repos;

import de.htw.imi.springdatajpa.entities.BueroRaum;
import de.htw.imi.springdatajpa.entities.Professor;
import de.htw.imi.springdatajpa.services.ProfessorService;
import de.htw.imi.springdatajpa.web.ProfessorDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

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
    void findByRang() {
        Pageable pageable = PageRequest.of(1,2, Sort.by(Sort.Order.desc("name")));
        Page<Professor> professorPage = professorRepository.findByRang(pageable, "C4");
        assertThat(professorPage.getTotalElements()).isEqualTo(5);
        assertThat(professorPage.getTotalPages()).isEqualTo(3);
        Professor firstProfessorBySort = professorPage.get().findFirst().orElseThrow();
        assertThat(firstProfessorBySort.getName()).isEqualTo("Meitner");

        pageable = PageRequest.of(0,10, Sort.by(Sort.Order.asc("name")));
        professorPage = professorRepository.findByRang(pageable, "C4");
        assertThat(professorPage.getTotalElements()).isEqualTo(5);
        assertThat(professorPage.getTotalPages()).isEqualTo(1);
        firstProfessorBySort = professorPage.get().findFirst().orElseThrow();
        assertThat(firstProfessorBySort.getName()).isEqualTo("Curie");
     }

    @Test
    @Transactional
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