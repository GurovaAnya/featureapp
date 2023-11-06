package com.feature.services;

import com.feature.models.AccessEntity;
import com.feature.models.Feature;
import com.feature.models.User;
import com.feature.repositories.FeatureRepository;
import com.feature.repositories.UserRepository;
import com.feature.repositories.access.AccessEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AccessFeatureTest {

    @InjectMocks
    private AccessService accessService;

    @Mock
    private FeatureRepository featureRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AccessEntityRepository accessEntityRepository;

    private final User user = new User(1L,"testName");
    private final Feature feature= new Feature(2L, "featureName");

    @Test
    public void isAccessibleForUser_alreadyHas_access_true(){
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(featureRepository.findByName(feature.getName())).thenReturn(feature);
        when(accessEntityRepository.isAccessibleForUser(user.getId(), feature.getId())).thenReturn(true);

        var isAccessible = accessService.isAccessibleForUser(user.getEmail(), feature.getName());
        assert isAccessible;
    }

    @Test
    public void isAccessibleForUser_userDoesntExist_access_false(){
        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
        when(featureRepository.findByName(feature.getName())).thenReturn(feature);

        var isAccessible = accessService.isAccessibleForUser(user.getEmail(), feature.getName());
        assert !isAccessible;
    }

    @Test
    public void isAccessibleForUser_featureDoesntExist_access_false(){
        when(featureRepository.findByName(feature.getName())).thenReturn(null);

        var isAccessible = accessService.isAccessibleForUser(user.getEmail(), feature.getName());
        assert !isAccessible;
    }

    @Test
    public void isAccessibleForUser_featureAndUserExist_noAccess_false(){
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(featureRepository.findByName(feature.getName())).thenReturn(feature);
        when(accessEntityRepository.isAccessibleForUser(user.getId(), feature.getId())).thenReturn(false);

        var isAccessible = accessService.isAccessibleForUser(user.getEmail(), feature.getName());
        assert !isAccessible;
    }

    @Test
    public void grantAccess_noAccessBefore_granting_returnsTrue(){
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(featureRepository.findByName(feature.getName())).thenReturn(feature);
        when(accessEntityRepository.isAccessibleForUser(user.getId(), feature.getId())).thenReturn(false);
        AccessEntity accessEntity = new AccessEntity(user.getId(), feature.getId());
        when(accessEntityRepository.save(accessEntity)).thenReturn(accessEntity);

        var somethingChanged = accessService.grantAccess(user.getEmail(), feature.getName(), true);
        assert somethingChanged;
    }

    @Test
    public void grantAccess_accessAlreadyGranted_granting_returnsFalse(){
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(featureRepository.findByName(feature.getName())).thenReturn(feature);
        when(accessEntityRepository.isAccessibleForUser(user.getId(), feature.getId())).thenReturn(true);

        var somethingChanged = accessService.grantAccess(user.getEmail(), feature.getName(), true);
        assert !somethingChanged;
    }

    @Test
    public void grantAccess_noAccessBefore_revoking_returnsFalse(){
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(featureRepository.findByName(feature.getName())).thenReturn(feature);
        when(accessEntityRepository.isAccessibleForUser(user.getId(), feature.getId())).thenReturn(false);

        var somethingChanged = accessService.grantAccess(user.getEmail(), feature.getName(), false);
        assert !somethingChanged;
    }

    @Test
    public void grantAccess_accessAlreadyGranted_revoking_returnsTrue(){
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(featureRepository.findByName(feature.getName())).thenReturn(feature);
        when(accessEntityRepository.isAccessibleForUser(user.getId(), feature.getId())).thenReturn(true);

        var somethingChanged = accessService.grantAccess(user.getEmail(), feature.getName(), false);

        verify(accessEntityRepository, times(1)).deleteByUserIdAndFeatureId(user.getId(), feature.getId());
        assert somethingChanged;
    }

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
}
