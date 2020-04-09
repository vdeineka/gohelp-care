package com.gohelp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.gohelp.domain.Request} entity.
 */
public class RequestDTO implements Serializable {
    
    private Long id;

    private String text;


    private Long userId;

    private String userLogin;

    private Long locationId;

    private Long typeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long geoJsonPointId) {
        this.locationId = geoJsonPointId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long requestTypeId) {
        this.typeId = requestTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RequestDTO requestDTO = (RequestDTO) o;
        if (requestDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), requestDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
            "id=" + getId() +
            ", text='" + getText() + "'" +
            ", userId=" + getUserId() +
            ", userLogin='" + getUserLogin() + "'" +
            ", locationId=" + getLocationId() +
            ", typeId=" + getTypeId() +
            "}";
    }
}
