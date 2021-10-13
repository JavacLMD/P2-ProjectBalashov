package com.sakuramatrix.microservicesezin.controller;

import com.sakuramatrix.microservicesezin.domain.Item;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/items")
public class ItemController {

        String url = "http://localhost:8080";
        WebClient webClient = WebClient.builder().baseUrl(url).build();

       void  setWebClient() {
                webClient.get()
                        .uri("items/byId/{id}", "item_id")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(Item.class)
                        .subscribe(System.out::println);
                webClient.get()
                        .uri("items ", "item_id")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(Item.class)
                        .subscribe(System.out::println);
        }
}
