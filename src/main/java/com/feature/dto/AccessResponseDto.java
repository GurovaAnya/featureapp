package com.feature.dto;

public class AccessResponseDto {
    private boolean canAccess;

    public AccessResponseDto() {
    }

    public AccessResponseDto(boolean canAccess){
       this.canAccess = canAccess;
    }

    public boolean getCanAccess(){return canAccess;}
    public void setCanAccess(boolean canAccess){this.canAccess = canAccess;}
}
