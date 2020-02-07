package com.example.restservice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonResponse {
    @JsonProperty("sprites")
    public SpritesResponse sprites;
}

class SpritesResponse {
    @JsonProperty("front_default")
    public String frontDefault;
}