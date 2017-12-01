package com.example.demo.reflection;

import com.example.demo.reflection.monitor.Monitor;
import com.example.demo.reflection.monitor.PresetMonitorConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eduardo on 01/12/17.
 */
public class MetodosReflection {

    @Autowired
    private PresetMonitorConfiguration presetMonitorConfiguration;

    /**
     * Metodo que verifica quais campos estao nulos de uma classe e adiciona o nome deles em uma lista
     * @param o
     * @return
     */
    public static List<String> getAtributos(Object o) {
        try {
            List<String> lista = new ArrayList<>();
            Class<?> c = o.getClass(); //obtem a referencia da classe passada
            for(Field f : c.getFields()) {
                Object value = f.get(o);
                if(value == null) {
                    lista.add(f.getName());
                }
            }
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * O metodo e reponsavel por validar se todos os campos obrigatorios
     * foram informados pelo usuario. Caso algum campo nao tenha sido
     * informado, o metodo ira obter o valor correspodente do preset padrao
     * em memoria
     * @param monitor
     */
    private void validateMonitor(final Monitor monitor) {
        Class<Monitor> aClass = Monitor.class;
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            Object value = null;
            try {
                value = field.get(monitor);
                if (value == null) {
                    field.set(monitor, presetMonitorConfiguration.configuration().get(field.getName()));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
