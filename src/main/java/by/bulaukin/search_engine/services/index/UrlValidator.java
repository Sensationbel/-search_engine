package by.bulaukin.search_engine.services.index;

import by.bulaukin.search_engine.model.entity.Site;
import by.bulaukin.search_engine.model.services.PageServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
@Slf4j
public class UrlValidator {

    private final PageServices services;

    @Value("${validator.check-regex}")
    private String checkRegex;

    public boolean isValidToVisit(Site site, String currentPage) {
        String headPage = site.getUrl();
        return currentPage.startsWith(headPage)
                && !currentPage.equals(headPage + "/")
                && !isFile(currentPage)
                && !currentPage.contains("#")
                && !currentPage.contains("?")
                && !currentPage.endsWith("//");
//                && isVisit(headPage, currentPage);
    }

    private boolean isVisit(String headPage, String currentPage) {
        var url = currentPage.replaceAll(headPage, "");
        boolean empty = services.findByPathIsContaining(url).isEmpty();
        log.debug("Check currentUrl:  %s, result : %s current Thread :%s".formatted(url,
                empty,
                Thread.currentThread().getName()));
        return empty;
    }

    private boolean isFile(String currentPage) {
        Pattern fileFilter =
                Pattern.compile(checkRegex);
        return fileFilter.matcher(currentPage).matches();
    }

}
