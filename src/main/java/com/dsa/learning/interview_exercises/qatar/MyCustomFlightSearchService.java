package com.dsa.learning.interview_exercises.qatar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MyCustomFlightSearchService {

    @Autowired
    static FlightAvailabilityServiceImpl flightAvailabilityService;

    public List<String> searchFlights(SearchQuery searchQuery){

        List<String> flightList = new ArrayList<>();

        FlightAvailabilityRequest request = new FlightAvailabilityRequest(
                searchQuery.getOrigin(),
                searchQuery.getDestination(),
                searchQuery.getDepartureDate(),
                searchQuery.getNoOfTravellers()
        );

        List<FlightSummary> flights = (List<FlightSummary>) flightAvailabilityService.getAvailableFlights(request);

        // NOTE: Remember to pass he type of Stream that we are creating - here its 'FlightSummary' ....
        Stream<FlightSummary> stream = flights.stream();

        if(searchQuery.getIsCancellable() != null){
            // It keeps only those elements in the stream where the item's cancellation status matches what the user is asking for ....
            stream.filter(flight -> flight.isCancellationPossible() == searchQuery.getIsCancellable());
        }

        if(searchQuery.getMaxPrice() != null){
            stream.filter(f -> f.getAveragePriceInUsd() < searchQuery.getMaxPrice());
        }

        if(searchQuery.getSortByPreference() == SearchQuery.SortByPreference.LENGTH){
            stream.sorted(Comparator.comparing(f -> getFlightDurationMillis(f)));
        } else {
            stream.sorted(Comparator.comparing(f -> f.getAveragePriceInUsd()));
        }

        int limit = searchQuery.getLimit() != null ? searchQuery.getLimit() : 3;
        stream.limit(limit);


        flightList = stream.map(f -> f.getAirlineCode()).collect(Collectors.toList());

        // Immutable list ....
        List<String> newList = List.copyOf(flightList);
        /**
         * ### ✅ This line works:
         * flightList = stream.map(FlightSummary::getAirlineCode).collect(Collectors.toList());
         *
         * #### What it does:
         * * `map(FlightSummary::getAirlineCode)` transforms the stream of `FlightSummary` objects into a stream of `String` airline codes.
         * * So now you're collecting a list of `String`.
         *
         * 👉 **Type of `flightList`:** `List<String>`
         *
         * ### ❌ This one **does not work** as expected:
         *
         * // stream.map(f -> f.getAirlineCode()); ❌ Not collected, has no effect
         * flightList = stream.collect(Collectors.toUnmodifiableList()); // ✅ But collects original FlightSummary objects
         *
         * #### Problem:
         *
         * * The `map()` operation here is written but **not stored or reused** — meaning it's a **no-op** (a wasted operation).
         * * Then you call `collect(...)` on the **original stream**, which still contains `FlightSummary` objects, not `String`.
         *
         * 👉 **Type of `flightList`:** `List<FlightSummary>` (but you're probably expecting `List<String>`)
         *
         * ### ✅ Fix:
         *
         * You must **chain the operations** properly. Either:
         * flightList = stream
         *     .map(FlightSummary::getAirlineCode)
         *     .collect(Collectors.toUnmodifiableList());
         *
         * Or if you want to collect `FlightSummary` itself:
         * flightSummaryList = stream.collect(Collectors.toUnmodifiableList()); // returns original list of FlightSummary
         *
         * ### 📌 Summary:
         *
         * | Code                           | Result                                          |
         * | ------------------------------ | ----------------------------------------------- |
         * | `stream.map(...).collect(...)` | Applies transformation, then collects           |
         * | `stream.map(...)` only         | Does nothing unless result is used              |
         * | `stream.collect(...)` directly | Collects stream **as-is**, without transforming |
         */
        //stream.map(f -> f.getAirlineCode());
       // flightList = stream.collect(Collectors.toUnmodifiableList());

        return flightList;
    }

    private long getFlightDurationMillis(FlightSummary flight){
        return flight.getDepartureTime().getTime() - flight.getArrivalTime().getTime();
    }

}
