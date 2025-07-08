package org.hibernate.entity;

import jakarta.persistence.*;

@Entity
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Embedded
    private TestEmbeddable embeddable;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public TestEmbeddable getEmbeddable() {
        return embeddable;
    }

    public void setEmbeddable(final TestEmbeddable embeddable) {
        this.embeddable = embeddable;
    }
}
