package by.bulaukin.search_engine.model.services;

import by.bulaukin.search_engine.dto.search_data.PagesDto;
import by.bulaukin.search_engine.model.entity.Page;
import by.bulaukin.search_engine.model.mapper.PagesMapper;
import by.bulaukin.search_engine.model.repository.PagesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PageServicesImpl implements PageServices {

    private final PagesRepository repository;
    private final PagesMapper mapper;

    @Override
//    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Page save(PagesDto pagesDto) {
        var pageForSave = mapper.pageDtoToPage(pagesDto);
        log.info("Saving pagesDto %s, %s".formatted(pagesDto.getPath(), Thread.currentThread().getName()));
        try {
            return repository.save(pageForSave);
        } catch (DataIntegrityViolationException e) {
            if (e.getMostSpecificCause().getClass().getName().equals("org.postgresql.util.PSQLException") && ((SQLException) e.getMostSpecificCause()).getSQLState().equals("23505")) {
                log.info("saving pagesDto: {}", e.getMostSpecificCause().getMessage());
            }
            return new Page();
        }

    }


    @Override
//    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Optional<Page> findByPathIsContaining(String path) {
        var page = repository.findByPathIsContaining(path).orElse(null);
        if (page != null) {
            log.info("result founding path: %s, %s".formatted(page.getPath(), Thread.currentThread().getName()));
        }
        return Optional.ofNullable(page);
    }
}
