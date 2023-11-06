package com.feature.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "access")
@Table(name = "access", indexes = @Index(name = "IDX_feature_user", columnList = "feature_id, user_id")
)
public class AccessEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SQ_access_id"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "feature_id")
    private Long featureId;
    @Column(name = "user_id")
    private Long userId;

    public AccessEntity(Long userId, Long featureId) {
        this.featureId = featureId;
        this.userId = userId;
    }

    public AccessEntity() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccessEntity that = (AccessEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(featureId, that.featureId)) return false;
        return Objects.equals(userId, that.userId);
    }
}
