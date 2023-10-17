package org.picsart.service;

import org.picsart.entitites.Airplane;
import org.picsart.entitites.Airport;
import org.picsart.utils.Time;

import java.util.*;

public final class AirportService {
    private static volatile AirportService instance;

    public List<Airport> airports;
    public List<Airplane> airplanes;
    public Time localTime;

    public static AirportService getInstance() {
        AirportService localInstance = instance;
        if (localInstance == null) {
            synchronized (AirportService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new AirportService();
                }
            }
        }
        return localInstance;
    }

    public void addAirplane(Airplane airplane, Airport airport) {
        airport.airplanes.add(airplane);
        airplanes.add(airplane);
    }

    public void log(String message) {
        synchronized (System.out) {
            System.out.println(localTime + ": " + message);
        }
    }

    private AirportService() {
        airports = new ArrayList<>();
        airplanes = new ArrayList<>();
        localTime = new Time(0, 0);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                localTime.plusMinute();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 0, 1000);
    }
}
