package com.github.JavacLMD.Marketplace.controller;

import com.github.JavacLMD.Marketplace.domain.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.HashSet;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemControllerTest {

    @Autowired
    ItemController context;

    @Test
    public void findAllItemsTest() {
        WebTestClient
                .bindToController(context)
                .build()
                .get()
                .uri("/items")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(ParameterizedTypeReference.forType(Item.class));
    }

    @Test
    public void findByItemIdTest() {
        WebTestClient
                .bindToController(context)
                .build()
                .get()
                .uri("/items/byId/1")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Item.class);
    }

    @Test
    public void findByItemNameTest() {
        WebTestClient
                .bindToController(context)
                .build()
                .get()
                .uri("/items/byName/{name}", "Normandy")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(ParameterizedTypeReference.forType(Item.class));
    }

    @Test
    public void findByItemCategoryTest() {
        WebTestClient
                .bindToController(context)
                .build()
                .get()
                .uri("/items/byCategory/{category}", "Star%20Wars")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(ParameterizedTypeReference.forType(Item.class));
    }

    @Test
    public void updateItemTest() {
        //Generated Row: Item{id=1, name='Millennium Falcon', price=3000.0, category=[Star Wars, Figurine, Spaceship]}
        Item expecteditem = new Item(1, "Millennium Falcon", 2444, "Star Wars", "Figurine", "Spaceship");

        WebTestClient
                .bindToController(context)
                .build()
                .put()
                .uri("/items/update/{id}", 1)
                .body(Mono.just(new Item(-1, "", 2444, new HashSet<>())), Item.class)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Item.class).isEqualTo(expecteditem);
    }


}