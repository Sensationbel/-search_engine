package by.bulaukin.search_engine.services.index;

import by.bulaukin.search_engine.config.SiteData;
import by.bulaukin.search_engine.config.SitesDataList;
import by.bulaukin.search_engine.model.entity.Site;
import by.bulaukin.search_engine.model.mapper.SitesMapper;
import by.bulaukin.search_engine.model.services.SiteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

@Service
@RequiredArgsConstructor
@Slf4j
public class IndexationService {

    private final ForkJoinPool forkJoinPool;
    private final SitesDataList sitesList;
    private final WebParser webParser;
    private final SiteService siteService;
    private final SitesMapper mapper;

    public void startPars() {
        List<Site> sites = prepareSiteData(sitesList);
        
        sites.forEach(site -> {
            log.info("indexationService startParse started with url:" + site.getUrl());
            webParser.setSite(site);
            forkJoinPool.invoke(new RecursiveWebParser(webParser.parsPage(site.getUrl()), webParser));
            log.info("indexationService startParse finished with url:" + site.getUrl());
        });

    }

    private List<Site> prepareSiteData(SitesDataList sitesList) {
        List<String> siteName = sitesList.getSites().stream().map(SiteData::getName).toList();
        siteName.forEach(siteService::deleteByName);
        return siteService.saveAll(mapper.sitesDataListToSitesList(sitesList.getSites()));
    }

}
