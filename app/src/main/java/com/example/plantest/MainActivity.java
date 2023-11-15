package com.example.plantest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.plantest.utility.ApiCalls;
import com.example.plantest.utility.JSONParsing;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Person> persons;

    private PersonAdapter personAdapter;

    String url = "http://10.0.2.2:8087/api/person/all";

    RequestQueue requestQueue;

    FloatingActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        persons = new ArrayList<>();
        recyclerView = findViewById(R.id.person_recycler_view);
        actionButton = findViewById(R.id.floatingActionButton);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        personAdapter = new PersonAdapter(this.persons, this, R.layout.person_item);
        requestQueue = Volley.newRequestQueue(this);
        actionButton.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), PersonAddActivity.class)));
    }
    @Override
    protected void onResume() {
        super.onResume();
        this.persons.clear();

        ApiCalls.makeJsonArrayRequest(url, Request.Method.GET, requestQueue, new ApiCalls.JsonArrayResponseHandler() {
            @Override
            public void onSuccess(JSONArray response) {
                try {
                    List<Person> list = JSONParsing.toArrayOfJavaObjects(response, Person.class);
                    MainActivity.this.persons.addAll(list);
                    MainActivity.this.recyclerView.setAdapter(personAdapter);
                } catch (JSONException e) {
                    Log.d("Error in parsing the Data", e.toString());
                }
            }
            @Override
            public void onError(VolleyError error) {
                Log.d("Error in fetching the Data", error.toString());
            }
        });
    }
}