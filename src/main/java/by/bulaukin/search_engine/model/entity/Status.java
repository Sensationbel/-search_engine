package by.bulaukin.search_engine.model.entity;

import lombok.Getter;

@Getter
public enum Status {

    INDEXING("INDEXING"),
    INDEXED("INDEXED"),
    FAILED("FAILED");

    private final String status;

    Status(String status) {
        this.status = status;
    }
}
