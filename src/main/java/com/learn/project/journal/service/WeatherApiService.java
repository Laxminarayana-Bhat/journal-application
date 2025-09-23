package com.learn.project.journal.service;

import com.learn.project.journal.model.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WeatherApiService {

    @Value("${weather-api-key}")
    private String API_KEY;

    private static final String API_ENDPOINT = "https://api.weatherstack.com/current?access_key=API_KEY&query=";

    @Autowired
    private final RestTemplate restTemplate;

    public WeatherResponse getWeatherData(String city) {
        String finalReqApi = API_ENDPOINT.replace("API_KEY", API_KEY) + city;
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("key", "val");
//        HttpEntity<String> entity=new HttpEntity<>("abcdef");
//        HttpEntity<String> entity2=new HttpEntity<>("abcdef",httpHeaders);
//        ResponseEntity<WeatherResponse> response2 = restTemplate.exchange(finalReqApi, HttpMethod.POST, entity, WeatherResponse.class);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalReqApi, HttpMethod.GET, null, WeatherResponse.class);
        // uri, http method, HttpEntity, response type or object
//        ResponseEntity<WeatherResponse> var = restTemplate.getForEntity("uri", WeatherResponse.class, Map.of("pageStart",1,"param2","value"));

        return response.getBody();
    }

    /*
     * Method	Purpose
     * getForObject()	GET request, returns body
     * getForEntity()	GET request, returns full ResponseEntity
     * postForObject()	POST request, returns body
     * postForEntity()	POST request, returns full response
     * put()	PUT request (no response)
     * delete()	DELETE request
     * exchange()	Full control over HTTP method, headers, body, etc.
     */
}
