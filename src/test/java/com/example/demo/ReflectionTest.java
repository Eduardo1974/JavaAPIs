package com.example.demo;

import com.example.demo.reflection.MetodosReflection;
import com.example.demo.reflection.exemplo1.Monitor;
import com.example.demo.reflection.exemplo2.ClasseExemplo;
import com.example.demo.reflection.exemplo3.User;
import com.example.demo.service.locator.example1.Config;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by eduardo on 21/12/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Config.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ReflectionTest {

    @Autowired
    private MetodosReflection reflection;

    @Test
    public void testEmptyFields() {
        Monitor monitor = new Monitor();
        monitor.setName("Eduardo");
        List<String> atributos = reflection.getAtributos(monitor);
        atributos.stream().forEach(s -> System.out.println(s));
    }

    @Test
    public void testFillNullFields() {
        Monitor monitor = new Monitor();
        monitor.setName("Eduardo");
        reflection.validateMonitor(monitor);
        System.out.println(monitor.toString());
    }

    @Test
    public void getCustomClass() {
        try {
            reflection.inserirInstancia("String", "java.lang.String");
            Class string = reflection.getClass("String");
            Assert.assertEquals("java.lang.String", string.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getObjectClass() {
        try {
            reflection.inserirInstancia("String", "java.lang.String");
            String string = (String) reflection.getObject("String");
            Assert.assertEquals(true, string.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getObjectWithParams() {
        try {
            reflection.inserirInstancia("ClasseExemplo", "com.example.demo.reflection.exemplo2.ClasseExemplo");
            Object[] params = new Object[1];
            params[0] = "java.lang.String";
            ClasseExemplo classeExemplo = (ClasseExemplo) reflection.getObject("ClasseExemplo", params);
            Assert.assertNotNull(classeExemplo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getErrorObjectWithoutParams() {
        try {
            reflection.inserirInstancia("ClasseExemplo", "com.example.demo.reflection.exemplo2.ClasseExemplo");
            Object[] params = new Object[0];
            ClasseExemplo classeExemplo = (ClasseExemplo) reflection.getObject("ClasseExemplo", params);
            Assert.assertNotNull(classeExemplo);
        } catch (NoSuchMethodException e) {
            Assert.assertNotNull("com.example.demo.reflection.exemplo2.ClasseExemplo.<init>()", e.getMessage());
        } catch (Exception e) {
        }
    }

    @Test
    public void validateObject() throws InvocationTargetException, IllegalAccessException {
        boolean edu = reflection.validateObject(User.builder().email("edu@hotmail.com").name("edu").pass("123456789").build());
        System.out.println(edu);
        Assert.assertTrue(edu);
    }
}