package de.htw.imi.springdatajpa.repos;

import de.htw.imi.springdatajpa.entities.Raum;
import de.htw.imi.springdatajpa.entities.Stockwerk;
import de.htw.imi.springdatajpa.services.StockwerkService;
import de.htw.imi.springdatajpa.web.StockwerkDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StockwerkRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    StockwerkRepository stockwerkRepository;

    @Autowired
    StockwerkService stockwerkService;

    @Test
    void findAll() {
        final List<Stockwerk> all = stockwerkRepository.findAll();
        assertThat(all).isNotEmpty();
        assertThat(all)
                .extracting(Stockwerk::getId)
                .isNotEmpty();
    }

    @Test
    void findById() {
        final Optional<Stockwerk> stockwerkOptional = stockwerkRepository.findById(1L);
        assertThat(stockwerkOptional).isPresent();
        assertThat(stockwerkOptional.get().getGeschossnummer()).isEqualTo(1);
    }

    @Test
    void findByIdWithFetchedRooms() {
        final Optional<Stockwerk> stockwerkOptional = stockwerkRepository.findById(6L);
        assertThat(stockwerkOptional).isPresent();
        final List<Raum> raeume = stockwerkOptional.get().getRaeume();
        assertThat(raeume)
                .hasSize(7)
                .extracting(Raum::getName)
                .contains("Office#2", "Office#3", "Office#4", "Office#5", "Seminar Room");
    }

    @Test
    void cannotfindById() {
        final Optional<Stockwerk> stockwerkOptional = stockwerkRepository.findById(0L);
        assertThat(stockwerkOptional).isNotPresent();
    }

    @Test
    void createNew() {
        final Stockwerk stockwerk = stockwerkRepository.save(new Stockwerk());
        assertThat(stockwerk).isNotNull();
        assertThat(stockwerk.getId()).isPositive();

        final Stockwerk stockwerk1 = stockwerkRepository.save(new Stockwerk());
        assertThat(stockwerk.getId()).isLessThan(stockwerk1.getId());
    }

    @Test
    void save() {
        final Stockwerk stockwerk = stockwerkRepository.save(new Stockwerk());
        stockwerk.setGeschossnummer(27);

        stockwerkRepository.save(stockwerk);

        final Optional<Stockwerk> stockwerkOptional = stockwerkRepository.findById(stockwerk.getId());
        assertThat(stockwerkOptional).isPresent();
        assertThat(stockwerkOptional.get().getGeschossnummer()).isEqualTo(27);
    }

    @Test
    void delete() {
        final Stockwerk stockwerk = stockwerkRepository.save(new Stockwerk());
        stockwerk.setGeschossnummer(22222);

        stockwerkRepository.save(stockwerk);
        stockwerkRepository.delete(stockwerk);

        final Optional<Stockwerk> stockwerkOptional = stockwerkRepository.findById(stockwerk.getId());
        assertThat(stockwerkOptional).isNotPresent();
    }

    @Test
    void createFrom() {
        final StockwerkDto stockwerkDto = new StockwerkDto();
        stockwerkDto.setGeschossnummer(47);

        final Stockwerk from = stockwerkService.createFrom(stockwerkDto);

        final Optional<Stockwerk> stockwerkOptional = stockwerkRepository.findById(from.getId());
        assertThat(stockwerkOptional).isPresent();
        assertThat(stockwerkOptional.get().getGeschossnummer()).isEqualTo(47);
    }

    @Test
    void update() {
        final Optional<Stockwerk> stockwerkOptional = stockwerkRepository.findById(6l);
        stockwerkOptional.ifPresent(b -> {
            b.setGeschossnummer(66);
            stockwerkRepository.save(b);
        });
        final Optional<Stockwerk> reloadedStockwerk = stockwerkRepository.findById(6l);
        assertThat(reloadedStockwerk).isPresent();
        assertThat(reloadedStockwerk.get().getGeschossnummer()).isEqualTo(66);
    }
}