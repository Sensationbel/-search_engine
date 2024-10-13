package by.bulaukin.search_engine.services.index;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebParserImpl implements WebParser {

    private final JsoupResponse jsoupResponse;
    private final UrlValidator urlValidator;

    @Override
    @SneakyThrows
    public Set<String> parsPage(String pageUrl) {
        log.debug("Parsing page: {}", pageUrl);
        Set<String> urls = new HashSet<>();

        Connection.Response response = jsoupResponse.getResponse(pageUrl);
        String hostName = response.url().getHost();
        Document doc = response.parse();

        for (Element element : doc.select("a")) {
            String currentUrl = element.attr("abs:href");

            if (urlValidator.isValidAndNotVisit(hostName, currentUrl)) {
                urls.add(currentUrl);
            }
        }

        return urls;
    }
}
