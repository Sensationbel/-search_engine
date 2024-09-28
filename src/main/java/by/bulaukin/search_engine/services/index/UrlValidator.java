package by.bulaukin.search_engine.services.index;

import by.bulaukin.search_engine.model.entity.Site;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
@Slf4j
public class UrlValidator {

    private final Set<String> visitedPage = ConcurrentHashMap.newKeySet();

    @Value("${validator.check-regex}")
    private String checkRegex;

    public boolean isValidAndNotVisit(Site site, String currentPage) {
        String headPage = site.getUrl();
        return currentPage.startsWith(headPage)
                && !currentPage.equals(headPage + "/")
                && !isFile(currentPage)
                && !currentPage.contains("#")
                && !currentPage.contains("?")
                && !currentPage.endsWith("//")
                && isNotVisit(currentPage);
    }

    private boolean isNotVisit(String currentPage) {
       return visitedPage.add(currentPage);
    }

    private boolean isFile(String currentPage) {
        Pattern fileFilter =
                Pattern.compile(checkRegex);
        return fileFilter.matcher(currentPage).matches();
    }

}
