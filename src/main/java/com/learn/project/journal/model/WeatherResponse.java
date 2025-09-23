package com.learn.project.journal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse {

    private Location location;
    private Current current;

    @Data
    private class Current {
        private String observation_time;
        private int temperature;
        private int weather_code;

    }

    @Data
    private class Location {
        private String name;
        private String country;
        private String region;
    }


}
