package by.bulaukin.search_engine.model.mapper;

import by.bulaukin.search_engine.config.SiteData;
import by.bulaukin.search_engine.model.entity.Site;
import by.bulaukin.search_engine.model.entity.Status;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class SitesMapperDelegate implements SitesMapper{

    @Override
    public Site siteDataToSite(SiteData siteData) {
        if (siteData == null) {
            return null;
        } else {
            Site site = new Site();
            site.setName(siteData.getName());
            site.setUrl(siteData.getUrl());
            site.setStatus(Status.INDEXING);
            site.setStatusTime(new Timestamp(System.currentTimeMillis()));
            return site;
        }
    }

    @Override
    public List<Site> sitesDataListToSitesList(List<SiteData> sites) {
        if (sites == null) {
            return null;
        } else {
            List<Site> list = new ArrayList(sites.size());
            Iterator var3 = sites.iterator();

            while(var3.hasNext()) {
                SiteData siteData = (SiteData)var3.next();
                list.add(this.siteDataToSite(siteData));
            }

            return list;
        }
    }
}
