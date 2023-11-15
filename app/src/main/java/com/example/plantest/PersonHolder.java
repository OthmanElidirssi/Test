package com.example.plantest;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class PersonHolder extends RecyclerView.ViewHolder {

    private TextView nom;
    private TextView prenom;

    public PersonHolder(@NonNull View itemView) {
        super(itemView);

        nom=itemView.findViewById(R.id.person_nom);
        prenom=itemView.findViewById(R.id.person_prenom);
    }

    public void populte(Person person){
        nom.setText(person.getNom());
        prenom.setText(person.getPrenom());
    }
}
