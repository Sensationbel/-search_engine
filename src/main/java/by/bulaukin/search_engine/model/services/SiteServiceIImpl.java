package by.bulaukin.search_engine.model.services;

import by.bulaukin.search_engine.config.SiteData;
import by.bulaukin.search_engine.config.SitesDataList;
import by.bulaukin.search_engine.model.entity.Site;
import by.bulaukin.search_engine.model.exception.EntityNotFoundException;
import by.bulaukin.search_engine.model.mapper.SitesMapper;
import by.bulaukin.search_engine.model.repository.SitesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SiteServiceIImpl implements SiteService {

    private final SitesRepository repository;
    private final SitesMapper mapper;

    @Override
    public List<Site> saveAll(List<Site> siteList) {
        return repository.saveAll(siteList);
    }

    @Override
    public Site save(Site site) {
        return repository.save(site);
    }

    @Override
    public Optional<Site> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Site update(Site site) {

        return null;
    }

    @Override
    public Site findByUrlContainsHostName(String hostName) {
        return repository.findByUrlContainsIgnoreCase(hostName).orElseThrow(
                () -> new EntityNotFoundException("Site with hostName %s, not found".formatted(hostName)));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW)
    public void deleteByName(String name) {
        log.info("SiteServiceIImpl.deleteByName: " + name);
        repository.deleteByName(name);
    }

    @Override
    @Transactional
    public List<Site> prepareSiteData(SitesDataList sitesList) {
        List<String> sitesName = sitesList.getSites().stream().map(SiteData::getName).toList();
        sitesName.forEach(this::deleteByName);
        return saveAll(mapper.sitesDataListToSitesList(sitesList.getSites()));
    }
}
