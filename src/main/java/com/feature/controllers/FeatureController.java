package com.feature.controllers;

import com.feature.dto.AccessRequestDto;
import com.feature.dto.AccessResponseDto;
import com.feature.services.AccessService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class FeatureController {
    private final AccessService accessService;

    @Autowired
    public FeatureController(AccessService accessService){
        this.accessService = accessService;
    }

    @GetMapping("/feature")
    public @ResponseBody AccessResponseDto getForUser(@RequestParam String email, @RequestParam String featureName){
        boolean isAccessGranted = accessService.isAccessibleForUser(email, featureName);
        return new AccessResponseDto(isAccessGranted);
    }

    @PostMapping("/feature")
    public @ResponseBody void setForUser(@RequestBody AccessRequestDto accessRequest, HttpServletResponse response){
        var accessChanged = accessService.grantAccess(accessRequest.getEmail(), accessRequest.getFeatureName(), accessRequest.getEnabled());
        if (!accessChanged)
            response.setStatus(304);
    }
}
