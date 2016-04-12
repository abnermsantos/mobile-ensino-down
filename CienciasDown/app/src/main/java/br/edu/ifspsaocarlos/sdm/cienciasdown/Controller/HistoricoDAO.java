package br.edu.ifspsaocarlos.sdm.cienciasdown.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifspsaocarlos.sdm.cienciasdown.Model.Historico;

/**
 * Created by Abner - Manutençao on 17/02/2016.
 */
public class HistoricoDAO {
    private Context context;
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public HistoricoDAO(Context context) {
        this.context = context;
    }

    public void open() throws SQLException {
        dbHelper = new SQLiteHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
        database.close();
    }

    public void create(Historico h) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.ALUNO, h.getAluno());
        values.put(SQLiteHelper.ALUNO_TURMA, h.getTurma());
        values.put(SQLiteHelper.DISCIPLINA, h.getDisciplina());
        values.put(SQLiteHelper.TAREFA, h.getTarefa());
        values.put(SQLiteHelper.TENTATIVAS, h.getTentativas());
        database.insert(SQLiteHelper.TABLE_HISTORICO, null, values);
    }

    public List<Historico> buscaIndividual(String nome) {

        List<Historico> historicos = new ArrayList<Historico>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_HISTORICO, new String[] { SQLiteHelper.KEY_HISTORICO,
                        SQLiteHelper.ALUNO, SQLiteHelper.ALUNO_TURMA, SQLiteHelper.DISCIPLINA, SQLiteHelper.TAREFA,
                        SQLiteHelper.TENTATIVAS}, SQLiteHelper.ALUNO + "=?", new String[] { nome },
                        null, null, SQLiteHelper.ALUNO);

        if (cursor!=null)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Historico historico = new Historico();
                historico.setId(cursor.getString(0));
                historico.setAluno(cursor.getString(1));
                historico.setTurma(cursor.getString(2));
                historico.setDisciplina(cursor.getString(3));
                historico.setTarefa(cursor.getString(4));
                historico.setTentativas(cursor.getString(5));
                historicos.add(historico);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return historicos;
    }

    public List<Historico> buscaGrupo(String turma, String disciplina) {

        List<Historico> historicos = new ArrayList<Historico>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_HISTORICO, new String[] { SQLiteHelper.KEY_HISTORICO,
                        SQLiteHelper.ALUNO, SQLiteHelper.ALUNO_TURMA, SQLiteHelper.DISCIPLINA, SQLiteHelper.TAREFA,
                        SQLiteHelper.TENTATIVAS}, SQLiteHelper.ALUNO_TURMA + "=? AND " + SQLiteHelper.DISCIPLINA + "=?",
                        new String[] {turma, disciplina}, null, null, SQLiteHelper.ALUNO);

        if (cursor!=null)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Historico historico = new Historico();
                historico.setId(cursor.getString(0));
                historico.setAluno(cursor.getString(1));
                historico.setTurma(cursor.getString(2));
                historico.setDisciplina(cursor.getString(3));
                historico.setTarefa(cursor.getString(4));
                historico.setTentativas(cursor.getString(5));
                historicos.add(historico);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return historicos;
    }
}
