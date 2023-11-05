package com.feature.repositories;

import com.feature.models.Feature;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature, Long> {
    @Nullable
    Feature findByName(String name);
}
