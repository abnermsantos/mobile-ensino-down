package br.edu.ifspsaocarlos.sdm.cienciasdown.Controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Abner - Manutençao on 11/09/2015.
 */
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