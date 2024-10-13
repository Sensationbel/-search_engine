package by.bulaukin.search_engine.model.repository;

import by.bulaukin.search_engine.model.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PagesRepository extends JpaRepository<Page, Integer> {

    Optional<Page> findByPath(String path);
}
