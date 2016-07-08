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

import br.edu.ifspsaocarlos.sdm.cienciasdown.Model.Aluno;

public class AlunoDAO {
    private Context context;
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public AlunoDAO(Context context) {
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

    public List<String> buscaNomes(){
        List<String> nomes = new ArrayList<String>();
        Cursor cursor = database.query(SQLiteHelper.TABLE_ALUNO, new String[] {
                        SQLiteHelper.KEY_NAME},
                null, null, null, null, SQLiteHelper.KEY_NAME);

        if (cursor!=null)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String nome = "";
                nome = cursor.getString(0);
                nomes.add(nome);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return nomes;
    }

    public List<Aluno> buscaPorTurma(String turma){
        List<Aluno> alunos = new ArrayList<Aluno>();
        Cursor cursor = database.query(SQLiteHelper.TABLE_ALUNO, new String[] {
                        SQLiteHelper.KEY_NAME, SQLiteHelper.KEY_TURMA},
                SQLiteHelper.KEY_TURMA + "=?", new String[] { turma }, null, null, SQLiteHelper.KEY_NAME);

        if (cursor!=null)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Aluno aluno = new Aluno();
                aluno.setNome(cursor.getString(0));
                aluno.setTurma(cursor.getString(1));
                alunos.add(aluno);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return alunos;
    }

    public List<Aluno> buscaTodos() {

        List<Aluno> alunos = new ArrayList<Aluno>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_ALUNO, new String[] { SQLiteHelper.KEY_ID,
                        SQLiteHelper.KEY_NAME, SQLiteHelper.KEY_NASCIMENTO, SQLiteHelper.KEY_TURMA},
                null, null, null, null, SQLiteHelper.KEY_NAME);

        if (cursor!=null)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Aluno aluno = new Aluno();
                aluno.setId(cursor.getString(0));
                aluno.setNome(cursor.getString(1));
                aluno.setNascimento(cursor.getString(2));
                aluno.setTurma(cursor.getString(3));
                alunos.add(aluno);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return alunos;
    }

    public Aluno search(String busca)
    {

        Aluno aluno = new Aluno();

        Cursor cursor = database.query(SQLiteHelper.TABLE_ALUNO, new String[] { SQLiteHelper.KEY_ID,
                        SQLiteHelper.KEY_NAME, SQLiteHelper.KEY_NASCIMENTO, SQLiteHelper.KEY_TURMA},
                SQLiteHelper.KEY_NAME + "=?", new String[] { busca }, null, null, SQLiteHelper.KEY_NAME);

        if (cursor!=null)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                aluno.setId(cursor.getString(0));
                aluno.setNome(cursor.getString(1));
                aluno.setNascimento(cursor.getString(2));
                aluno.setTurma(cursor.getString(3));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return aluno;
    }

    public void update(Aluno a) {
        ContentValues updateValues = new ContentValues();
        updateValues.put(SQLiteHelper.KEY_NAME, a.getNome());
        updateValues.put(SQLiteHelper.KEY_NASCIMENTO, a.getNascimento());
        updateValues.put(SQLiteHelper.KEY_TURMA, a.getTurma());
        database.update(SQLiteHelper.TABLE_ALUNO, updateValues, SQLiteHelper.KEY_ID + "=" + a.getId(), null);
    }

    public void create(Aluno a) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.KEY_NAME, a.getNome());
        values.put(SQLiteHelper.KEY_NASCIMENTO, a.getNascimento());
        values.put(SQLiteHelper.KEY_TURMA, a.getTurma());
        database.insert(SQLiteHelper.TABLE_ALUNO, null, values);
    }

    public void delete(Aluno a)
    {
        database.delete(SQLiteHelper.TABLE_ALUNO, SQLiteHelper.KEY_ID +"="+ a.getId(), null);
    }
}
