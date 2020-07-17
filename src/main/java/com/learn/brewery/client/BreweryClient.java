package com.learn.brewery.client;

import com.learn.brewery.model.BeerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@ConfigurationProperties(value = "com.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    private String apiHost;
    public static final String BEER_PATH_V1 = "/api/v1/beer/";

    /**
     * Important thing to note in spring boot is that we require to create RestTemplateBuilder
     * This helps us to configure the RestTemplate, so we might want to configure security on it
     * or some type of HTTP Client Library. We can configure number of things with the RestTemplateBuilder library.
     * Consider it as a best practice, create the spring builder and then create Rest Template from that.
     * In this way we are using standard env. RestTemplate. So it will pick up anything that we would have setup globally.
     * Alternatively,
     * If we have set up RestTemplate Locally, we could not pick up what the Spring Boot has setup globally.
     */

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID beerId){
        return restTemplate.getForObject(apiHost + BEER_PATH_V1 + beerId.toString(), BeerDto.class);
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }
}
