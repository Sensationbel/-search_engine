package by.bulaukin.search_engine.dto.search_data;

import by.bulaukin.search_engine.model.entity.Site;
import lombok.Data;

@Data
public class PagesDto {
    private Site site;
    private String path;
    private Integer code;
    private String content;
}
