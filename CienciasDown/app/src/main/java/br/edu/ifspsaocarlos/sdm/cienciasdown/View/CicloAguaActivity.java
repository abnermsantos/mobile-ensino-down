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
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import android.net.NetworkInfo;

import br.edu.ifspsaocarlos.sdm.cienciasdown.R;

public class CicloAguaActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_agua);
        context = CicloAguaActivity.this;
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
            Intent intent;
            if(existeConexao()){
                intent = new Intent(CicloAguaActivity.this, EvaporacaoActivity.class);
                startActivity(intent);
            }else{
                intent = new Intent(CicloAguaActivity.this, EvaporacaoOfflineActivity.class);
                startActivity(intent);
            }
        }catch (Exception e){
            Toast.makeText(CicloAguaActivity.this, "Ocorreu uma falha, por favor tente novamente.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Método para chamar a Activity de Trabalho(Condensação)
    public void onClickCondensacao(View v){
        try{
            Intent intent;
            if(existeConexao()){
                intent = new Intent(CicloAguaActivity.this, CondensacaoActivity.class);
                startActivity(intent);
            }else{
                intent = new Intent(CicloAguaActivity.this, CondensacaoOfflineActivity.class);
                startActivity(intent);
            }
        }catch (Exception e){
            Toast.makeText(CicloAguaActivity.this, "Ocorreu uma falha, por favor tente novamente.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Método para chamar a Activity de Trabalho(Precipitação)
    public void onClickPrecipitacao(View v){
        try{
            Intent intent;
            if(existeConexao()){
                intent = new Intent(CicloAguaActivity.this, PrecipitacaoActivity.class);
                startActivity(intent);
            }else{
                intent = new Intent(CicloAguaActivity.this, PrecipitacaoOfflineActivity.class);
                startActivity(intent);
            }
        }catch (Exception e){
            Toast.makeText(CicloAguaActivity.this, "Ocorreu uma falha, por favor tente novamente.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Método para testar a conexão com a Internet
    private boolean existeConexao(){
        ConnectivityManager connectivity = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo netInfo = connectivity.getActiveNetworkInfo();

            // Se não existe nenhum tipo de conexão retorna false
            if (netInfo == null) {
                return false;
            }

            int netType = netInfo.getType();

            // Verifica se a conexão é do tipo WiFi ou Mobile e
            // retorna true se estiver conectado ou false em
            // caso contrário
            if (netType == ConnectivityManager.TYPE_WIFI ||
                    netType == ConnectivityManager.TYPE_MOBILE) {
                return netInfo.isConnected();

            } else {
                return false;
            }
        }else{
            return false;
        }
    }
}