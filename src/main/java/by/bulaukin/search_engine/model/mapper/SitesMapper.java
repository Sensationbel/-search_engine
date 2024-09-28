package by.bulaukin.search_engine.model.mapper;

import by.bulaukin.search_engine.config.SiteData;
import by.bulaukin.search_engine.model.entity.Site;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(SitesMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SitesMapper {

    Site siteDataToSite(SiteData siteData);

    List<Site> sitesDataListToSitesList(List<SiteData> sites);
}
