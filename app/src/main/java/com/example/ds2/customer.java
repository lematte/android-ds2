package com.example.ds2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Vector;

public class customer extends ArrayAdapter {
    ArrayList listValues;
    LayoutInflater inflater;

    public customer(Context context, ArrayList listValues, LayoutInflater inflater){
        super(context, -1, listValues);
        this.listValues = listValues;
        this.inflater = inflater;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.list_livre, parent, false);


        TextView item1 = view.findViewById(R.id.idlistNomLivre);
        TextView item2 = view.findViewById(R.id.idlistAuteurs);

        Vector itemValues = (Vector) listValues.get(position);
        String nom, auteur;
        nom = (String) itemValues.get(1);
        auteur = (String) itemValues.get(2);

        item1.setText(nom);
        item2.setText(auteur);

        return view;
    }
}
