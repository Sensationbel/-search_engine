package by.bulaukin.search_engine.model.services;

import by.bulaukin.search_engine.config.SitesDataList;
import by.bulaukin.search_engine.model.entity.Site;

import java.util.List;
import java.util.Optional;

public interface SiteService {

    List<Site> saveAll(List<Site> siteList);

    Site save(Site site);

    Optional<Site> findById(Integer id);

    Site update(Site site);

    Site findByUrlContainsHostName(String hostName);

    void deleteByName(String name);

    List<Site> prepareSiteData(SitesDataList sitesList);
}
