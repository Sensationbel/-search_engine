package by.bulaukin.search_engine.services.index.listener;

import by.bulaukin.search_engine.model.entity.Site;
import by.bulaukin.search_engine.model.services.SiteService;
import by.bulaukin.search_engine.services.index.JsoupResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class CreatedPagesAspect {

    private final ApplicationEventPublisher publisher;
    private final SiteService service;

    @SneakyThrows
    @Around("@annotation(Publisher)")
    public Object addPublish(ProceedingJoinPoint point) {
        JsoupResponse target = (JsoupResponse) point.getTarget();
        Object proceed = point.proceed();
        publisher.publishEvent(new CreatedPagesEventHolder(target,
                getDataFromJsoupResponse((Connection.Response) proceed)));
        return proceed;
    }

    private JsoupResponseData getDataFromJsoupResponse(Connection.Response response) throws IOException {
        JsoupResponseData data = new JsoupResponseData();
        data.setSite(getUrlContainsHostName(response));
        data.setPath(response.url().getPath());
        data.setStatusCode(response.statusCode());
        Document document = response.parse();

        if (data.getStatusCode() == 200) {
            data.setPageContent(document.html());
        } else {
            data.setPageContent(document.connection().response().statusMessage());
            log.debug("status code: {}", data.getStatusCode());
        }

        return data;
    }

    private Site getUrlContainsHostName(Connection.Response response) {
        return service.findByUrlContainsHostName(response.url().getHost());
    }

}
