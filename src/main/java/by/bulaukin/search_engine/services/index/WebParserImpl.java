package by.bulaukin.search_engine.services.index;

import by.bulaukin.search_engine.model.entity.Site;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Scope("prototype")
@Slf4j
@RequiredArgsConstructor
public class WebParserImpl implements WebParser {

    private final JsoupResponse jsoupResponse;
    private final UrlValidator urlValidator;
    private Site site;

    @Override
    @SneakyThrows
    public Set<String> parsPage(String pageUrl) {

        Set<String> urls = new HashSet<>();

        Connection.Response response = jsoupResponse.getResponse(pageUrl);
        Document doc = response.parse();

        for (Element element : doc.select("a")) {
            String currentUrl = element.attr("abs:href");

            if (urlValidator.isValidAndNotVisit(site, currentUrl)) {
                    urls.add(currentUrl);
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
