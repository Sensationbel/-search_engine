package by.bulaukin.search_engine.services.index.listener;

import by.bulaukin.search_engine.dto.search_data.PagesDto;
import by.bulaukin.search_engine.model.entity.Site;
import by.bulaukin.search_engine.model.services.PageServicesImpl;
import by.bulaukin.search_engine.model.services.SiteService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class AddedPagesListenerHolder {

    private final PageServicesImpl pageServices;
    private final SiteService service;

    @EventListener
    @SneakyThrows
    public void addPageDTOIntoDb(CreatedPagesEventHolder eventHolder) {
        pageServices.save(createNewPageDto(eventHolder));
    }

    private PagesDto createNewPageDto(CreatedPagesEventHolder eventHolder) throws IOException {
        PagesDto pagesDto = new PagesDto();
        Connection.Response response = eventHolder.getResponse();
        String hostName = response.url().getHost();
        Site site = service.findByUrlContainsHostName(hostName);
        String path = response.url().toString();
        if(!path.equals(site.getUrl())){
            path = path.replaceAll(site.getUrl(), "");
        }
        int statusCode = response.statusCode();
        Document doc = response.parse();
        String pageContent = "";

        if(statusCode ==  200) pageContent = doc.html();
        else log.debug("status code: {}", statusCode);

        pagesDto.setSite(site);
        pagesDto.setPath(path);
        pagesDto.setCode(statusCode);
        pagesDto.setContent(pageContent);

        return pagesDto;
    }

}
