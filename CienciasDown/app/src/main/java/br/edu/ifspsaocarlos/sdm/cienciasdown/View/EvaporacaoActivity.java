package br.edu.ifspsaocarlos.sdm.cienciasdown.View;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.MediaController;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.security.Provider;

import br.edu.ifspsaocarlos.sdm.cienciasdown.Controller.HistoricoDAO;
import br.edu.ifspsaocarlos.sdm.cienciasdown.Model.Historico;
import br.edu.ifspsaocarlos.sdm.cienciasdown.R;

public class EvaporacaoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private YouTubePlayerView youTubePlayerView;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private Button btContinuar;
    private Button btRepetir;
    private TextView txtParabens;
    private TextView txtRepetir;
    private String idVideo = "y6XMDA9mj3Y";
    private int tentativas;
    SharedPreferences prefs;
    Historico historico;
    HistoricoDAO historicoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaporacao);

        //Recupera as preferencias compartilhadas
        prefs = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String youtubeKey = prefs.getString("key_youtubeAPI", null);

        //Inicializa as variáveis e faz a conexão com o Banco
        historico = new Historico();
        historicoDAO = new HistoricoDAO(this);
        historicoDAO.open();

        //Vinculando Botões e textos
        btContinuar = (Button) findViewById(R.id.bt_evap_continuar);
        btRepetir = (Button) findViewById(R.id.bt_evap_repetir);
        txtParabens = (TextView) findViewById(R.id.txtEvapParabens);
        txtRepetir = (TextView) findViewById(R.id.txtEvapRepetir);

        //Inicializa o componente "YOUTUBE"
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubeEvaporacao);
        youTubePlayerView.initialize(youtubeKey, this);

        //Chama a função para verificar a seleção
        verificaRadioGroup();

        //Inicializa a variável tentativas
        tentativas = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        EvaporacaoActivity v = this;
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

    //Método para inicializar o vídeo no componente
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(!b){
            youTubePlayer.cueVideo(idVideo);
        }

    }

    //Métdo para mostrar mensagem de erro caso não funcione o vídeo
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Ocorreu uma falha ao carregar o vídeo, favor verifique sua conexão.",
                Toast.LENGTH_SHORT).show();
    }

    //Verifica qual opção foi selecionada
    private void verificaRadioGroup(){
        radioGroup = (RadioGroup) findViewById(R.id.rdgEvaporacao);
        radioButton1 = (RadioButton) findViewById(R.id.opEvapSol);
        radioButton2 = (RadioButton) findViewById(R.id.opEvapCasa);
        radioButton3 = (RadioButton) findViewById(R.id.opEvapPeixe);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.opEvapSol) {
                    //Altera caracteristicas de visibilidade dos botões e textos
                    btContinuar.setVisibility(View.VISIBLE);
                    btRepetir.setVisibility(View.INVISIBLE);
                    txtParabens.setVisibility(View.VISIBLE);
                    txtRepetir.setVisibility(View.INVISIBLE);
                    //Bloqueia os radios para seleção
                    radioButton1.setEnabled(false);
                    radioButton2.setEnabled(false);
                    radioButton3.setEnabled(false);
                } else if(checkedId == R.id.opEvapCasa) {
                    //Altera caracteristicas de visibilidade dos botões e textos
                    btContinuar.setVisibility(View.INVISIBLE);
                    btRepetir.setVisibility(View.VISIBLE);
                    txtParabens.setVisibility(View.INVISIBLE);
                    txtRepetir.setVisibility(View.VISIBLE);
                    //Bloqueia os radios para seleção
                    radioButton1.setEnabled(false);
                    radioButton2.setEnabled(false);
                    radioButton3.setEnabled(false);
                } else {
                    //Altera caracteristicas de visibilidade dos botões e textos
                    btContinuar.setVisibility(View.INVISIBLE);
                    btRepetir.setVisibility(View.VISIBLE);
                    txtParabens.setVisibility(View.INVISIBLE);
                    txtRepetir.setVisibility(View.VISIBLE);
                    //Bloqueia os radios para seleção
                    radioButton1.setEnabled(false);
                    radioButton2.setEnabled(false);
                    radioButton3.setEnabled(false);
                }
            }

        });
    }

    //Método para salvar a tentativa errada e liberar o sistema para tetar novamente
    public void onClickRepetir(View v){
        try{
            //Incrementa o total de tentativas
            tentativas += 1;
            //Altera caracteristicas de visibilidade dos botões e textos
            btContinuar.setVisibility(View.INVISIBLE);
            btRepetir.setVisibility(View.INVISIBLE);
            txtParabens.setVisibility(View.INVISIBLE);
            txtRepetir.setVisibility(View.INVISIBLE);
            //Bloqueia os radios para seleção
            radioButton1.setEnabled(true);
            radioButton2.setEnabled(true);
            radioButton3.setEnabled(true);
        }catch (Exception e){
            Toast.makeText(EvaporacaoActivity.this, "Ocorreu uma falha, por favor tente novamente.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Método para chamar a Activity Ciclo da água e salvar os dados no banco
    public void onClickContinuar(View v){
        try{
            //Incrementa o total de tentativas
            tentativas += 1;

            //Salva os dados no banco
            historico.setAluno(prefs.getString("key_nome", null));
            historico.setTurma(prefs.getString("key_turma", null));
            historico.setDisciplina("Ciclo da Água");
            historico.setTarefa("Evaporação");
            historico.setTentativas(String.valueOf(tentativas));
            historicoDAO.create(historico);

            //Finaliza e volta para a tela anterior
            Intent resultIntent = new Intent();
            setResult(RESULT_OK,resultIntent);
            finish();
        }catch (Exception e){
            Toast.makeText(EvaporacaoActivity.this, "Ocorreu uma falha, por favor tente novamente.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
