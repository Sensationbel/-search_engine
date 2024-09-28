package by.bulaukin.search_engine.model.mapper;

import by.bulaukin.search_engine.dto.search_data.PagesDto;
import by.bulaukin.search_engine.dto.search_data.SitesDto;
import by.bulaukin.search_engine.model.entity.Page;
import by.bulaukin.search_engine.model.entity.Site;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {SitesMapper.class})
public interface PagesMapper {


    Page pageDtoToPage(PagesDto page);
}
