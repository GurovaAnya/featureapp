package com.feature.dto;

public class AccessRequestDto {
    private String featureName;
    private String email;
    private boolean enabled;

    public AccessRequestDto(String featureName, String email, boolean enabled) {
        this.featureName = featureName;
        this.email = email;
        this.enabled = enabled;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}