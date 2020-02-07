package com.example.restservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties()
public class ApplicationProperties {

    public int latestGeneration;

    //    getters and setters are required for Spring configuration
    public int getLatestGeneration() {
        return latestGeneration;
    }

    public void setLatestGeneration(int latestGeneration) {
        this.latestGeneration = latestGeneration;
    }

    private int favouritePokemon;

    public int getFavouritePokemon() {
        return favouritePokemon;
    }

    public void setFavouritePokemon(int favouritePokemon) {
        this.favouritePokemon = favouritePokemon;
    }
}
