package by.bulaukin.search_engine.services.index.listener;

import lombok.Getter;
import org.jsoup.Connection;
import org.springframework.context.ApplicationEvent;

@Getter
public class CreatedPagesEventHolder extends ApplicationEvent {

    private final Connection.Response response;

    public CreatedPagesEventHolder(Object source, Connection.Response response) {
        super(source);
        this.response = response;
    }
}
