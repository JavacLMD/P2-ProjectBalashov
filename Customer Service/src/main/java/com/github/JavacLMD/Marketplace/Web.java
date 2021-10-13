package com.github.JavacLMD.Marketplace;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Web{
    public static void main(String[] args){
        SpringApplication.run(Web.class, args);
    }
    static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    static ByteBuf toByteBuf(Object o) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            OBJECT_MAPPER.writeValue(out, o);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ByteBufAllocator.DEFAULT.buffer().writeBytes(out.toByteArray());
    }
}