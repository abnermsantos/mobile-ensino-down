package br.edu.ifspsaocarlos.sdm.cienciasdown.Model;

import java.io.Serializable;

/**
 * Created by Abner - Manutençao on 11/09/2015.
 */
public class Aluno implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nome;
    private String turma;
    private String nascimento;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }
}