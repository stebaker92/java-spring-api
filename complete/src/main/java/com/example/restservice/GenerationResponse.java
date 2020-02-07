package com.example.restservice;

import com.fasterxml.jackson.annotation.JsonAlias;

public class GenerationResponse {

    // We could use getters and setters here with different JsonProperty annotations
    // to serialize and deserialize to different JSON properties
    // This uses 'generation' during serialization, but allows 'id' as an alias during deserialization.
    // This still allows 'generation' to be deserialized as well, though.
    @JsonAlias({"id"})
    public int generation;

    @JsonAlias({"pokemon_species"})
    public PokemonSpeciesResponse[] pokemonSpecies;
}
