package com.example.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

@RestController
public class PokemonController {

    @Autowired
    ApplicationProperties appProperties;

    @GetMapping("/generations/random")
    public GenerationResponse getRandomGeneration() {

        Random random = new Random();
        int generation = random.nextInt(appProperties.latestGeneration) + 1;

        return getGeneration(generation);
    }

    @RequestMapping(value = "/pokemon/random/image", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public void getRandomImage(HttpServletResponse response) throws IOException {

        GenerationResponse randomGeneration = getRandomGeneration();

        int pokemonCount = randomGeneration.pokemonSpecies.length;

        Random r = new Random();
        int randomId = r.nextInt(pokemonCount) + 1;

        String name = randomGeneration.pokemonSpecies[randomId].name;

        PokemonResponse pokemon = getPokemon(name);

        String url = pokemon.sprites.frontDefault;

        BufferedImage img = ImageIO.read(new URL(url));
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ImageIO.write(img, "png", bao);
        response.getOutputStream().write(bao.toByteArray());
    }

    private PokemonResponse getPokemon(String name) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "My Java API");

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<PokemonResponse> httpResponse = restTemplate.exchange("https://pokeapi.co/api/v2/pokemon/" + name, HttpMethod.GET, request, PokemonResponse.class);

        return httpResponse.getBody();
    }

    private GenerationResponse getGeneration(int gen) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "My Java API");

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<GenerationResponse> httpResponse = restTemplate.exchange("https://pokeapi.co/api/v2/generation/" + gen, HttpMethod.GET, request, GenerationResponse.class);

        return httpResponse.getBody();
    }
}
