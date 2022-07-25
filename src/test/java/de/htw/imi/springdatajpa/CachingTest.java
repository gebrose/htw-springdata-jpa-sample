package de.htw.imi.springdatajpa;

import de.htw.imi.springdatajpa.entities.BueroRaum;
import de.htw.imi.springdatajpa.entities.Professor;
import de.htw.imi.springdatajpa.entities.Stockwerk;
import de.htw.imi.springdatajpa.repos.AbstractRepositoryTest;
import de.htw.imi.springdatajpa.repos.BueroRaumRepository;
import de.htw.imi.springdatajpa.repos.ProfessorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CachingTest extends AbstractRepositoryTest {


    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    BueroRaumRepository bueroRaumRepository;

    /*
      will trigger two DB calls until second-level caches is enabled
     */
    @Test
    void findTwiceById() {
        Optional<Professor> professorOptional = professorRepository.findById(125L);
        professorOptional = professorRepository.findById(125L);
    }

    /*
      will trigger only one call because result remains in first-level cache
     */
    @Transactional
    @Test
    void findTwiceByIdTransactional() {
        professorRepository.findById(126L);
        professorRepository.findById(126L);
    }

    /*
       will trigger *two* DB calls for the room entity.
       until second-level caches is enabled AND the cacheable annotation used on the Bueroraum class!
       NB: Its relationship cannot be lazyly fetched without a TX!
    */
    @Test
    void findByIdWithRelationships() {
        Optional<BueroRaum> bueroRaumOptional = bueroRaumRepository.findById(11L);
        bueroRaumOptional = bueroRaumRepository.findById(11L);
    }

    /*
       will trigger n+1 DB calls once because result remains in first-level cache
       and stockwerk is only fetched lazily
    */
    @Transactional
    @Test
    void findByIdWithRelationshipsTransactional() {
        Optional<BueroRaum> bueroRaumOptional = bueroRaumRepository.findById(11L);
        Stockwerk stockwerk = bueroRaumOptional.orElseThrow().getStockwerk();
        bueroRaumOptional = bueroRaumRepository.findById(11L);
        stockwerk = bueroRaumOptional.orElseThrow().getStockwerk();
    }
}
