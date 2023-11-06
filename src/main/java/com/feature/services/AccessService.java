package com.feature.services;

import com.feature.models.AccessEntity;
import com.feature.models.Feature;
import com.feature.models.User;
import com.feature.repositories.FeatureRepository;
import com.feature.repositories.UserRepository;
import com.feature.repositories.access.AccessEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessService {

    private final UserRepository userRepository;
    private final FeatureRepository featureRepository;
    private final AccessEntityRepository accessEntityRepository;

    @Autowired
    public AccessService(UserRepository userRepository, FeatureRepository featureRepository, AccessEntityRepository accessEntityRepository) {
        this.userRepository = userRepository;
        this.featureRepository = featureRepository;
        this.accessEntityRepository = accessEntityRepository;
    }
    public boolean isAccessibleForUser(String email, String featureName) {

        var feature = featureRepository.findByName(featureName);
        if (feature == null)
            return false;
        var user = userRepository.findByEmail(email);
        if (user == null)
            return false;

        return accessEntityRepository.isAccessibleForUser(user.getId(), feature.getId());
    }

    public boolean grantAccess(String email, String featureName, boolean enabled) {
        var feature = featureRepository.findByName(featureName);
        if (feature == null && enabled)
            feature = featureRepository.save(new Feature(featureName));
        var user = userRepository.findByEmail(email);
        if (user == null && enabled)
            user = userRepository.save(new User(email));

        Long userId = user.getId();
        Long featureId = feature.getId();

        boolean accessIsAlreadyGranted = accessEntityRepository.isAccessibleForUser(userId, featureId);
        if (enabled && !accessIsAlreadyGranted) {
            accessEntityRepository.save(new AccessEntity(userId, featureId));
            return true;
        }
        else if (!enabled && accessIsAlreadyGranted) {
            accessEntityRepository.deleteByUserIdAndFeatureId(userId, featureId);
            return true;
        }

        return false;
    }
}
