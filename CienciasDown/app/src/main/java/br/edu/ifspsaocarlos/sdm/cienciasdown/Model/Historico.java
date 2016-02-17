package br.edu.ifspsaocarlos.sdm.cienciasdown.Model;

import java.io.Serializable;

/**
 * Created by Abner - Manutençao on 17/02/2016.
 */
public class Historico implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String aluno;
    private String turma;
    private String disciplina;
    private String tarefa;
    private String tentativas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public String getTentativas() {
        return tentativas;
    }

    public void setTentativas(String tentativas) {
        this.tentativas = tentativas;
    }
}
