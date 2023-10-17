package org.picsart;

import org.picsart.entitites.*;
import org.picsart.service.AirportService;
import org.picsart.utils.Coordinate;
import org.picsart.utils.Time;

public class Main {
    public static void main(String[] args) {
        initializeFakeService();
    }

    static void initializeFakeService() {
        AirportService service = AirportService.getInstance();

        for (int i = 0; i < 5; i++) {
            service.airports.add(new Airport(
                    "Airport - " + (i + 1),
                    new Coordinate(i * i * 2, i * i * 2)
            ));

            for (int j = 0; j < 5; j++) {
                Airplane airplane = new Airplane(service.airports.get(i).location);
                try {
                    airplane.planFlight(new Flight(service.airports.get(i), service.airports.get(j), new Time(0, j * 10), airplane));
                } catch (Exception ignored) {

                }
                service.addAirplane(airplane, service.airports.get(i));
            }
        }

        service.airports.forEach(airport -> service.log(airport.toString()));
    }
}
