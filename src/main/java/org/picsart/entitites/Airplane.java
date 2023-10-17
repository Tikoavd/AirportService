package org.picsart.entitites;

import org.picsart.utils.InFlight;
import org.picsart.utils.Location;
import org.picsart.utils.Time;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class Airplane {
    public final UUID uuid;
    public List<Flight> flights;
    public Location location;

    public final int speed = 10;

    public Airplane(Location location) {
        this.uuid = UUID.randomUUID();
        this.location = location;
        flights = new ArrayList<>();
    }

    public boolean planFlight(Flight flight) {
        if (getLocationByTime(flight.startTime) instanceof InFlight
                || getLocationByTime(flight.endTime) instanceof InFlight
                || hasFlightsInTimeRange(flight.startTime, flight.endTime)) {
            return false;
        }
        if (getLocationByTime(flight.startTime) != flight.startDestination.location) {
            return false;
        }

        flights.add(flight);
        flight.start();
        return true;
    }

    public Location getLocationByTime(Time time) {
        Time lastTime = new Time(0, 0);
        Location lastLocation = location;
        for (Flight flight : flights) {
            if (flight.startTime.compareTo(time) <= 0 && flight.endTime.compareTo(time) > 0) {
                return InFlight.getInstance();
            }
            if (flight.endTime.compareTo(time) <= 0 && flight.endTime.compareTo(lastTime) > 0) {
                lastTime = flight.endTime;
                lastLocation = flight.endDestination.location;
            }
        }
        return lastLocation;
    }

    public boolean hasFlightsInTimeRange(Time start, Time end) {
        for (Flight flight : flights) {
            if (flight.startTime.compareTo(start) >= 0 && flight.startTime.compareTo(end) <= 0) {
                return true;
            }
            if (flight.endTime.compareTo(start) >= 0 && flight.endTime.compareTo(end) <= 0) {
                return true;
            }
        }
        return false;
    }
}
