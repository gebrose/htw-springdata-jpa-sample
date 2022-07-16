package de.htw.imi.springdatajpa.repos;

import de.htw.imi.springdatajpa.entities.BueroRaum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BueroRaumRepository extends JpaRepository<BueroRaum, Long> {
}
