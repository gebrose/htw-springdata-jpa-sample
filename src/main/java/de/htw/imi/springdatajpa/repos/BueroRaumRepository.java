package de.htw.imi.springdatajpa.entities.repos;

import de.htw.imi.springdatajpa.entities.entities.BueroRaum;
import de.htw.imi.springdatajpa.entities.entities.Stockwerk;
import org.springframework.data.repository.CrudRepository;

public interface BueroRaumRepository extends CrudRepository<BueroRaum, Long> {
}
