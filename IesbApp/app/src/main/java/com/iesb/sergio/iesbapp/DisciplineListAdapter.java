package com.iesb.sergio.iesbapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.iesb.sergio.iesbapp.model.Discipline;

import java.util.ArrayList;
import java.util.List;

public class DisciplineListAdapter extends ArrayAdapter<Discipline> {


    public ArrayList<Discipline> itens = new ArrayList<>();
    Context mContext;

    private static class ViewHolder {
        TextView textViewMateria;
        TextView textViewHorario;
        TextView textViewSala;
    }

    public DisciplineListAdapter(Context context, int resource) {
        super(context, resource);
        mContext = context;
    }

    public DisciplineListAdapter(Context context, ArrayList<Discipline> disciplines) {
        super(context, R.layout.list_item,disciplines);
        mContext = context;
        itens.addAll(disciplines);
    }

    public DisciplineListAdapter(Context context, int resource, int textViewResourceId, List<Discipline> objects) {
        super(context, resource, textViewResourceId, objects);
        itens.addAll(objects);
        mContext = context;
    }

    @Override
    public int getCount() {
        return itens.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Discipline discipline = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item,parent,false);
            viewHolder.textViewMateria = (TextView) convertView.findViewById(R.id.titleTextView);
            viewHolder.textViewHorario = (TextView) convertView.findViewById(R.id.disciplinaTextView);
            viewHolder.textViewSala = (TextView) convertView.findViewById(R.id.salaTextView);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.textViewMateria.setText(discipline.getName());
        viewHolder.textViewSala.setText(discipline.getTeacherName());
        viewHolder.textViewHorario.setText(discipline.getTeacherEmail());

        return convertView;
    }

}
