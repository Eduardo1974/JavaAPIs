package com.example.demo.collections.exemplo2;

public class Aula implements Comparable<Aula> {

    private String nome;
    private int tempo;

    public Aula(String nome, int tempo) {
        this.nome = nome;
        this.tempo = tempo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    @Override
    public int compareTo(Aula o) {
        return this.nome.compareTo(o.getNome());
    }

    @Override
    public String toString() {
        return "[Aula: " + this.nome + ", " + this.tempo + " minutos]";
    }
}
