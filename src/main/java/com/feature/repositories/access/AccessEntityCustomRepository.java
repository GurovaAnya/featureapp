package com.feature.repositories.access;

public interface AccessEntityCustomRepository {
    boolean isAccessibleForUser(Long userId, Long featureId);
}
