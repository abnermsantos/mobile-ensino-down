package br.edu.ifspsaocarlos.sdm.cienciasdown.View;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import br.edu.ifspsaocarlos.sdm.cienciasdown.R;

public class CicloAguaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_agua);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        CicloAguaActivity v = this;
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

    //Método para chamar a Activity de Trabalho(Evaporação)
    public void onClickEvaporacao(View v){
        try{
            Intent intent = new Intent(CicloAguaActivity.this, EvaporacaoActivity.class);
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(CicloAguaActivity.this, "Ocorreu uma falha, por favor tente novamente.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Método para chamar a Activity de Trabalho(Condensação)
    public void onClickCondensacao(View v){
        try{
            Intent intent = new Intent(CicloAguaActivity.this, CondensacaoActivity.class);
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(CicloAguaActivity.this, "Ocorreu uma falha, por favor tente novamente.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Método para chamar a Activity de Trabalho(Precipitação)
    public void onClickPrecipitacao(View v){
        try{
            Intent intent = new Intent(CicloAguaActivity.this, PrecipitacaoActivity.class);
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(CicloAguaActivity.this, "Ocorreu uma falha, por favor tente novamente.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}