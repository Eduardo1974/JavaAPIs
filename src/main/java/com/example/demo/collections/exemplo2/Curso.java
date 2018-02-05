package com.example.demo.collections.exemplo2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Curso {

    private String nome;
    private List<Aula> aulas;

    public Curso(String nome) {
        this.nome = nome;
        this.aulas = new ArrayList<>();
    }

    public void adicionaAula(final Aula aula) {
        this.aulas.add(aula);
    }

    public List<Aula> getAulas() {
        return Collections.unmodifiableList(aulas);
    }

    public int getTempoTotal() {
        return this.aulas.stream().mapToInt(Aula::getTempo).sum();
    }

    @Override
    public String toString() {
        return "[Curso:"+ this.nome+",tempo total:"+ this.getTempoTotal()+ ", aulas: ["+ this.aulas+"] ]";
    }
}
