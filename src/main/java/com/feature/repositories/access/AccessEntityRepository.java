package com.feature.repositories.access;

import com.feature.models.AccessEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccessEntityRepository extends JpaRepository<AccessEntity, Long>, AccessEntityCustomRepository {
    @Modifying
    @Transactional
    @Query("DELETE FROM access AS a WHERE a.featureId = :featureId AND a.userId = :userId")
    void deleteByUserIdAndFeatureId(@Param("userId") Long userId, @Param("featureId") Long featureId);
}
