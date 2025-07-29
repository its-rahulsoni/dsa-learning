package com.dsa.learning.interview_exercises.qatar;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.Date;

@Value
@Builder
public class SearchQuery {

    private String origin;
    private String destination;
    private Date departureDate;
    int noOfTravellers;
    Boolean isCancellable;
    Double maxPrice;
    SortByPreference sortByPreference;
    Integer limit;

    enum SortByPreference{
        LENGTH, PRICE
    }

}
