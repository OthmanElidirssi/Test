package com.example.plantest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonHolder> {

    private List<Person> persons;

    private Context context;

    private int layout;

    private LayoutInflater inflater;

    public PersonAdapter(List<Person> persons, Context context, int layout) {
        this.persons = persons;
        this.context = context;
        this.layout = layout;
        this.inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PersonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View person_item=this.inflater.inflate(this.layout,parent,false);
        return new PersonHolder(person_item);
    }
    @Override
    public void onBindViewHolder(@NonNull PersonHolder holder, int position) {

        Person person=this.persons.get(position);
        holder.populte(person);
    }

    @Override
    public int getItemCount() {
        return this.persons.size();
    }
}
