package br.edu.ifspsaocarlos.sdm.cienciasdown.View;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import br.edu.ifspsaocarlos.sdm.cienciasdown.R;

public class MainActivity extends ActionBarActivity {
    Button btAgua;
    Button btAstros;
    Button btSentidos;
    Button btFotossintese;
    Button btLuz;
    Button btReciclagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Definindo as propriedades do Spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.names_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Vinculando Botões
        btAgua = (Button) findViewById(R.id.bt_acessar_agua);
        btAstros = (Button) findViewById(R.id.bt_acessar_astros);
        btSentidos = (Button) findViewById(R.id.bt_acessar_sentidos);
        btFotossintese = (Button) findViewById(R.id.bt_acessar_fotossintese);
        btLuz = (Button) findViewById(R.id.bt_acessar_luz);
        btReciclagem = (Button) findViewById(R.id.bt_acessar_reciclagem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MainActivity v = this;
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

    //Método para chamar a Activity de Cadastro
    public void onClickCadastro(View v){
        try{
            Intent cadastroIntent = new Intent(MainActivity.this, CadastroActivity.class);
            startActivity(cadastroIntent);
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "Ocorreu uma falha, por favor tente novamente.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Método para chamar a Activity Histórico
    public void onClickHistorico(View v){
        try{
            Intent historicoIntent = new Intent(MainActivity.this, HistoricoActivity.class);
            startActivity(historicoIntent);
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "Ocorreu uma falha, por favor tente novamente.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Método para chamar a Activity Agua
    public void onClickAgua(View v){
        try{
            Intent aguaIntent = new Intent(MainActivity.this, AguaActivity.class);
            startActivity(aguaIntent);
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "Ocorreu uma falha, por favor tente novamente.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Método para chamar a Activity Astros
    public void onClickAstros(View v){
        try{
            /*Intent astrosIntent = new Intent(MainActivity.this, AstrosActivity.class);
            startActivity(astrosIntent);*/
            Toast.makeText(MainActivity.this, "Funcionalidade ainda não implementada!",
                    Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "Ocorreu uma falha, por favor tente novamente.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Método para chamar a Activity Cinco Sentidos
    public void onClickSentidos(View v){
        try{
            /*Intent sentidosIntent = new Intent(MainActivity.this, SentidosActivity.class);
            startActivity(sentidosIntent);*/
            Toast.makeText(MainActivity.this, "Funcionalidade ainda não implementada!",
                    Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "Ocorreu uma falha, por favor tente novamente.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Método para chamar a Activity Fotossíntese
    public void onClickFotossintese(View v){
        try{
            /*Intent fotossinteseIntent = new Intent(MainActivity.this, FotossinteseActivity.class);
            startActivity(fotossinteseIntent);*/
            Toast.makeText(MainActivity.this, "Funcionalidade ainda não implementada!",
                    Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "Ocorreu uma falha, por favor tente novamente.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Método para chamar a Activity Luz
    public void onClickLuz(View v){
        try{
            /*Intent luzIntent = new Intent(MainActivity.this, LuzActivity.class);
            startActivity(luzIntent);*/
            Toast.makeText(MainActivity.this, "Funcionalidade ainda não implementada!",
                    Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "Ocorreu uma falha, por favor tente novamente.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Método para chamar a Activity Reciclagem
    public void onClickReciclagem(View v){
        try{
            /*Intent reciclagemIntent = new Intent(MainActivity.this, ReciclagemActivity.class);
            startActivity(reciclagemIntent);*/
            Toast.makeText(MainActivity.this, "Funcionalidade ainda não implementada!",
                    Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "Ocorreu uma falha, por favor tente novamente.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
