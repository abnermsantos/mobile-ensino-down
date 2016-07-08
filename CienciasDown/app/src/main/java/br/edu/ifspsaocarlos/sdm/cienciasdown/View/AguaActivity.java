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
package br.edu.ifspsaocarlos.sdm.cienciasdown.View;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import br.edu.ifspsaocarlos.sdm.cienciasdown.R;

public class AguaActivity extends ActionBarActivity {
    Button btCiclo;
    Button btConsumo;
    Button btPoluicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agua);

        //Vinculando Botões
        btCiclo = (Button) findViewById(R.id.bt_ciclo_agua);
        btConsumo = (Button) findViewById(R.id.bt_consumo_agua);
        btPoluicao = (Button) findViewById(R.id.bt_poluicao_agua);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AguaActivity v = this;
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

    //Método para chamar a Activity Ciclo da Água
    public void onClickCiclo(View v){
        try{
            Intent cicloIntent = new Intent(AguaActivity.this, CicloAguaActivity.class);
            startActivity(cicloIntent);
        }catch (Exception e){
            Toast.makeText(AguaActivity.this, "Ocorreu uma falha, por favor tente novamente.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Método para chamar a Activity Consumo da Água
    public void onClickConsumo(View v){
        try{
            Intent consumoIntent = new Intent(AguaActivity.this, ConsumoAguaActivity.class);
            startActivity(consumoIntent);
        }catch (Exception e){
            Toast.makeText(AguaActivity.this, "Ocorreu uma falha, por favor tente novamente.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Método para chamar a Activity Poluição da Água
    public void onClickPoluicao(View v){
        try{
            Intent poluicaoIntent = new Intent(AguaActivity.this, PoluicaoAguaActivity.class);
            startActivity(poluicaoIntent);
        }catch (Exception e){
            Toast.makeText(AguaActivity.this, "Ocorreu uma falha, por favor tente novamente.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
