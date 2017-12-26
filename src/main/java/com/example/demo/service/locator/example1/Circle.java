package com.example.demo.service.locator.example1;

import org.springframework.stereotype.Component;

/**
 * Created by eduardo on 21/12/17.
 */
@Component
public class Circle implements Shape {

    public void draw() {
        System.out.println("This is a cicle shape.");
    }

}
