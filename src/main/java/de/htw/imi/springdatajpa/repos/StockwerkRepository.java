package de.htw.imi.springdatajpa.repos;

import de.htw.imi.springdatajpa.entities.Stockwerk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StockwerkRepository extends JpaRepository<Stockwerk, Long> {
}
