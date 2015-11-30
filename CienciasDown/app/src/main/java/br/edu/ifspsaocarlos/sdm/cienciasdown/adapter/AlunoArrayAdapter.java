package br.edu.ifspsaocarlos.sdm.cienciasdown.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.ifspsaocarlos.sdm.cienciasdown.Model.Aluno;
import br.edu.ifspsaocarlos.sdm.cienciasdown.R;

/**
 * Created by Abner - Manutençao on 11/09/2015.
 */
public class AlunoArrayAdapter extends ArrayAdapter<Aluno> {
    private LayoutInflater inflater;

    public AlunoArrayAdapter(Activity activity, List<Aluno> objects) {
        super(activity, R.layout.aluno_celula, objects);
        this.inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.aluno_celula, null);
            holder = new ViewHolder();
            holder.nome = (TextView) convertView.findViewById(R.id.nome);
            holder.turma = (TextView) convertView.findViewById(R.id.turma);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Aluno a = getItem(position);
        holder.nome.setText(a.getNome());
        holder.turma.setText(a.getTurma());
        return convertView;
    }

    static class ViewHolder {
        public TextView nome;
        public TextView turma;
    }
}
