/*package com.github.ProjectBalashov;

import ch.qos.logback.classic.Logger;
import com.datastax.oss.driver.api.core.CqlSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.*;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.scheduling.annotation.EnableAsync;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
@EnableAsync
@EnableMBeanExport
@Configuration
@ComponentScan
public class CustomerConfig {
    @Autowired
    @Primary
    @Bean
    public CacheManager jdkCacheManager() {
        return new ConcurrentMapCacheManager("cache");
    }
    @Bean
    public InstrumentationLoadTimeWeaver loadTimeWeaver()  throws Throwable {
        InstrumentationLoadTimeWeaver loadTimeWeaver = new InstrumentationLoadTimeWeaver();
        return loadTimeWeaver;
    }
    @Bean
    public Logger logger() {
        return (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("main");
    }
    @Bean
    public CqlSession session() {
        return CqlSession.builder().build();
    }

    @Bean

    public DisposableServer disposableServer() {
        CustomerService db = new CustomerService(session(), logger());
        return HttpServer.create()
                .port(8080)
                .route(routes ->
                        routes.get("/customers", (request, response) ->
                                response.send(db.getAllCustomers().map(Web::toByteBuf)
                                        .log("http-server")))
                                .get("/customers/{param}", (request, response) ->
                                        response.send(db.getCustomerById(Integer.parseInt(request.param("param"))).map(Web::toByteBuf)
                                                .log("http-server")))

                )
                .bindNow();

    }


    static final ObjectMapper MAPPER = new ObjectMapper();
    private ByteBuf toByteBuf(Object o) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            MAPPER.writeValue(out, o);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ByteBufAllocator.DEFAULT.buffer().writeBytes(out.toByteArray());
    }
}*/