package by.bulaukin.search_engine.model.services;

import by.bulaukin.search_engine.dto.search_data.PagesDto;
import by.bulaukin.search_engine.model.entity.Page;

import java.sql.SQLException;
import java.util.Optional;

public interface PageServices {

    Page save(PagesDto page) throws SQLException;
    Optional<Page> findByPathIsContaining(String path);

}
