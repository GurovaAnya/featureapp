package com.feature.repositories.access;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class AccessEntityRepositoryImpl implements AccessEntityCustomRepository {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public boolean isAccessibleForUser(Long userId, Long featureId) {
        Query query = entityManager.createNativeQuery("SELECT count(*) FROM access as a " +
                "WHERE a.feature_id = :featureId AND a.user_id = :userId");
        query.setParameter("featureId", featureId);
        query.setParameter("userId", userId);

        var count = (Long) query.getSingleResult();
        return count > 0;
    }
}
