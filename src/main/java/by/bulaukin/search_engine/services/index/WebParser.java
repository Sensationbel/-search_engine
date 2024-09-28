package by.bulaukin.search_engine.services.index;

import by.bulaukin.search_engine.model.entity.Site;
import org.jsoup.Connection;

import java.io.IOException;
import java.util.Set;

public interface WebParser {

    void setSite(Site site);
    Site getSite();
    Set<String> parsPage(String pageUrl);

}
