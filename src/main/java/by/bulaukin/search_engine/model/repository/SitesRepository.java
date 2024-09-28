package by.bulaukin.search_engine.model.repository;

import by.bulaukin.search_engine.model.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SitesRepository extends JpaRepository<Site, Integer> {

   void deleteByName(String name);
    Optional<Site> findByUrlContainsIgnoreCase(String hostName);
}
