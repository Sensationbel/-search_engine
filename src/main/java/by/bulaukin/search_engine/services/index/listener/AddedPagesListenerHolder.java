package by.bulaukin.search_engine.services.index.listener;

import by.bulaukin.search_engine.dto.search_data.PagesDto;
import by.bulaukin.search_engine.model.services.PageServices;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class AddedPagesListenerHolder {

    private final PageServices pageServices;

    @EventListener
    @SneakyThrows
    @Transactional(isolation = Isolation.READ_COMMITTED, noRollbackFor = DataIntegrityViolationException.class)
    public void addPageDTOIntoDb(CreatedPagesEventHolder eventHolder) {

        String path = eventHolder.getResponseData().getPath();

        if (pageServices.findByPath(path).isEmpty()) {
            pageServices.save(createNewPageDto(eventHolder.getResponseData()));
        }
    }

    private PagesDto createNewPageDto(JsoupResponseData response) {
        return PagesDto.builder()
                .code(response.getStatusCode())
                .site(response.getSite())
                .content(response.getPageContent())
                .path(response.getPath())
                .build();
    }

}
