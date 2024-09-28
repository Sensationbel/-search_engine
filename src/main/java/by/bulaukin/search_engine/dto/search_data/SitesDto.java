package by.bulaukin.search_engine.dto.search_data;

import by.bulaukin.search_engine.model.entity.Status;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;


@Data
public class SitesDto implements Serializable {

    private Status status;
    private Timestamp statusTime;
    private String lastError;
    private String url;
    private String name;
}