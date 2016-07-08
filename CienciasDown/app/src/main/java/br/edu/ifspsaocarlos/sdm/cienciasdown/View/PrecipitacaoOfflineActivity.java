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
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.widget.MediaController;

import br.edu.ifspsaocarlos.sdm.cienciasdown.Controller.HistoricoDAO;
import br.edu.ifspsaocarlos.sdm.cienciasdown.Model.Historico;
import br.edu.ifspsaocarlos.sdm.cienciasdown.R;

public class PrecipitacaoOfflineActivity extends AppCompatActivity {
    private Button btnResposta;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private TextView txtResposta;
    private String idVideo;
    private int tentativas;
    private boolean tentar;
    SharedPreferences prefs;
    MediaPlayer mp;
    Historico historico;
    HistoricoDAO historicoDAO;
    ProgressDialog pDialog;
    VideoView videoview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precipitacao_offline);

        //Recupera as preferencias compartilhadas
        prefs = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);

        //Inicializa as variáveis e faz a conexão com o Banco
        historico = new Historico();
        historicoDAO = new HistoricoDAO(this);
        historicoDAO.open();

        //Vinculando Botões e textos
        btnResposta = (Button) findViewById(R.id.btnPrec);
        txtResposta = (TextView) findViewById(R.id.txtPrec);

        //Inicializa o componente "VIDEOVIEW"
        videoview = (VideoView) findViewById(R.id.videoViewPrecipitacao);
        iniciaVideo();

        //Chama a função para verificar a seleção
        verificaRadioGroup();

        //Inicializa a variável tentativas
        tentativas = 0;
        tentar = false;
    }

    //Método para Criar Popup "HELP"
    public void criaPopup(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_help);
        dialog.show();
    }

    //Método para Inicializar o componente VideoView
    private void iniciaVideo(){
        // Create a progressbar
        pDialog = new ProgressDialog(PrecipitacaoOfflineActivity.this);
        // Set progressbar title
        pDialog.setTitle("Ciclo da Água");
        // Set progressbar message
        pDialog.setMessage("Carregando...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        // Show progressbar
        pDialog.show();

        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(
                    PrecipitacaoOfflineActivity.this);
            mediacontroller.setAnchorView(videoview);
            // Get the URL from String VideoURL
            //Uri video = Uri.parse(VideoURL);
            String path = "android.resource://" + getPackageName() + "/" + R.raw.ciclo;
            videoview.setMediaController(mediacontroller);
            videoview.setVideoURI(Uri.parse(path));

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoview.requestFocus();
        videoview.setOnPreparedListener(new OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                videoview.start();
            }
        });
    }


    //Verifica qual opção foi selecionada
    private void verificaRadioGroup(){
        radioGroup = (RadioGroup) findViewById(R.id.rdgPrecipitacao);
        radioButton1 = (RadioButton) findViewById(R.id.opPrecNuvem);
        radioButton2 = (RadioButton) findViewById(R.id.opPrecCasa);
        radioButton3 = (RadioButton) findViewById(R.id.opPrecPeixe);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.opPrecNuvem) {
                    //Altera caracteristicas dos botões e textos
                    btnResposta.setVisibility(View.VISIBLE);
                    txtResposta.setVisibility(View.VISIBLE);
                    btnResposta.setText(getString(R.string.texto_botao_continuar));
                    txtResposta.setText(getString(R.string.texto_parabens));
                    tentar = true;
                    //Bloqueia os radios para seleção
                    radioButton1.setEnabled(false);
                    radioButton2.setEnabled(false);
                    radioButton3.setEnabled(false);
                    //Método para chamar o audio
                    playAudio();
                } else if (checkedId == R.id.opPrecCasa) {
                    //Altera caracteristicas dos botões e textos
                    btnResposta.setVisibility(View.VISIBLE);
                    txtResposta.setVisibility(View.VISIBLE);
                    btnResposta.setText(getString(R.string.texto_botao_repetir));
                    txtResposta.setText(getString(R.string.texto_tente_novamente));
                    tentar = false;
                    //Bloqueia os radios para seleção
                    radioButton1.setEnabled(false);
                    radioButton2.setEnabled(false);
                    radioButton3.setEnabled(false);
                } else {
                    //Altera caracteristicas dos botões e textos
                    btnResposta.setVisibility(View.VISIBLE);
                    txtResposta.setVisibility(View.VISIBLE);
                    btnResposta.setText(getString(R.string.texto_botao_repetir));
                    txtResposta.setText(getString(R.string.texto_tente_novamente));
                    tentar = false;
                    //Bloqueia os radios para seleção
                    radioButton1.setEnabled(false);
                    radioButton2.setEnabled(false);
                    radioButton3.setEnabled(false);
                }
            }

        });
    }

    //Método para tratar eventos do botão
    public void onClickPrec(View v){
        if(tentar){
            try{
                //Incrementa o total de tentativas
                tentativas += 1;

                //Salva os dados no banco
                historico.setAluno(prefs.getString("key_nome", null));
                historico.setTurma(prefs.getString("key_turma", null));
                historico.setDisciplina("Ciclo da Água");
                historico.setTarefa("Precipitação");
                historico.setTentativas(String.valueOf(tentativas));
                historicoDAO.create(historico);

                //Finaliza e volta para a tela anterior
                Intent resultIntent = new Intent();
                setResult(RESULT_OK,resultIntent);
                finish();
            }catch (Exception e){
                Toast.makeText(PrecipitacaoOfflineActivity.this, "Ocorreu uma falha, por favor tente novamente.",
                        Toast.LENGTH_SHORT).show();
            }
        }else {
            //Incrementa o total de tentativas
            tentativas += 1;
            //Altera caracteristicas de visibilidade dos botões e textos
            btnResposta.setVisibility(View.INVISIBLE);
            txtResposta.setVisibility(View.INVISIBLE);
            //Bloqueia os radios para seleção
            radioButton1.setEnabled(true);
            radioButton2.setEnabled(true);
            radioButton3.setEnabled(true);
        }
    }

    //Método para chamar o arquivo de aplausos
    private void playAudio(){
        mp = MediaPlayer.create(PrecipitacaoOfflineActivity.this, R.raw.aplauso);
        mp.start();
    }

    /*@Override
    protected void onDestroy(){
        super.onDestroy();
        mp.release();
        mp = null;
    }*/
}
