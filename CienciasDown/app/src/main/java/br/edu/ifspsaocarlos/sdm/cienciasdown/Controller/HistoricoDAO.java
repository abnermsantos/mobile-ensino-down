/*
 Copyright 2016 Abner Moises dos Santos Gomes

 This file is part of Ciência Interativa.

 Ciência Interativa is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 Foobar is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/
package br.edu.ifspsaocarlos.sdm.cienciasdown.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifspsaocarlos.sdm.cienciasdown.Model.Historico;

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

    public List<Historico> buscaIndividual(String aluno, String disciplina) {

        List<Historico> historicos = new ArrayList<Historico>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_HISTORICO, new String[] { SQLiteHelper.KEY_HISTORICO,
                        SQLiteHelper.ALUNO, SQLiteHelper.ALUNO_TURMA, SQLiteHelper.DISCIPLINA, SQLiteHelper.TAREFA,
                        SQLiteHelper.TENTATIVAS}, SQLiteHelper.ALUNO + "=? AND " + SQLiteHelper.DISCIPLINA + "=?",
                        new String[] { aluno, disciplina }, null, null, SQLiteHelper.ALUNO);

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
