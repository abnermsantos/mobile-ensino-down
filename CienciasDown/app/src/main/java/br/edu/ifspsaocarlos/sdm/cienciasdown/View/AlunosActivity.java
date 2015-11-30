package br.edu.ifspsaocarlos.sdm.cienciasdown.View;

import android.app.Dialog;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.ifspsaocarlos.sdm.cienciasdown.Controller.AlunoDAO;
import br.edu.ifspsaocarlos.sdm.cienciasdown.Model.Aluno;
import br.edu.ifspsaocarlos.sdm.cienciasdown.R;
import br.edu.ifspsaocarlos.sdm.cienciasdown.adapter.AlunoArrayAdapter;

public class AlunosActivity extends ListActivity {
    AlunoDAO alunoDAO;
    List<Aluno> alunosArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alunos);

        alunoDAO = new AlunoDAO(this);
        alunoDAO.open();

        listaAlunos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlunosActivity v = this;
        switch (item.getItemId()) {
            case R.id.ic_help:
                criaPopup();
                break;
        }
        return true;
    }

    //Método para Criar Popup "HELP"
    public void criaPopup(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_help);
        dialog.show();
    }

    //Método para Listar os Alunos Cadastrados
    public void listaAlunos(){
        try{
            alunosArray = alunoDAO.buscaTodos();
            AlunoArrayAdapter adapter = new AlunoArrayAdapter(this, alunosArray);
            setListAdapter(adapter);
        }catch (Exception e){
            Toast.makeText(AlunosActivity.this, "Erro ao listar contatos!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        AlunoArrayAdapter adapter = (AlunoArrayAdapter) getListAdapter();
        Aluno aluno = (Aluno) adapter.getItem(position);
        Intent i = new Intent(this, CadastroActivity.class);
        i.putExtra("aluno", aluno);
        startActivityForResult(i, 0);
        Intent resultIntent = new Intent();
        setResult(RESULT_OK,resultIntent);
        finish();
    }
}