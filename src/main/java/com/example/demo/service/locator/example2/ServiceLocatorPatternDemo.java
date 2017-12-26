package com.example.demo.service.locator.example2;

/**
 * Created by eduardo on 21/12/17.
 */
public class ServiceLocatorPatternDemo {

    public static void main(String[] args) {

        Service service = ServiceLocator.getService("Service1");
        service.execute();
        service = ServiceLocator.getService("Service2");
        service.execute();
        service = ServiceLocator.getService("Service1");
        service.execute();
        service = ServiceLocator.getService("Service2");
        service.execute();
    }
}
