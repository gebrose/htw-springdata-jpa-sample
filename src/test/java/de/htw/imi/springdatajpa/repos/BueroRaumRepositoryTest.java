package de.htw.imi.springdatajpa.repos;

import de.htw.imi.springdatajpa.entities.BueroRaum;
import de.htw.imi.springdatajpa.services.BueroRaumService;
import de.htw.imi.springdatajpa.web.BueroDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BueroRaumRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    BueroRaumRepository bueroRaumRepository;

    @Autowired
    private BueroRaumService bueroRaumService;

    @Test
    void findAll() {
        final List<BueroRaum> all = bueroRaumRepository.findAll();
        assertThat(all).isNotEmpty();
        assertThat(all)
                .extracting(BueroRaum::getId)
                .isNotEmpty();
    }

    @Test
    void findById() {
        final Optional<BueroRaum> bueroRaumOptional = bueroRaumRepository.findById(10L);
        assertThat(bueroRaumOptional).isPresent();
        assertThat(bueroRaumOptional.get().getName()).isEqualTo("Office#5");
    }

    @Test
    void cannotfindById() {
        final Optional<BueroRaum> bueroRaumOptional = bueroRaumRepository.findById(0L);
        assertThat(bueroRaumOptional).isNotPresent();
    }

    @Test
    void createNew() {
        final BueroRaum bueroRaum = bueroRaumRepository.save(new BueroRaum());
        assertThat(bueroRaum).isNotNull();
        assertThat(bueroRaum.getId()).isPositive();

        final BueroRaum bueroRaum2 = bueroRaumRepository.save(new BueroRaum());
        assertThat(bueroRaum.getId()).isLessThan(bueroRaum2.getId());
    }

    @Test
    void save() {
        final BueroRaum bueroRaum = bueroRaumRepository.save(new BueroRaum());
        final String roomNumber = "#" + bueroRaum.getId();
        final String name = "Test Room " + roomNumber;
        bueroRaum.setRaumnummer(roomNumber);
        bueroRaum.setName(name);
        bueroRaum.setHoehe(2.8);
        bueroRaum.setFlaeche(25.4);
        bueroRaum.setKapazitaet(2);

        bueroRaumRepository.save(bueroRaum);

        final Optional<BueroRaum> bueroRaumOptional = bueroRaumRepository.findById(bueroRaum.getId());
        assertThat(bueroRaumOptional).isPresent();
        assertThat(bueroRaumOptional.get().getName()).isEqualTo(name);
        assertThat(bueroRaumOptional.get().getRaumnummer()).isEqualTo(roomNumber);
        assertThat(bueroRaumOptional.get().getHoehe()).isEqualTo(2.8);
        assertThat(bueroRaumOptional.get().getFlaeche()).isEqualTo(25.4);
        assertThat(bueroRaumOptional.get().getKapazitaet()).isEqualTo(2);
    }

    @Test
    void delete() {
        final BueroRaum bueroRaum = bueroRaumRepository.save(new BueroRaum());
        final String roomNumber = "#" + bueroRaum.getId();
        final String name = "Test Room " + roomNumber;
        bueroRaum.setRaumnummer(roomNumber);
        bueroRaum.setName(name);
        bueroRaum.setHoehe(2.8);
        bueroRaum.setFlaeche(25.4);
        bueroRaum.setKapazitaet(2);

        bueroRaumRepository.save(bueroRaum);
        bueroRaumRepository.delete(bueroRaum);

        final Optional<BueroRaum> bueroRaumOptional = bueroRaumRepository.findById(bueroRaum.getId());
        assertThat(bueroRaumOptional).isNotPresent();
    }

    @Test
    void createFrom() {
        final BueroDto bueroRaum = new BueroDto();
        final String roomNumber = "#17";
        final String name = "Test Room " + roomNumber;
        bueroRaum.setRaumnummer(roomNumber);
        bueroRaum.setName(name);
        bueroRaum.setKapazitaet(2);

        final BueroRaum from = bueroRaumService.createFrom(bueroRaum);

        final Optional<BueroRaum> bueroRaumOptional = bueroRaumRepository.findById(from.getId());
        assertThat(bueroRaumOptional).isPresent();
        assertThat(bueroRaumOptional.get().getName()).isEqualTo(name);
        assertThat(bueroRaumOptional.get().getRaumnummer()).isEqualTo(roomNumber);
        assertThat(bueroRaumOptional.get().getKapazitaet()).isEqualTo(2);
        // TODO alle Attribute prüfen
    }

    @Test
    void update() {
        final Optional<BueroRaum> bueroRaumOptional = bueroRaumRepository.findById(11L);
        final String name = "" + System.currentTimeMillis();
        bueroRaumOptional.ifPresent(b -> {
            b.setName(name);
            bueroRaumRepository.save(b);
        });
        final Optional<BueroRaum> reloadedOffice = bueroRaumRepository.findById(11L);
        assertThat(reloadedOffice).isPresent();
        assertThat(reloadedOffice.get().getName()).isEqualTo(name);
    }
}