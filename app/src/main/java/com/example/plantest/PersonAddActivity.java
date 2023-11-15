package com.example.plantest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.plantest.utility.ApiCalls;
import com.example.plantest.utility.JSONFactory;
import com.example.plantest.utility.components.CustomDatePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

public class PersonAddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText nom;
    private EditText prenom;
    private TextView date;
    private Button setDate;
    private Button send;
    private RequestQueue requestQueue;
    private CustomDatePickerDialog datePickerDialog;
    private String url = "http://10.0.2.2:8087/api/person/save";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_add);
        nom = findViewById(R.id.add_nom);
        prenom = findViewById(R.id.add_prenom);
        date = findViewById(R.id.date_txt);
        setDate = findViewById(R.id.date_set);
        send = findViewById(R.id.send);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        datePickerDialog = new CustomDatePickerDialog(this, this, 2023, 7, 11);
        setDate.setOnClickListener(v -> {
            datePickerDialog.show();
        });
        send.setOnClickListener(v -> {
            String nom_text = nom.getText().toString();
            String prenom_txt = prenom.getText().toString();
            String date_txt = date.getText().toString();
            Person person = new Person(0, nom_text, prenom_txt, date_txt);
            try {
                JSONObject jsonObject = JSONFactory.getJSONObject(person);

                ApiCalls.makeJsonObjectRequest(url, Request.Method.POST, jsonObject, requestQueue, new ApiCalls.JsonObjectResponseHandler() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        PersonAddActivity.this.finish();
                    }
                    @Override
                    public void onError(VolleyError error) {
                        Log.d("Error in Creating the Person Object", error.toString());
                    }
                });
            } catch (JSONException e) {
                Log.d("Failing to Ceate the JSON Object", e.toString());
            }
        });
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date.setText(datePickerDialog.getDateStr(year, month, dayOfMonth));
    }
}