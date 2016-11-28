package com.iesb.sergio.iesbapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.iesb.sergio.iesbapp.model.RegisteredDiscipline;

import java.util.ArrayList;

public class DisciplineRegisteredAdapter extends ArrayAdapter<RegisteredDiscipline> {


    public ArrayList<RegisteredDiscipline> itens = new ArrayList<>();
    Context mContext;

    private static class ViewHolder {
        TextView textViewName;
        TextView textViewHorario;
        TextView textViewSala;
    }


    public DisciplineRegisteredAdapter(Context context, ArrayList<RegisteredDiscipline> disciplines) {
        super(context, R.layout.discipline_registered_item,disciplines);
        mContext = context;
        itens.addAll(disciplines);
    }

    @Override
    public int getCount() {
        return itens.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RegisteredDiscipline discipline = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.discipline_registered_item,parent,false);
            viewHolder.textViewName = (TextView) convertView.findViewById(R.id.registeredDisciplineName);
            viewHolder.textViewHorario = (TextView) convertView.findViewById(R.id.registeredDate);
            viewHolder.textViewSala = (TextView) convertView.findViewById(R.id.registeredRoom);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.textViewName.setText(discipline.getName());
        viewHolder.textViewSala.setText(discipline.getRoom());
        viewHolder.textViewHorario.setText(discipline.getDay());

        return convertView;
    }




}
