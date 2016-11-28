package com.iesb.sergio.iesbapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.iesb.sergio.iesbapp.model.Discipline;
import com.iesb.sergio.iesbapp.model.Grade;

import java.util.ArrayList;

/**
 * Created by henriquepru on 28/11/16.
 */

public class GradesListAdapter extends ArrayAdapter<Grade> {

    public ArrayList<Grade> itens = new ArrayList<>();
    Context mContext;

    private static class ViewHolder {
        TextView textGradeP1;
        TextView textGradeP2;
        TextView textGradeP3;
        TextView textFrequence;
        TextView textDisciplineName;
    }

    public GradesListAdapter(Context context, ArrayList<Grade> itens) {
        super(context, R.layout.grade_list_item, itens);
        mContext = context;
        this.itens = itens;
    }

    @Override
    public int getCount() {
        return itens.size();
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Grade grade = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.grade_list_item,parent,false);
            viewHolder.textDisciplineName = (TextView) convertView.findViewById(R.id.materiaTextView);
            viewHolder.textGradeP1 = (TextView) convertView.findViewById(R.id.notaP1TextView);
            viewHolder.textGradeP2 = (TextView) convertView.findViewById(R.id.notaP2TextView);
            viewHolder.textGradeP3 = (TextView) convertView.findViewById(R.id.notaP3TextView);
            viewHolder.textFrequence = (TextView) convertView.findViewById(R.id.frequenciaTextView);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.textDisciplineName.setText(grade.getDiscipline());
        viewHolder.textGradeP1.setText(""+grade.getGradeP1());
        viewHolder.textGradeP2.setText(""+grade.getGradeP2());
        viewHolder.textGradeP3.setText(""+grade.getGradeP3());
        viewHolder.textFrequence.setText(""+grade.getFrequence()+"%");

        return convertView;
    }
}
