package com.example.demo;

import com.example.demo.service.locator.example1.Config;
import com.example.demo.service.locator.example1.Shape;
import com.example.demo.service.locator.example1.ShapeFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by eduardo on 21/12/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={Config.class},webEnvironment=SpringBootTest.WebEnvironment.NONE)
public class ShapeFactoryTest {

    @Autowired
    private ShapeFactory shapeFactory;

    @Test
    public void testGetShape() {
        Shape circle = shapeFactory.getShape("circle");
        Assert.assertNotNull(circle);
        circle.draw();
        Shape square = shapeFactory.getShape("square");
        Assert.assertNotNull(square);
        square.draw();
    }

    @Test
    public void testGetShapeForWrongShape(){
        Shape rectangle = shapeFactory.getShape("rectangle");
        Assert.assertNotNull(rectangle);
        rectangle.draw();
    }

}