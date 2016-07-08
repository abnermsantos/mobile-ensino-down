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

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
<<<<<<< HEAD

import java.util.ArrayList;
import java.util.List;

=======

import java.util.ArrayList;
import java.util.List;

>>>>>>> origin/master
import br.edu.ifspsaocarlos.sdm.cienciasdown.Controller.AlunoDAO;
import br.edu.ifspsaocarlos.sdm.cienciasdown.Controller.HistoricoDAO;
import br.edu.ifspsaocarlos.sdm.cienciasdown.Model.Aluno;
import br.edu.ifspsaocarlos.sdm.cienciasdown.Model.Historico;
import br.edu.ifspsaocarlos.sdm.cienciasdown.R;

public class HistoricoActivity extends AppCompatActivity {
    BarChart barChart;
    Spinner spinnerOp1;
    Spinner spinnerOp2;
    HistoricoDAO historicoDAO;
    AlunoDAO alunoDAO;
    List<Historico> historicoTurma;
    List<Aluno> alunos;
    SharedPreferences prefs;
    String turma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        spinnerOp1 = (Spinner) findViewById(R.id.spinnerOp1);
        spinnerOp2 = (Spinner) findViewById(R.id.spinnerOp2);
        barChart = (BarChart) findViewById(R.id.dataChart01);


        historicoDAO = new HistoricoDAO(this);
        historicoDAO.open();
        alunoDAO = new AlunoDAO(this);
        alunoDAO.open();

        //Recupera as preferencias compartilhadas
        prefs = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        turma = prefs.getString("key_turma", null);

        loadSppinerOp1();
        //criarGrafico("Ciclo da Água");
    }

    //Método para carregar o Sppiner
    private void loadSppinerOp1(){
        final String[] assunto = { "Água", "Astros e Estrelas", "Cinco Sentidos", "Fotossíntese", "Luz e Sombra", "Reciclagem" };
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, assunto);
<<<<<<< HEAD

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerOp1.setAdapter(dataAdapter);

=======
<<<<<<< HEAD

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerOp1.setAdapter(dataAdapter);

=======

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerOp1.setAdapter(dataAdapter);

>>>>>>> origin/master
>>>>>>> origin/master
        //Listener do SppinerOp1
        spinnerOp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String assuntoSelect = spinnerOp1.getSelectedItem().toString();

                if (assuntoSelect.equals("Água")) {
                    spinnerOp2.setVisibility(View.VISIBLE);
                    final String[] conteudo = {"Ciclo da Água", "Consumo", "Poluição"};
                    // Creating adapter for spinner
                    ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(HistoricoActivity.this,
                            android.R.layout.simple_spinner_item, conteudo);

                    // Drop down layout style - list view with radio button
                    dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerOp2.setAdapter(dataAdapter2);
                } else if (assuntoSelect.equals("Astros e Estrelas")) {
                    spinnerOp2.setVisibility(View.INVISIBLE);
                    final String[] conteudo = {"Ciclo da Água", "Consumo", "Poluição"};
                    // Creating adapter for spinner
                    ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(HistoricoActivity.this,
                            android.R.layout.simple_spinner_item, conteudo);

                    // Drop down layout style - list view with radio button
                    dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerOp2.setAdapter(dataAdapter2);
                } else if (assuntoSelect.equals("Cinco Sentidos")) {
                    spinnerOp2.setVisibility(View.INVISIBLE);
                    final String[] conteudo = {"Ciclo da Água", "Consumo", "Poluição"};
                    // Creating adapter for spinner
                    ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(HistoricoActivity.this,
                            android.R.layout.simple_spinner_item, conteudo);

                    // Drop down layout style - list view with radio button
                    dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerOp2.setAdapter(dataAdapter2);
                } else if (assuntoSelect.equals("Fotossíntese")) {
                    spinnerOp2.setVisibility(View.INVISIBLE);
                    final String[] conteudo = {"Ciclo da Água", "Consumo", "Poluição"};
                    // Creating adapter for spinner
                    ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(HistoricoActivity.this,
                            android.R.layout.simple_spinner_item, conteudo);

                    // Drop down layout style - list view with radio button
                    dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerOp2.setAdapter(dataAdapter2);
                } else if (assuntoSelect.equals("Luz e Sombra")) {
                    spinnerOp2.setVisibility(View.INVISIBLE);
                    final String[] conteudo = {"Ciclo da Água", "Consumo", "Poluição"};
                    // Creating adapter for spinner
                    ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(HistoricoActivity.this,
                            android.R.layout.simple_spinner_item, conteudo);

                    // Drop down layout style - list view with radio button
                    dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerOp2.setAdapter(dataAdapter2);
                } else if (assuntoSelect.equals("Reciclagem")) {
                    spinnerOp2.setVisibility(View.INVISIBLE);
                    final String[] conteudo = {"Ciclo da Água", "Consumo", "Poluição"};
                    // Creating adapter for spinner
                    ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(HistoricoActivity.this,
                            android.R.layout.simple_spinner_item, conteudo);

                    // Drop down layout style - list view with radio button
                    dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerOp2.setAdapter(dataAdapter2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        //Listener do SppinerOp2
        spinnerOp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String conteudo = spinnerOp2.getSelectedItem().toString();
                criarGrafico(conteudo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });
    }

    //Método para criar e popular o gráfico
    private void criarGrafico(String conteudo){
<<<<<<< HEAD
        try{
            BarData data = new BarData(getXAxisValues(), getDataSet(conteudo));
            barChart.setData(data);
            barChart.setDescription(conteudo);
            barChart.animateXY(2000, 2000);
            barChart.invalidate();
        }catch (Exception e){
            Toast.makeText(HistoricoActivity.this, "Erro ao buscar dados do Histórico!",
                    Toast.LENGTH_SHORT).show();
        }

=======
        BarData data = new BarData(getXAxisValues(), getDataSet(conteudo));
        barChart.setData(data);
        barChart.setDescription(conteudo);
        barChart.animateXY(2000, 2000);
        barChart.invalidate();
>>>>>>> origin/master
    }

    private ArrayList<BarDataSet> getDataSet(String conteudo) {
        ArrayList<BarDataSet> dataSets = new ArrayList<>();
        ArrayList<BarEntry> valueSet = new ArrayList<>();
<<<<<<< HEAD
        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        ArrayList<BarEntry> valueSet3 = new ArrayList<>();
        BarEntry value;
        BarDataSet barDataSet = null;
        BarDataSet barDataSet2 = null;
        BarDataSet barDataSet3 = null;
=======
        BarEntry value = null;
        BarDataSet barDataSet = null;
<<<<<<< HEAD
        BarDataSet barDataSet2 = null;
        BarDataSet barDataSet3 = null;
=======
>>>>>>> origin/master
>>>>>>> origin/master

        //Busca os dados do Banco
        try{
            historicoTurma = null;
            historicoTurma = historicoDAO.buscaGrupo(turma, conteudo);
        }catch (Exception e){
            Toast.makeText(HistoricoActivity.this, "Erro ao buscar dados do Histórico!",
                    Toast.LENGTH_SHORT).show();
        }

        //Se o Array estiver vazio insere um valor padrão e oculta o gráfico, se não, preenche com os dados do banco
        if (historicoTurma.isEmpty()){
            value = new BarEntry(1.000f, 0); // Jan
            valueSet.add(value);
            barDataSet = new BarDataSet(valueSet, "Padrão");
            barDataSet.setColor(Color.rgb(0, 155, 0));
            dataSets.add(barDataSet);
            barChart.setVisibility(View.INVISIBLE);

            Toast.makeText(HistoricoActivity.this, "Nenhum dado encontrado.",
                    Toast.LENGTH_SHORT).show();

            return dataSets;
        }else {
            String tarefa1 = "";
            String tarefa2 = "";
            String tarefa3 = "";
<<<<<<< HEAD
            String legenda;
            int color;
            int position;
=======
            String legenda = "";
            int color = 0;
            int position = 0;
>>>>>>> origin/master

            barChart.setVisibility(View.VISIBLE);

            for(Historico h : historicoTurma) {

                if(tarefa1.equals("") || tarefa1.equals(h.getTarefa())){
                    tarefa1 = h.getTarefa();
                    position = 0;
                    legenda = tarefa1;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> origin/master
                    color = Color.rgb(1, 155, 255);

                    value = new BarEntry(Float.valueOf(h.getTentativas()), position);
                    valueSet.add(value);
                    barDataSet = new BarDataSet(valueSet, legenda);
                    barDataSet.setColor(color);

<<<<<<< HEAD
=======
=======
                    color = Color.rgb(0, 155, 0);
>>>>>>> origin/master
>>>>>>> origin/master
                } else if(tarefa2.equals("") || tarefa2.equals(h.getTarefa())){
                    tarefa2 = h.getTarefa();
                    position = 1;
                    legenda = tarefa2;
<<<<<<< HEAD
                    color = Color.rgb(0, 0, 300);

                    BarEntry value2 = new BarEntry(Float.valueOf(h.getTentativas()), position);
=======
<<<<<<< HEAD
                    color = Color.rgb(0, 0, 300);

                    BarEntry value2 = new BarEntry(Float.valueOf(h.getTentativas()), position);
                    ArrayList<BarEntry> valueSet2 = new ArrayList<>();
>>>>>>> origin/master
                    valueSet2.add(value2);
                    barDataSet2 = new BarDataSet(valueSet2, legenda);
                    barDataSet2.setColor(color);

<<<<<<< HEAD
=======
=======
                    color = Color.rgb(0, 205, 0);
>>>>>>> origin/master
>>>>>>> origin/master
                } else if(tarefa3.equals("") || tarefa3.equals(h.getTarefa())){
                    tarefa3 = h.getTarefa();
                    position = 2;
                    legenda = tarefa3;
<<<<<<< HEAD
                    color = Color.rgb(200, 0, 200);

                    BarEntry value3 = new BarEntry(Float.valueOf(h.getTentativas()), position);
=======
<<<<<<< HEAD
                    color = Color.rgb(200, 0, 200);

                    BarEntry value3 = new BarEntry(Float.valueOf(h.getTentativas()), position);
                    ArrayList<BarEntry> valueSet3 = new ArrayList<>();
>>>>>>> origin/master
                    valueSet3.add(value3);
                    barDataSet3 = new BarDataSet(valueSet3, legenda);
                    barDataSet3.setColor(color);

                }
            }
            dataSets.add(barDataSet);
            dataSets.add(barDataSet2);
            dataSets.add(barDataSet3);
<<<<<<< HEAD
=======
=======
                    color = Color.rgb(0, 255, 0);
                }
                value = new BarEntry(Float.valueOf(h.getTentativas()), position);
                valueSet.add(value);
                barDataSet = new BarDataSet(valueSet, legenda);
                barDataSet.setColor(color);
                dataSets.add(barDataSet);
            }
>>>>>>> origin/master
>>>>>>> origin/master
            return dataSets;
        }
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        //Busca os dados do Banco
<<<<<<< HEAD
        try{
=======
<<<<<<< HEAD
        /*try{
=======
        try{
>>>>>>> origin/master
>>>>>>> origin/master
            alunos = null;
            alunos = alunoDAO.buscaPorTurma(turma);
        }catch (Exception e){
            Toast.makeText(HistoricoActivity.this, "Erro ao buscar dados do Histórico!",
                    Toast.LENGTH_SHORT).show();
        }
        for(Aluno a : alunos) {
<<<<<<< HEAD
            xAxis.add(a.getNome());

        }
        /*xAxis.add("1");
        xAxis.add("2");
        xAxis.add("3");*/
=======
<<<<<<< HEAD
            //xAxis.add(a.getNome());

        }*/
        xAxis.add("1");
        xAxis.add("2");
        xAxis.add("3");
=======
            xAxis.add(a.getNome());
        }
>>>>>>> origin/master
>>>>>>> origin/master
        return xAxis;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}