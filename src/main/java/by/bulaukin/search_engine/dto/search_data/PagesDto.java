package by.bulaukin.search_engine.dto.search_data;

import by.bulaukin.search_engine.model.entity.Site;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PagesDto {
    private Site site;
    private String path;
    private Integer code;
    private String content;
}
