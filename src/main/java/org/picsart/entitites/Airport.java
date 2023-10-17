package org.picsart.entitites;

import org.picsart.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Airport {

    public final String name;
    public final Coordinate location;

    public List<Airplane> airplanes;

    public Airport(String name, Coordinate location) {
        this.name = name;
        this.location = location;
        this.airplanes = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name).append(" has ").append(airplanes.size()).append(" airplanes: ");
        airplanes.forEach(airplane -> {
            builder.append(airplane.uuid).append(", ");
        });
        builder.delete(builder.length() - 3, builder.length() - 1);
        return builder.toString();
    }
}
