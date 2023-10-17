package org.picsart.entitites;

import org.picsart.service.AirportService;
import org.picsart.utils.InFlight;
import org.picsart.utils.Time;

public class Flight extends Thread {
    public final Airplane airplane;
    public final Airport startDestination;
    public final Airport endDestination;
    public final Time startTime;
    public final Time endTime;


    public Flight(Airport startDestination, Airport endDestination, Time startTime, Airplane airplane)
            throws IllegalArgumentException {
        if (startDestination == endDestination) {
            throw new IllegalArgumentException();
        } else {
            this.startDestination = startDestination;
            this.endDestination = endDestination;
            this.startTime = startTime;
            this.endTime = startTime.plusMinutes(
                    startDestination.location.distanceTo(endDestination.location) / airplane.speed
            );
            this.airplane = airplane;
        }
    }

    @Override
    public void run() {
        try {
            sleep(startTime.differenceMillis(AirportService.getInstance().localTime));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (airplane) {
            airplane.location = InFlight.getInstance();
            startDestination.airplanes.remove(airplane);
        }

        AirportService.getInstance().log("Airplane " + airplane.uuid + " took off from " + startDestination.name +
                " towards " + endDestination.name);

        try {
            sleep(startTime.differenceMillis(endTime));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (airplane) {
            airplane.location = endDestination.location;
            airplane.flights.remove(this);
            endDestination.airplanes.add(airplane);
        }

        AirportService.getInstance().log("Airplane " + airplane.uuid + " landed in " + endDestination.name);
    }
}
