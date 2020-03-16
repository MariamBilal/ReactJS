package com.example.demo.demo;

import com.example.demo.demo.Event;
import com.example.demo.demo.Group;
import com.example.demo.demo.GroupRepositry;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

    private final GroupRepositry repositry;

    public Initializer(GroupRepositry repositry) {
        this.repositry = repositry;
    }

    @Override
    public void run(String... strings){
        Stream.of("Denver JUG", "Utah JUG", "Seattle JUG","Richmond JUG").forEach(name -> repositry.save(new Group(name)));
        

        repositry.findAll().forEach(System.out::println);
    }
}
