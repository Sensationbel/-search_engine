package by.bulaukin.search_engine.services.index.listener;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CreatedPagesEventHolder extends ApplicationEvent {

    private final JsoupResponseData responseData;

    public CreatedPagesEventHolder(Object source, JsoupResponseData response) {
        super(source);
        this.responseData = response;
    }
}
