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
    private GenerationResponse getGeneration(int gen) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "My Java API");

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<GenerationResponse> httpResponse = restTemplate.exchange("https://pokeapi.co/api/v2/generation/" + gen, HttpMethod.GET, request, GenerationResponse.class);

        return httpResponse.getBody();
    }
}
