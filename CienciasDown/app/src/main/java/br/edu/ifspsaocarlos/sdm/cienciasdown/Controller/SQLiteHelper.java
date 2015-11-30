package br.edu.ifspsaocarlos.sdm.cienciasdown.Controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Abner - Manutençao on 11/09/2015.
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "educadown.db";
    public static final String DATABASE_TABLE_ALUNO = "aluno";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "nome";
    public static final String KEY_NASCIMENTO = "nascimento";
    public static final String KEY_TURMA = "turma";

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_CREATE = "CREATE TABLE "+ DATABASE_TABLE_ALUNO +" " +
            "("+ KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME +" TEXT NOT NULL, "
            + KEY_NASCIMENTO +" TEXT NOT NULL, " + KEY_TURMA +" TEXT NOT NULL);";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int 	newVersion) {
        onCreate(database);
    }
}