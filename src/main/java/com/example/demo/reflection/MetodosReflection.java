package com.example.demo.reflection;

import com.example.demo.reflection.exemplo1.Monitor;
import com.example.demo.reflection.exemplo1.PresetMonitorConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by eduardo on 01/12/17.
 */
@Component
public class MetodosReflection {

    //TIPOS DE LEITURA
    /*
        Class class = String.class
        Class class = obj.getClass()
        Class class = Class.fotName("java.lang.String")

     */

    private Map<String,String> mapa = new HashMap<>();

    public void inserirInstancia(String key, String value) {
        this.mapa.put(key, value);
    }

    /**
     * Retorna o Class da chave presente no mapa
     * @param chave
     * @return
     * @throws Exception
     */
    public Class getClass(String chave) throws Exception{
        String valor = mapa.get(chave);
        if(valor != null){
            return Class.forName(valor);
        }else{
            throw new RuntimeException("Chave inválida");
        }
    }

    /**
     * Retorna uma nova instancia do mapa de classes
     * @param chave
     * @return
     * @throws Exception
     */
    public Object getObject(String chave) throws Exception{
        return getClass(chave).newInstance();
    }

    /**
     * Retorna a instancia de classe de acordo com os parametros informados
     * Caso nao encontre o construtor informado seja retornado uma excecao
     * @param chave
     * @param params
     * @return
     * @throws Exception
     */
    public Object getObject(String chave, Object[] params) throws Exception{
        Class<?>[] tiposConstrutor = new Class<?>[params.length];
        for(int i=0; i<tiposConstrutor.length; i++){
            tiposConstrutor[i] = params[i].getClass();
        }
        Constructor<?> c = getClass(chave).getConstructor(tiposConstrutor);
        return c.newInstance(params);
    }

    ///////////////////////////////////////////////////////////////////////////////////////

    @Autowired
    private PresetMonitorConfiguration presetMonitorConfiguration;

    /**
     * c.getDeclaredFields() //obtem atributos privados de classe filha
     * c.getFields() //obtem somente publicos
     */

    /**
     * Metodo que verifica quais campos estao nulos de uma classe e adiciona o nome deles em uma lista
     * @param o
     * @return
     */
    public List<String> getAtributos(Object o) {
        try {
            List<String> lista = new ArrayList<>();
            Class<?> c = o.getClass(); //obtem a referencia da classe passada
            for(Field f : c.getDeclaredFields()) {
                f.setAccessible(true);
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
    public void validateMonitor(final Monitor monitor) {
        Class<Monitor> aClass = Monitor.class;
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
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

    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * Metodo que invoca metodos validate via reflection
     * @param obj
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public boolean validateObject(Object obj) {

        Boolean result =  true;

        Class<?> clazz = obj.getClass();

        for(Method m: clazz.getDeclaredMethods()) {
            if(m.getName().startsWith("validate")
                    && m.getReturnType() == boolean.class
                    && m.getParameterTypes().length == 0) {

                try {
                    result = (Boolean) m.invoke(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {

                    //Retorna e excecao real do metodo invocado
                    e.getTargetException().printStackTrace();
                }
            }
        }

        return result;
    }

}
