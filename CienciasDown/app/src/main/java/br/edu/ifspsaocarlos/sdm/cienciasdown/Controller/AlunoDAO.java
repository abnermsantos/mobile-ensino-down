package br.edu.ifspsaocarlos.sdm.cienciasdown.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifspsaocarlos.sdm.cienciasdown.Model.Aluno;

/**
 * Created by Abner - Manutençao on 11/09/2015.
 */
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
