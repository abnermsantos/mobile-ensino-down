package br.edu.ifspsaocarlos.sdm.cienciasdown.View;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifspsaocarlos.sdm.cienciasdown.Controller.AlunoDAO;
import br.edu.ifspsaocarlos.sdm.cienciasdown.Model.Aluno;
import br.edu.ifspsaocarlos.sdm.cienciasdown.R;

public class CadastroActivity extends ActionBarActivity {
    Aluno aluno;
    AlunoDAO alunoDAO;
    EditText edtNome;
    EditText edtNascimento;
    EditText edtTurma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        alunoDAO = new AlunoDAO(this);
        alunoDAO.open();

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtNascimento = (EditText) findViewById(R.id.edtNascimento);
        edtTurma = (EditText) findViewById(R.id.edtTurma);

        if (getIntent().hasExtra("aluno")){
            this.aluno = (Aluno)getIntent().getSerializableExtra("aluno");
            edtNome.setText(aluno.getNome());
            edtNascimento.setText(aluno.getNascimento());
            edtTurma.setText(aluno.getTurma());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem itemAdd = menu.findItem(R.id.ic_add);
        itemAdd.setVisible(true);
        MenuItem itemLista = menu.findItem(R.id.ic_alunos);
        itemLista.setVisible(true);

        if(aluno != null){
            MenuItem item = menu.findItem(R.id.ic_remove);
            item.setVisible(true);
            itemLista.setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        CadastroActivity v = this;
        switch (item.getItemId()) {
            case R.id.ic_help:
                criaPopup();
                break;
            case R.id.ic_add:
                cadastrarAluno();
                break;
            case R.id.ic_remove:
                removerAluno(aluno);
                break;
            case R.id.ic_alunos:
                listarTodos();
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

    //Método para cadastrar o aluno no Banco de Dados
    private void cadastrarAluno(){
        String nome = edtNome.getText().toString();
        String nascimento = edtNascimento.getText().toString();
        String turma = edtTurma.getText().toString();

        if(aluno == null){
            aluno = new Aluno();
            aluno.setNome(nome);
            aluno.setNascimento(nascimento);
            aluno.setTurma(turma);

            try{
                alunoDAO.create(aluno);

                edtNome.setText("");
                edtNascimento.setText("");
                edtTurma.setText("");

                Toast.makeText(CadastroActivity.this, "Aluno Cadastrado com Sucesso!",
                        Toast.LENGTH_SHORT).show();

                Intent resultIntent = new Intent();
                setResult(RESULT_OK,resultIntent);
                finish();
            }catch (Exception e){
                Toast.makeText(CadastroActivity.this, "Erro ao cadastrar aluno, por favor, verifique os campos digitados!",
                        Toast.LENGTH_SHORT).show();
            }
        }else{
            aluno.setNome(nome);
            aluno.setNascimento(nascimento);
            aluno.setTurma(turma);

            try{
                alunoDAO.update(aluno);

                edtNome.setText("");
                edtNascimento.setText("");
                edtTurma.setText("");

                Toast.makeText(CadastroActivity.this, "Cadastro Alterado com Sucesso!",
                        Toast.LENGTH_SHORT).show();

                Intent resultIntent = new Intent();
                setResult(RESULT_OK,resultIntent);
                finish();
            }catch (Exception e){
                Toast.makeText(CadastroActivity.this, "Erro ao atualizar cadastro, por favor, verifique os campos digitados!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Método Remover Aluno
    private void removerAluno(Aluno aluno){
        try{
            alunoDAO.delete(aluno);

            Toast.makeText(CadastroActivity.this, "Aluno Removido com Sucesso!",
                    Toast.LENGTH_SHORT).show();

            Intent resultIntent = new Intent();
            setResult(RESULT_OK,resultIntent);
            finish();
        }catch (Exception e){
            Toast.makeText(CadastroActivity.this, "Erro ao remover aluno, por favor, tente novamente!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Método para chamar a Activity Alunos Cadastrados
    private void listarTodos(){
        try{
            Intent alunosIntent = new Intent(CadastroActivity.this, AlunosActivity.class);
            startActivity(alunosIntent);
        }catch (Exception e){
            Toast.makeText(CadastroActivity.this, "Ocorreu uma falha, por favor tente novamente.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /*
    //Método para cadastrar o aluno no Banco de Dados
    public void onClickEnviar(View v){

    }

    //Método para chamar a Activity Alunos Cadastrados
    public void onClickVerAlunos(View v){

    }
    */
}