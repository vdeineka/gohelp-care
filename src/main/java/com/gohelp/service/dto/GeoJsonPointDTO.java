package com.gohelp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.gohelp.domain.GeoJsonPoint} entity.
 */
public class GeoJsonPointDTO implements Serializable {
    
    private Long id;

    private Double x;

    private Double y;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GeoJsonPointDTO geoJsonPointDTO = (GeoJsonPointDTO) o;
        if (geoJsonPointDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), geoJsonPointDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GeoJsonPointDTO{" +
            "id=" + getId() +
            ", x=" + getX() +
            ", y=" + getY() +
            "}";
    }
}
