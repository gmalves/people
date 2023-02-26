package com.masson.people.third_party.viacep;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.masson.people.third_party.config.HttpServerClient;
import com.masson.people.third_party.viacep.response.AddressResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Component
public class ViaCepClient extends HttpServerClient {

    public AddressResponse findByZipCode(String zipCode) {
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://viacep.com.br/ws/" +zipCode+ "/json"))
                    .timeout(Duration.ofSeconds(5))
                    .build();

            var response = client().send(request, HttpResponse.BodyHandlers.ofString());
            return new ObjectMapper().readValue(response.body(), AddressResponse.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}