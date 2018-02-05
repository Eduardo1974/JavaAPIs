package com.example.demo;


import com.example.demo.collections.exemplo2.Aula;
import com.example.demo.collections.exemplo2.Curso;
import com.example.demo.service.locator.example1.Config;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by eduardo on 21/12/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Config.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CollectionsTest {

    @Test
    public void tamanhoListaTest() {
        Curso curso = new Curso("História");
        curso.adicionaAula(new Aula("Aula2",50));
        curso.adicionaAula(new Aula("Aula1",50));

        List<Aula> aulas = new ArrayList<>(curso.getAulas()); //Criando uma nova copia (alocação de memoria) diferente da recebida
        Collections.sort(aulas);
        aulas.stream().forEach(a -> System.out.println(a.getNome()));

        Assert.assertEquals(2,curso.getAulas().size());
    }

    @Test
    public void formaErradaDeManipularLista() {
        try {
            Curso curso = new Curso("História");
            curso.getAulas().add(new Aula("Aula1",50));
            // Collections.sort(curso.getAulas()); //Tambem nao posso ordenar pois estaria alterando elementos da lista
        } catch (UnsupportedOperationException error) {
            error.printStackTrace();
        }
    }

}