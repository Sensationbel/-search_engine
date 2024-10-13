package by.bulaukin.search_engine.services.index;

import java.util.Set;

public interface WebParser {

    Set<String> parsPage(String pageUrl);

}
