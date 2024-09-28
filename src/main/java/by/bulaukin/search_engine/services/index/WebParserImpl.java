package by.bulaukin.search_engine.services.index;

import by.bulaukin.search_engine.model.entity.Site;
import by.bulaukin.search_engine.model.services.PageServices;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@Scope("prototype")
@Slf4j
@RequiredArgsConstructor
public class WebParserImpl implements WebParser {

    private final JsoupResponse jsoupResponse;
    private final UrlValidator urlValidator;
    private final PageServices pageServices;
    private Site site;

    @Override
    @SneakyThrows
    @Transactional(isolation = Isolation.REPEATABLE_READ, noRollbackFor = RuntimeException.class)
    public Set<String> parsPage(String pageUrl) {
//        log.info("{} before is present", pageUrl);
//        String url = pageUrl.equals(site.getUrl()) ? pageUrl : pageUrl.replaceAll(site.getUrl(), "");
//        if(pageServices.findByPathIsContaining(url).isPresent() ){
//            log.info("{} is present", pageUrl);
//                    }
        Set<String> urls = new HashSet<>();

        Connection.Response response = jsoupResponse.getResponse(pageUrl);
        Document doc = response.parse();

        for (Element element : doc.select("a")) {
            String currentUrl = element.attr("abs:href");
            if (urlValidator.isValidToVisit(site, currentUrl)) {
                String url = currentUrl.replaceAll(site.getUrl(), "");
                if(!pageServices.findByPathIsContaining(url).isPresent() ){
                    urls.add(currentUrl);
                }

            }
        }
        return urls;
    }

    @Override
    public void setSite(Site site) {
        this.site = site;
    }

    @Override
    public Site getSite() {
        return site;
    }
}
