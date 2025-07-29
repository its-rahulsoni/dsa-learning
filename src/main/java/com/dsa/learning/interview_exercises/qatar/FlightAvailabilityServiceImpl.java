package com.dsa.learning.interview_exercises.qatar;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public class FlightAvailabilityServiceImpl implements FlightAvailabilityService{

    @Override
    public Collection<FlightSummary> getAvailableFlights(FlightAvailabilityRequest request){
        List<FlightSummary> flights = new ArrayList<>();

        FlightSummary fs1 = new FlightSummary("QTRARWY", new Date("01-08-2025"), new Date("02-08-2025"), 100, true);
        FlightSummary fs2 = new FlightSummary("QTRARWY", new Date("02-08-2025"), new Date("02-08-2025"), 200, false);
        FlightSummary fs3 = new FlightSummary("QTRARWY", new Date("01-08-2025"), new Date("02-08-2025"), 400, true);
        FlightSummary fs4 = new FlightSummary("QTRARWY", new Date("03-08-2025"), new Date("04-08-2025"), 600, true);
        FlightSummary fs5 = new FlightSummary("QTRARWY", new Date("01-08-2025"), new Date("02-08-2025"), 460, false);

        return flights;
    }

}
