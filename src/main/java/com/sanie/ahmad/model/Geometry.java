package com.sanie.ahmad.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sanie.ahmad.util.CustomCoordinatesDeserializer;

public class Geometry {

    private String type;
    @JsonDeserialize(using = CustomCoordinatesDeserializer.class)
    private Object coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Object coordinates) {
        this.coordinates = coordinates;
    }
}
