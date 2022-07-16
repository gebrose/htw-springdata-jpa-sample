package de.htw.imi.springdatajpa.services;

import de.htw.imi.springdatajpa.entities.Stockwerk;
import de.htw.imi.springdatajpa.repos.StockwerkRepository;
import de.htw.imi.springdatajpa.web.StockwerkDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implements the DAO (data access object) pattern for BueroRaum.
 * <p>
 * Classes annotated with @Service can be injected using @Autowired
 * in other Spring components.
 * <p>
 * Classes annotated with @Slf4j have access to loggers.
 */
@Service
@Slf4j
public class StockwerkService  {

    @Autowired
    StockwerkRepository stockwerkRepository;

    public Stockwerk createFrom(final StockwerkDto template) {
        final Stockwerk stockwerk = new Stockwerk();
        stockwerk.setGeschossnummer(template.getGeschossnummer());
        return stockwerkRepository.save(stockwerk);
    }

}
