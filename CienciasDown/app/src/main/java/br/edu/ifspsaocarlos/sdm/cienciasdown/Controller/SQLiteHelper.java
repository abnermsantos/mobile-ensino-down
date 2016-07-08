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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "educadown.db";
    public static final String TABLE_ALUNO = "aluno";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "nome";
    public static final String KEY_NASCIMENTO = "nascimento";
    public static final String KEY_TURMA = "turma";

    public static final String TABLE_HISTORICO = "historico";
    public static final String KEY_HISTORICO = "idHistorico";
    public static final String ALUNO = "nome";
    public static final String ALUNO_TURMA = "alunoTurma";
    public static final String DISCIPLINA = "disciplina";
    public static final String TAREFA = "tarefa";
    public static final String TENTATIVAS = "tentativas";

    public static final int DATABASE_VERSION = 1;

    public static final String CREATE_TABLE_ALUNO =
            "CREATE TABLE "+ TABLE_ALUNO +"("+ KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_NAME +" TEXT NOT NULL, " + KEY_NASCIMENTO +" TEXT NOT NULL, " + KEY_TURMA +
                    " TEXT NOT NULL);";

    public static final String CREATE_TABLE_HISTORICO =
            " CREATE TABLE " + TABLE_HISTORICO + "(" + KEY_HISTORICO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ALUNO + " TEXT NOT NULL, " + ALUNO_TURMA + " TEXT NOT NULL, " + DISCIPLINA +
                    " TEXT NOT NULL, " + TAREFA + " TEXT NOT NULL, " + TENTATIVAS + " TEXT NOT NULL, " +
                    "FOREIGN KEY (" + ALUNO + ") REFERENCES " + TABLE_ALUNO + "(" + KEY_NAME + ")," +
                    "FOREIGN KEY (" + ALUNO_TURMA + ") REFERENCES " + TABLE_ALUNO  + "(" + KEY_TURMA + "));";



    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_ALUNO);
        database.execSQL(CREATE_TABLE_HISTORICO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int 	newVersion) {
        onCreate(database);
    }
}