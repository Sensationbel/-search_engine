package by.bulaukin.search_engine.services.index;

import by.bulaukin.search_engine.config.ConnectionData;
import by.bulaukin.search_engine.services.index.listener.Publisher;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class JsoupResponse {

    private final ConnectionData connectionData;

    @SneakyThrows
    @Publisher
    public Connection.Response getResponse(String pageUrl) {
        return Jsoup.connect(pageUrl)
                .userAgent(connectionData.getUserAgent())
//                .timeout(5000)
                .referrer(connectionData.getReferrer())
                .ignoreHttpErrors(true)
                .execute().bufferUp();
    }
}
