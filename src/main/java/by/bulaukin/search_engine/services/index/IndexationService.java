package by.bulaukin.search_engine.services.index;

import by.bulaukin.search_engine.config.SitesDataList;
import by.bulaukin.search_engine.dto.statistics.ResultParsResponse;
import by.bulaukin.search_engine.model.entity.Site;
import by.bulaukin.search_engine.model.entity.Status;
import by.bulaukin.search_engine.model.services.SiteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

@Service
@RequiredArgsConstructor
@Slf4j
public class IndexationService {

    private final ForkJoinPool forkJoinPool;
    private final SitesDataList sitesList;
    private final WebParser webParser;
    private final SiteService siteService;

    public ResultParsResponse startPars() {
        List<Site> sites = siteService.prepareSiteData(sitesList);
        ResultParsResponse response = new ResultParsResponse();
        response.setResult(true);
        sites.forEach(this::createFuture);
        return response;
    }

    private CompletableFuture<Void> createFuture(Site site) {
        return CompletableFuture.runAsync(() -> forkJoinPool.
                                invoke(new RecursiveWebParser(webParser.parsPage(site.getUrl()), webParser)),
                        forkJoinPool).
                thenRun(() -> {
                    site.setStatus(Status.INDEXED);
                    siteService.update(site);
                    siteService.save(site);
                    log.info("Site {} saved successfully with status: INDEXED", site.getName());
                });
    }


}
