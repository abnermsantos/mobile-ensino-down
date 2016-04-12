package br.edu.ifspsaocarlos.sdm.cienciasdown.View;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import br.edu.ifspsaocarlos.sdm.cienciasdown.Controller.HistoricoDAO;
import br.edu.ifspsaocarlos.sdm.cienciasdown.Model.Historico;
import br.edu.ifspsaocarlos.sdm.cienciasdown.R;

public class CondensacaoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private YouTubePlayerView youTubePlayerView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condensacao);

        //Recupera as preferencias compartilhadas
        prefs = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String youtubeKey = prefs.getString("key_youtubeAPI", null);

        //Inicializa as variáveis e faz a conexão com o Banco
        historico = new Historico();
        historicoDAO = new HistoricoDAO(this);
        historicoDAO.open();

        //Vinculando Botões e textos
        btnResposta = (Button) findViewById(R.id.btnCond);
        txtResposta = (TextView) findViewById(R.id.txtCond);

        //Inicializa o componente "YOUTUBE"
        idVideo = getString(R.string.id_video);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubeCondensacao);
        youTubePlayerView.initialize(youtubeKey, this);

        //Chama a função para verificar a seleção
        verificaRadioGroup();

        //Inicializa as variáveis tentativas
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
        radioGroup = (RadioGroup) findViewById(R.id.rdgCondensacao);
        radioButton1 = (RadioButton) findViewById(R.id.opCondNuvem);
        radioButton2 = (RadioButton) findViewById(R.id.opCondCasa);
        radioButton3 = (RadioButton) findViewById(R.id.opCondPeixe);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.opCondNuvem) {
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
                } else if (checkedId == R.id.opEvapCasa) {
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
    public void onClickCond(View v){
        if(tentar){
            try{
                //Incrementa o total de tentativas
                tentativas += 1;

                //Salva os dados no banco
                historico.setAluno(prefs.getString("key_nome", null));
                historico.setTurma(prefs.getString("key_turma", null));
                historico.setDisciplina("Ciclo da Água");
                historico.setTarefa("Condensação");
                historico.setTentativas(String.valueOf(tentativas));
                historicoDAO.create(historico);

                //Finaliza e volta para a tela anterior
                Intent resultIntent = new Intent();
                setResult(RESULT_OK,resultIntent);
                finish();
            }catch (Exception e){
                Toast.makeText(CondensacaoActivity.this, "Ocorreu uma falha, por favor tente novamente.",
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
        mp = MediaPlayer.create(CondensacaoActivity.this, R.raw.aplauso);
        mp.start();
    }

    /*@Override
    protected void onDestroy(){
        super.onDestroy();
        mp.release();
        mp = null;
    }*/
}
