package com.example.avito.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class BaselineMatrixId implements Serializable {
    @Column(name = "microcategory_id")
    private int microcategoryId;

    @Column(name = "location_id")
    private int locationId;

    public int getMicrocategoryId() {
        return microcategoryId;
    }

    public void setMicrocategoryId(int microcategoryId) {
        this.microcategoryId = microcategoryId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}
