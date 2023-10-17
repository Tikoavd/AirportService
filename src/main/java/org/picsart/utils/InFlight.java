package org.picsart.utils;

import org.picsart.service.AirportService;

public final class InFlight implements Location {
    private static volatile InFlight instance;

    public static InFlight getInstance() {
        InFlight localInstance = instance;
        if (localInstance == null) {
            synchronized (AirportService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new InFlight();
                }
            }
        }
        return localInstance;
    }

    private InFlight() {}
}
