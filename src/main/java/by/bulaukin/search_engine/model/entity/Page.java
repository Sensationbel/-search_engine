package by.bulaukin.search_engine.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "site_id")
    private Site site;

    @Column(nullable = false)
    private String path;

    private Integer code;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

}
