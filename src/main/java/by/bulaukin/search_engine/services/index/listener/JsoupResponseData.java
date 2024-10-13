package by.bulaukin.search_engine.services.index.listener;

import by.bulaukin.search_engine.model.entity.Site;
import lombok.Data;

@Data
public class JsoupResponseData {

    private String path;
    private Site site;
    private int statusCode;
    private String pageContent;

}
