package com.github.JavacLMD.Marketplace.service;

import com.github.JavacLMD.Marketplace.ItemApplication;
import com.github.JavacLMD.Marketplace.domain.Item;

import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItemApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemServiceTest {

    @Autowired
    private ItemService service;

    @Test
    public void findAllTest() {
        Flux<Item> findAll = service.findAll();
        //findAll.subscribe(Assertions::assertNotNull, Assertions::fail);
        StepVerifier.create(findAll.log())
                .expectComplete();
    }

    @Test
    public void findByIdTest() {
        Mono<Item> findById = service.findById(1);

        StepVerifier.create(findById)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void findByCategoryTest() {
        String findCategory = "Star Wars";
        Flux<Item> findByCategory = service.findByCategory(findCategory);
        findByCategory.subscribe(System.out::println, e -> Assertions.fail(e));
    }

    @Test
    public void findByNameTest() {
        String name = "Normandy";
        Flux<Item> findByName = service.findByName("Normandy");
        StepVerifier.create(findByName)
                .expectNextMatches(item -> item.getName().equals(name))
                .verifyComplete();
    }

    @Test
    public void updateTest() {
        Item item = new Item(1, "Millennium Falcon", 2555, "Star Wars", "Figurine", "Spaceship");
        Mono<Item> updateMono = service.update(item.getId(), item);

        StepVerifier.create(updateMono)
                .expectNextMatches(found -> found.getPrice() == item.getPrice())
                .verifyComplete();
    }



}