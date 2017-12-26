package com.example.demo.service.locator.example2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eduardo on 21/12/17.
 */
public class Service1 implements Service {

    public void execute(){
        System.out.println("Executing Service1");
    }

    @Override
    public String getName() {
        return "Service1";
    }
}

