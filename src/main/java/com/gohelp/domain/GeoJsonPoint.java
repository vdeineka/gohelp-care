package com.gohelp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A GeoJsonPoint.
 */
@Entity
@Table(name = "geo_json_point")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GeoJsonPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "x")
    private Double x;

    @Column(name = "y")
    private Double y;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public GeoJsonPoint x(Double x) {
        this.x = x;
        return this;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public GeoJsonPoint y(Double y) {
        this.y = y;
        return this;
    }

    public void setY(Double y) {
        this.y = y;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeoJsonPoint)) {
            return false;
        }
        return id != null && id.equals(((GeoJsonPoint) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GeoJsonPoint{" +
            "id=" + getId() +
            ", x=" + getX() +
            ", y=" + getY() +
            "}";
    }
}
