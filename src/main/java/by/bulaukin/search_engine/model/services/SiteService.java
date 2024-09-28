package by.bulaukin.search_engine.model.services;

import by.bulaukin.search_engine.model.entity.Site;

import java.util.List;

public interface SiteService {

    List<Site> saveAll(List<Site> siteList);

    Site findByUrlContainsHostName(String hostName);

    void deleteByName(String name);

}
