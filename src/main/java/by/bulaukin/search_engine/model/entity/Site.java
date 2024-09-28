package by.bulaukin.search_engine.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "site", schema = "search_engine_schema")
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private Status status;

    @Column(name = "status_time")
    @UpdateTimestamp
    private Timestamp statusTime;

    @Column(name = "last_error")
    private String lastError;

    private String url;

    private String name;

    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL)
    private List<Page> pages;



}
